
cat > create-ct.sh <<'EOF'
#!/usr/bin/env bash
set -e

PROJECT_NAME="${1:-my-ct}"

mkdir -p "$PROJECT_NAME"
cd "$PROJECT_NAME"

mkdir -p input
mkdir -p python/script
mkdir -p node/script
mkdir -p java/src/runner
mkdir -p java/src/script
mkdir -p java/out

cargo new rust
mkdir -p rust/script

cat > python/main.py <<'PYEOF'
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
PYEOF

cat > node/main.js <<'JSEOF'
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
JSEOF

cat > rust/src/main.rs <<'RSEOF'
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
RSEOF

cat > java/src/runner/Main.java <<'JAVAEOF'
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
JAVAEOF

cat > coding-test.zsh <<'ZSHEOF'
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
ZSHEOF

echo "created project: $PROJECT_NAME"
EOF

chmod +x create-ct.sh
./create-ct.sh my-ct