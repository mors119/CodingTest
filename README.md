# 공통

### zsh 자동화 스크립트

```zsh
cat >> coding-test.zsh <<'EOF'
find_ct_root() {
  local dir="$PWD"

  while [[ "$dir" != "/" ]]; do
    if [[ -d "$dir/input" && ( -d "$dir/python" || -d "$dir/rust" || -d "$dir/node" || -d "$dir/java" ) ]]; then
      echo "$dir"
      return 0
    fi
    dir="$(dirname "$dir")"
  done

  return 1
}
EOF

source coding-test.zsh
```

### 자동화 스크립트 활성화 (원하는 언어 선택 후 반드시 실행)

```zsh
grep -qxF "source $(pwd)/coding-test.zsh" ~/.zshrc || \
echo "source $(pwd)/coding-test.zsh" >> ~/.zshrc
```

# Python

### 기본 템플릿 생성

```bash
mkdir -p input
mkdir -p python/script
```

```bash
cat > python/main.py <<'EOF'
from importlib.util import module_from_spec, spec_from_file_location
from pathlib import Path
import sys

def load_solve_function(script_path: Path):
    spec = spec_from_file_location("problem_module", script_path)
    if spec is None or spec.loader is None:
        raise ImportError(f"cannot load module from {script_path}")

    module = module_from_spec(spec)
    spec.loader.exec_module(module)

    if not hasattr(module, "solve"):
        raise AttributeError(f"{script_path} does not define solve(text: str)")

    return module.solve

def main():
    if len(sys.argv) < 2:
        print("usage: python3 python/main.py <group/problem>", file=sys.stderr)
        sys.exit(1)

    problem = sys.argv[1]
    root = Path(__file__).resolve().parent.parent

    script = root / "python" / "script" / f"{problem}.py"
    input_file = root / "input" / f"{problem}.txt"

    if not script.is_file():
        print(f"python script not found: {script}", file=sys.stderr)
        sys.exit(1)

    if not input_file.is_file():
        print(f"input file not found: {input_file}", file=sys.stderr)
        sys.exit(1)

    text = input_file.read_text(encoding="utf-8")
    result = load_solve_function(script)(text)
    print(result)

if __name__ == "__main__":
    main()
EOF
```

### zsh 자동화 스크립트

```zsh
cat >> coding-test.zsh <<'OUTER'
pyct() {
  if [[ $# -lt 1 ]]; then
    echo "usage: pyct <group/problem>"
    echo "example: pyct 1/1245"
    return 1
  fi

  local root
  root="$(find_ct_root)" || {
    echo "coding-test project root not found"
    echo "expected directories: ./python and ./input"
    return 1
  }

  local problem="$1"
  local main_path="$root/python/main.py"
  local script_path="$root/python/script/${problem}.py"
  local input_path="$root/input/${problem}.txt"

  if [[ ! -f "$main_path" ]]; then
    echo "python runner not found: $main_path"
    return 1
  fi

  if [[ ! -f "$script_path" ]]; then
    echo "python script not found: $script_path"
    return 1
  fi

  if [[ ! -f "$input_path" ]]; then
    echo "input file not found: $input_path"
    return 1
  fi

  python3 "$main_path" "$problem"
}

pynew() {
  if [[ $# -lt 1 ]]; then
    echo "usage: pynew <group/problem>"
    echo "example: pynew 1/2000"
    return 1
  fi

  local root
  root="$(find_ct_root)" || {
    echo "coding-test project root not found"
    echo "expected directories: ./python and ./input"
    return 1
  }

  local problem="$1"
  local script_path="$root/python/script/${problem}.py"
  local input_path="$root/input/${problem}.txt"

  mkdir -p "$(dirname "$script_path")"
  mkdir -p "$(dirname "$input_path")"

  if [[ ! -f "$script_path" ]]; then
    cat > "$script_path" <<'PYEOF'
def solve(text: str) -> str:
    lines = [line.strip() for line in text.splitlines() if line.strip()]

    # TODO: 문제 풀이 작성
    return "\n".join(lines)
PYEOF
    echo "created: $script_path"
  else
    echo "already exists: $script_path"
  fi

  if [[ ! -f "$input_path" ]]; then
    touch "$input_path"
    echo "created: $input_path"
  else
    echo "already exists: $input_path"
  fi
}
OUTER

source coding-test.zsh
```

