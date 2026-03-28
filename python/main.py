from importlib.util import module_from_spec, spec_from_file_location
from pathlib import Path
import sys


def load_solve_function(script_path: Path):
    """
    문제 파일을 동적으로 불러와 solve 함수를 반환한다.
    """
    spec = spec_from_file_location("problem_module", script_path)
    if spec is None or spec.loader is None:
        raise ImportError(f"cannot load module from {script_path}")

    module = module_from_spec(spec)
    spec.loader.exec_module(module)

    if not hasattr(module, "solve"):
        raise AttributeError(f"{script_path} does not define solve(text: str)")

    return module.solve


def main() -> None:
    """
    사용법:
        python3 python/main.py 1/1245
    """
    if len(sys.argv) < 2:
        print("usage: python3 python/main.py <group/problem>", file=sys.stderr)
        print("example: python3 python/main.py 1/1245", file=sys.stderr)
        sys.exit(1)

    problem = sys.argv[1]

    # root/python/main.py 기준으로 프로젝트 루트 찾기
    root_dir = Path(__file__).resolve().parent.parent

    script_path = root_dir / "python" / "script" / f"{problem}.py"
    input_path = root_dir / "input" / f"{problem}.txt"

    if not script_path.is_file():
        print(f"python script not found: {script_path}", file=sys.stderr)
        sys.exit(1)

    if not input_path.is_file():
        print(f"input file not found: {input_path}", file=sys.stderr)
        sys.exit(1)

    solve = load_solve_function(script_path)
    text = input_path.read_text(encoding="utf-8")
    answer = solve(text)
    print(answer)


if __name__ == "__main__":
    main()