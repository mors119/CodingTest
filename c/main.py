from pathlib import Path
import subprocess
import sys

# C 자동 채점기
def main() -> None:
    """
    사용법:
        python3 c/main.py 1/1245
    """
    if len(sys.argv) < 2:
        print("usage: python3 c/main.py <group/problem>", file=sys.stderr)
        print("example: python3 c/main.py 1/1245", file=sys.stderr)
        sys.exit(1)

    problem = sys.argv[1]

    root_dir = Path(__file__).resolve().parent.parent
    script_path = root_dir / "c" / "script" / f"{problem}.c"
    input_path = root_dir / "input" / f"{problem}.txt"
    output_path = root_dir / "c" / "bin" / problem

    if not script_path.is_file():
        print(f"c script not found: {script_path}", file=sys.stderr)
        sys.exit(1)

    if not input_path.is_file():
        print(f"input file not found: {input_path}", file=sys.stderr)
        sys.exit(1)

    output_path.parent.mkdir(parents=True, exist_ok=True)

    compile_cmd = [
        "gcc",
        str(script_path),
        "-O2",
        "-std=c11",
        "-Wall",
        "-Wextra",
        "-o",
        str(output_path),
    ]

    compile_result = subprocess.run(compile_cmd, capture_output=True, text=True)
    if compile_result.returncode != 0:
        if compile_result.stdout:
            print(compile_result.stdout, end="", file=sys.stderr)
        if compile_result.stderr:
            print(compile_result.stderr, end="", file=sys.stderr)
        sys.exit(1)

    with input_path.open("r", encoding="utf-8") as infile:
        run_result = subprocess.run([str(output_path)], stdin=infile, capture_output=True, text=True)

    if run_result.stdout:
        print(run_result.stdout, end="")

    if run_result.returncode != 0:
        if run_result.stderr:
            print(run_result.stderr, end="", file=sys.stderr)
        sys.exit(run_result.returncode)


if __name__ == "__main__":
    main()