### 문제 / 풀이 템플릿 생성

```bash
# pynew 1/1245
pynew [폴더번호]/[문제번호]
```

### 정답 체크

```bash
# pyct 1/1245
pyct [폴더번호]/[문제번호]
```

# Rust

### 기본 템플릿 생성

```bash
mkdir -p input
cargo new rust
mkdir -p rust/script
```

```bash
cat > rust/src/main.rs <<'EOF'
use std::env;
use std::fs;
use std::path::PathBuf;

// AUTO_MODS_START
// AUTO_MODS_END

fn main() {
    if let Err(err) = run() {
        eprintln!("{err}");
        std::process::exit(1);
    }
}

fn run() -> Result<(), String> {
    let args: Vec<String> = env::args().collect();

    if args.len() < 2 {
        return Err("usage: cargo run -- 1/1245".to_string());
    }

    let problem = &args[1];

    let root_dir = project_root()?;
    let input_path = root_dir.join("input").join(format!("{problem}.txt"));

    if !input_path.is_file() {
        return Err(format!("input file not found: {}", input_path.display()));
    }

    let text = fs::read_to_string(&input_path)
        .map_err(|e| format!("failed to read input file {}: {}", input_path.display(), e))?;

    let answer = match problem.as_str() {
        // AUTO_MATCH_ARMS_START
        // AUTO_MATCH_ARMS_END
        _ => return Err(format!("unsupported problem: {problem}")),
    };

    println!("{answer}");
    Ok(())
}

fn project_root() -> Result<PathBuf, String> {
    let current = env::current_dir().map_err(|e| format!("failed to get current dir: {e}"))?;
    let mut dir = current.as_path();

    loop {
        let has_rust = dir.join("rust").is_dir();
        let has_input = dir.join("input").is_dir();

        if has_rust && has_input {
            return Ok(dir.to_path_buf());
        }

        match dir.parent() {
            Some(parent) => dir = parent,
            None => return Err("coding-test project root not found".to_string()),
        }
    }
}
EOF
```

### zsh 자동화 스크립트

