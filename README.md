# 공통

### zsh 자동화 스크립트

```zsh
cat >> coding-test.zsh <<'EOF'
find_ct_root() {
  local dir="$PWD"

  while [[ "$dir" != "/" ]]; do
    if [[ -d "$dir/input" && ( -d "$dir/python" || -d "$dir/rust" || -d "$dir/node" || -d "$dir/java" || -d "$dir/c" ) ]]; then
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
  local script_path="$root/python/script/${problem}.py"
  local input_path="$root/input/${problem}.txt"

  if [[ ! -f "$script_path" ]]; then
    echo "python script not found: $script_path"
    return 1
  fi

  if [[ ! -f "$input_path" ]]; then
    echo "input file not found: $input_path"
    return 1
  fi

  python3 "$script_path" < "$input_path"
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
import sys
input = sys.stdin.readline
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
mkdir -p rust/src/bin
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
  local group="${problem%%/*}"
  local number="${problem##*/}"

  local cargo_toml="$root/rust/Cargo.toml"
  local bin_name="p${group}_${number}"
  local script_path="$root/rust/src/bin/${bin_name}.rs"
  local input_path="$root/input/${group}/${number}.txt"

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

  cargo run --quiet --manifest-path "$cargo_toml" --bin "$bin_name" < "$input_path"
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
  local group="${problem%%/*}"
  local number="${problem##*/}"

  local bin_name="p${group}_${number}"
  local script_path="$root/rust/src/bin/${bin_name}.rs"
  local input_path="$root/input/${group}/${number}.txt"

  mkdir -p "$(dirname "$script_path")"
  mkdir -p "$(dirname "$input_path")"

  if [[ ! -f "$script_path" ]]; then
    cat > "$script_path" <<'EOF'
fn main() {

}
EOF
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
  local script_path="$root/node/script/${problem}.js"
  local input_path="$root/input/${problem}.txt"

  if [[ ! -f "$script_path" ]]; then
    echo "node script not found: $script_path"
    return 1
  fi

  if [[ ! -f "$input_path" ]]; then
    echo "input file not found: $input_path"
    return 1
  fi

  node "$script_path" < "$input_path"
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
    cat > "$script_path" <<'JSEOF'
const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').toString().trim();
JSEOF
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
mkdir -p java/src/script
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
  local group="${problem%%/*}"
  local number="${problem##*/}"

  local src_root="$root/java/src"
  local problem_java="$src_root/script/p${group}/p${number}/Main.java"
  local input_path="$root/input/${group}/${number}.txt"
  local class_name="script.p${group}.p${number}.Main"

  if [[ ! -f "$problem_java" ]]; then
    echo "java problem file not found: $problem_java"
    return 1
  fi

  if [[ ! -f "$input_path" ]]; then
    echo "input file not found: $input_path"
    return 1
  fi

  (
    cd "$src_root" || return 1
    find "script/p${group}/p${number}" -name "*.class" -delete
    javac "script/p${group}/p${number}/Main.java" || return 1
    java -cp "$src_root" "$class_name" < "$input_path"
  )
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
  local input_path="$root/input/${group}/${number}.txt"

  mkdir -p "$(dirname "$script_path")"
  mkdir -p "$(dirname "$input_path")"

  if [[ ! -f "$script_path" ]]; then
    cat > "$script_path" <<EOF
package script.p${group}.p${number};

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    }
}
EOF
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

# C

### 기본 템플릿 생성

```bash
mkdir -p input
mkdir -p c/script
```

### zsh 자동화 스크립트

```zsh
cat >> coding-test.zsh <<'OUTER'
cct() {
  if [[ $# -lt 1 ]]; then
    echo "usage: cct <group/problem>"
    echo "example: cct 1/1245"
    return 1
  fi

  local root
  root="$(find_ct_root)" || {
    echo "coding-test project root not found"
    echo "expected directories: ./c and ./input"
    return 1
  }

  local problem="$1"
  local script_path="$root/c/script/${problem}.c"
  local input_path="$root/input/${problem}.txt"
  local output_path="$root/c/bin/${problem}"

  if [[ ! -f "$script_path" ]]; then
    echo "c script not found: $script_path"
    return 1
  fi

  if [[ ! -f "$input_path" ]]; then
    echo "input file not found: $input_path"
    return 1
  fi

  mkdir -p "$(dirname "$output_path")"

  gcc "$script_path" -O2 -std=c11 -Wall -Wextra -o "$output_path" || return 1
  "$output_path" < "$input_path"
}

cnew() {
  if [[ $# -lt 1 ]]; then
    echo "usage: cnew <group/problem>"
    echo "example: cnew 1/2000"
    return 1
  fi

  local root
  root="$(find_ct_root)" || {
    echo "coding-test project root not found"
    echo "expected directories: ./c and ./input"
    return 1
  }

  local problem="$1"
  local script_path="$root/c/script/${problem}.c"
  local input_path="$root/input/${problem}.txt"

  mkdir -p "$(dirname "$script_path")"
  mkdir -p "$(dirname "$input_path")"

  if [[ ! -f "$script_path" ]]; then
    cat > "$script_path" <<'CEOF'
#include <stdio.h>

int main(void) {

    return 0;
}
CEOF
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
# cnew 1/1245
cnew [폴더번호]/[문제번호]
```

### 정답 체크

```bash
# cct 1/1245
cct [폴더번호]/[문제번호]
```
