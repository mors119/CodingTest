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