```zsh
cat >> coding-test.zsh <<'OUTER'
rsct() {
  if [[ $# -lt 1 ]]; then
    echo "usage: rsct <group/problem>"
    echo "example: rsct 1/1245"
    return 1
  fi

  local root
  root="$(find_ct_root)" || {
    echo "coding-test project root not found"
    echo "expected directories: ./rust and ./input"
    return 1
  }

  local problem="$1"
  local cargo_toml="$root/rust/Cargo.toml"
  local script_path="$root/rust/script/${problem}.rs"
  local input_path="$root/input/${problem}.txt"

  if [[ ! -f "$cargo_toml" ]]; then
    echo "rust Cargo.toml not found: $cargo_toml"
    return 1
  fi

  if [[ ! -f "$script_path" ]]; then
    echo "rust script not found: $script_path"
    return 1
  fi

  if [[ ! -f "$input_path" ]]; then
    echo "input file not found: $input_path"
    return 1
  fi

  cargo run --manifest-path "$cargo_toml" -- "$problem"
}

rsnew() {
  if [[ $# -lt 1 ]]; then
    echo "usage: rsnew <group/problem>"
    echo "example: rsnew 1/2000"
    return 1
  fi

  local root
  root="$(find_ct_root)" || {
    echo "coding-test project root not found"
    echo "expected directories: ./rust and ./input"
    return 1
  }

  local problem="$1"
  local script_path="$root/rust/script/${problem}.rs"
  local input_path="$root/input/${problem}.txt"
  local main_rs="$root/rust/src/main.rs"

  local module_name
  module_name="p${problem//\//_}"

  local mod_line
  mod_line="#[path = \"../script/${problem}.rs\"]"
  local mod_decl="mod ${module_name};"

  local match_arm
  match_arm="        \"${problem}\" => ${module_name}::solve(&text).to_string(),"

  mkdir -p "$(dirname "$script_path")"
  mkdir -p "$(dirname "$input_path")"

  if [[ ! -f "$script_path" ]]; then
    cat > "$script_path" <<'RUSTEOF'
pub fn solve(text: &str) -> String {
    let lines: Vec<&str> = text
        .lines()
        .map(|line| line.trim())
        .filter(|line| !line.is_empty())
        .collect();

    // TODO: 문제 풀이 작성
    lines.join("\n")
}
RUSTEOF
    echo "created: $script_path"
  else
    echo "already exists: $script_path"
  fi

  if [[ ! -f "$input_path" ]]; then
    touch "$input_path"
    echo "created: $input_path"
  else
    echo "already exists: $input_path"
  fi

  if [[ ! -f "$main_rs" ]]; then
    echo "main.rs not found: $main_rs"
    return 1
  fi

  if ! grep -q "AUTO_MODS_START" "$main_rs"; then
    echo "AUTO_MODS_START marker not found in $main_rs"
    return 1
  fi

  if ! grep -q "AUTO_MATCH_ARMS_START" "$main_rs"; then
    echo "AUTO_MATCH_ARMS_START marker not found in $main_rs"
    return 1
  fi

  # 이미 등록되어 있으면 중복 추가하지 않음
  if grep -q "mod ${module_name};" "$main_rs"; then
    echo "already registered: ${module_name}"
  else
    python3 - "$main_rs" "$mod_line" "$mod_decl" "$match_arm" <<'PY'
import sys
from pathlib import Path

main_rs = Path(sys.argv[1])
mod_line = sys.argv[2]
mod_decl = sys.argv[3]
match_arm = sys.argv[4]

text = main_rs.read_text(encoding="utf-8")

mods_start = "// AUTO_MODS_START"
mods_end = "// AUTO_MODS_END"
match_start = "// AUTO_MATCH_ARMS_START"
match_end = "// AUTO_MATCH_ARMS_END"

if mods_start not in text or mods_end not in text:
    raise SystemExit("mod markers not found")

if match_start not in text or match_end not in text:
    raise SystemExit("match markers not found")

mod_insert = f"{mods_start}\n{mod_line}\n{mod_decl}"
text = text.replace(mods_start, mod_insert, 1)

match_insert = f"{match_start}\n{match_arm}"
text = text.replace(match_start, match_insert, 1)

main_rs.write_text(text, encoding="utf-8")
PY
    echo "registered in main.rs: ${problem} -> ${module_name}"
  fi
}
OUTER

source coding-test.zsh
```

### 문제 / 풀이 템플릿 생성

```bash
# rsnew 1/1245
rsnew [폴더번호]/[문제번호]
```

### 정답 체크

```bash
# rsct 1/1245
rsct [폴더번호]/[문제번호]
```

# Node

### 기본 템플릿 생성

```bash
mkdir -p input
mkdir -p node/script
```

```bash
cat > node/main.js <<'EOF'
const fs = require("fs");
const path = require("path");

function main() {
  const problem = process.argv[2];

  if (!problem) {
    console.error("usage: node node/main.js <group/problem>");
    process.exit(1);
  }

  const root = path.resolve(__dirname, "..");
  const script = path.join(root, "node", "script", `${problem}.js`);
  const input = path.join(root, "input", `${problem}.txt`);

  if (!fs.existsSync(script)) {
    console.error(`node script not found: ${script}`);
    process.exit(1);
  }

  if (!fs.existsSync(input)) {
    console.error(`input file not found: ${input}`);
    process.exit(1);
  }

  const solve = require(script).solve;
  const text = fs.readFileSync(input, "utf-8");

  console.log(solve(text));
}

main();
EOF
```

### zsh 자동화 스크립트

```zsh
cat >> coding-test.zsh <<'OUTER'
jsct() {
  if [[ $# -lt 1 ]]; then
    echo "usage: jsct <group/problem>"
    echo "example: jsct 1/1245"
    return 1
  fi

  local root
  root="$(find_ct_root)" || {
    echo "coding-test project root not found"
    echo "expected directories: ./node and ./input"
    return 1
  }

  local problem="$1"
  local main_path="$root/node/main.js"
  local script_path="$root/node/script/${problem}.js"
  local input_path="$root/input/${problem}.txt"

  if [[ ! -f "$main_path" ]]; then
    echo "node runner not found: $main_path"
    return 1
  fi

  if [[ ! -f "$script_path" ]]; then
    echo "node script not found: $script_path"
    return 1
  fi

  if [[ ! -f "$input_path" ]]; then
    echo "input file not found: $input_path"
    return 1
  fi

  node "$main_path" "$problem"
}

jsnew() {
  if [[ $# -lt 1 ]]; then
    echo "usage: jsnew <group/problem>"
    echo "example: jsnew 1/2000"
    return 1
  fi

  local root
  root="$(find_ct_root)" || {
    echo "coding-test project root not found"
    echo "expected directories: ./node and ./input"
    return 1
  }

  local problem="$1"
  local script_path="$root/node/script/${problem}.js"
  local input_path="$root/input/${problem}.txt"

  mkdir -p "$(dirname "$script_path")"
  mkdir -p "$(dirname "$input_path")"

  if [[ ! -f "$script_path" ]]; then
    cat > "$script_path" <<'NODEOF'
function solve(text) {
  const lines = text
    .split(/\r?\n/)
    .map((line) => line.trim())
    .filter(Boolean);

  // TODO: 문제 풀이 작성
  return lines.join("\n");
}

module.exports = { solve };
NODEOF
    echo "created: $script_path"
  else
    echo "already exists: $script_path"
  fi

  if [[ ! -f "$input_path" ]]; then
    touch "$input_path"
    echo "created: $input_path"
  else
    echo "already exists: $input_path"
  fi
}
OUTER

source coding-test.zsh
```

### 문제 / 풀이 템플릿 생성

```bash
# jsnew 1/1245
jsnew [폴더번호]/[문제번호]
```

### 정답 체크

```bash
# jsct 1/1245
jsct [폴더번호]/[문제번호]
```

# Java

### 기본 템플릿 생성

```bash
mkdir -p input
mkdir -p java/src/runner
mkdir -p java/src/script
mkdir -p java/out
```

```bash
cat > java/src/runner/Main.java <<'EOF'
package runner;

import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.err.println("usage: java runner.Main <group/problem>");
            System.exit(1);
        }

        String problem = args[0];
        String[] parts = problem.split("/");

        if (parts.length != 2) {
            System.err.println("invalid problem format: " + problem);
            System.exit(1);
        }

        String group = parts[0];
        String number = parts[1];

        Path rootDir = Path.of("").toAbsolutePath();
        Path inputPath = rootDir.resolve("input").resolve(group).resolve(number + ".txt");

        if (!Files.exists(inputPath)) {
            System.err.println("input file not found: " + inputPath);
            System.exit(1);
        }

        String className = "script.p" + group + ".p" + number + ".Main";
        String text = Files.readString(inputPath);

        Class<?> clazz = Class.forName(className);
        Method solveMethod = clazz.getMethod("solve", String.class);

        Object result = solveMethod.invoke(null, text);
        System.out.println(result);
    }
}
EOF
```

### zsh 자동화 스크립트

```zsh
cat >> coding-test.zsh <<'OUTER'
javact() {
  if [[ $# -lt 1 ]]; then
    echo "usage: javact <group/problem>"
    echo "example: javact 1/1245"
    return 1
  fi

  local root
  root="$(find_ct_root)" || {
    echo "coding-test project root not found"
    echo "expected directories: ./java and ./input"
    return 1
  }

  local problem="$1"
  local runner_path="$root/java/src/runner/Main.java"
  local input_path="$root/input/${problem}.txt"

  local group="${problem%%/*}"
  local number="${problem##*/}"
  local problem_java="$root/java/src/script/p${group}/p${number}/Main.java"

  if [[ ! -f "$runner_path" ]]; then
    echo "java runner not found: $runner_path"
    return 1
  fi

  if [[ ! -f "$problem_java" ]]; then
    echo "java problem file not found: $problem_java"
    return 1
  fi

  if [[ ! -f "$input_path" ]]; then
    echo "input file not found: $input_path"
    return 1
  fi

  mkdir -p "$root/java/out"

  javac -d "$root/java/out" $(find "$root/java/src" -name "*.java") || return 1
  java -cp "$root/java/out" runner.Main "$problem"
}

javanew() {
  if [[ $# -lt 1 ]]; then
    echo "usage: javanew <group/problem>"
    echo "example: javanew 1/2000"
    return 1
  fi

  local root
  root="$(find_ct_root)" || {
    echo "coding-test project root not found"
    echo "expected directories: ./java and ./input"
    return 1
  }

  local problem="$1"
  local group="${problem%%/*}"
  local number="${problem##*/}"

  local script_path="$root/java/src/script/p${group}/p${number}/Main.java"
  local input_path="$root/input/${problem}.txt"
  local runner_path="$root/java/src/runner/Main.java"

  mkdir -p "$(dirname "$script_path")"
  mkdir -p "$(dirname "$input_path")"
  mkdir -p "$(dirname "$runner_path")"

  if [[ ! -f "$runner_path" ]]; then
    cat > "$runner_path" <<'JAVAEOF'
package runner;

import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.err.println("usage: java runner.Main <group/problem>");
            System.err.println("example: java runner.Main 1/1245");
            System.exit(1);
        }

        String problem = args[0];
        String[] parts = problem.split("/");

        if (parts.length != 2) {
            System.err.println("invalid problem format: " + problem);
            System.err.println("expected format: <group/problem>");
            System.exit(1);
        }

        String group = parts[0];
        String number = parts[1];

        Path rootDir = Path.of("").toAbsolutePath();
        Path inputPath = rootDir.resolve("input").resolve(group).resolve(number + ".txt");

        if (!Files.exists(inputPath)) {
            System.err.println("input file not found: " + inputPath);
            System.exit(1);
        }

        String className = "script.p" + group + ".p" + number + ".Main";
        String text = Files.readString(inputPath);

        Class<?> clazz = Class.forName(className);
        Method solveMethod = clazz.getMethod("solve", String.class);

        Object result = solveMethod.invoke(null, text);
        System.out.println(result);
    }
}
JAVAEOF
    echo "created: $runner_path"
  else
    echo "already exists: $runner_path"
  fi

  if [[ ! -f "$script_path" ]]; then
    cat > "$script_path" <<'JAVA_CODE'
package script.p${group}.p${number};

import java.util.Arrays;
import java.util.List;

public class Main {
    public static String solve(String text) {
        List<String> lines = Arrays.stream(text.split("\\\\R"))
                .map(String::trim)
                .filter(line -> !line.isEmpty())
                .toList();

        // TODO: 문제 풀이 작성
        return String.join("\n", lines);
    }
}
JAVA_CODE
    echo "created: $script_path"
  else
    echo "already exists: $script_path"
  fi

  if [[ ! -f "$input_path" ]]; then
    touch "$input_path"
    echo "created: $input_path"
  else
    echo "already exists: $input_path"
  fi
}
OUTER

source coding-test.zsh
```

### 문제 / 풀이 템플릿 생성

```bash
# javanew 1/1245
javanew [폴더번호]/[문제번호]
```

### 정답 체크

```bash
# javact 1/1245
javact [폴더번호]/[문제번호]
```
