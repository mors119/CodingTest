const fs = require('fs');
const path = require('path');

function main() {
  if (process.argv.length < 3) {
    console.error('usage: node node/main.js <group/problem>');
    console.error('example: node node/main.js 1/1245');
    process.exit(1);
  }

  const problem = process.argv[2];

  const rootDir = path.resolve(__dirname, '..');
  const scriptPath = path.join(rootDir, 'node', 'script', `${problem}.js`);
  const inputPath = path.join(rootDir, 'input', `${problem}.txt`);

  if (!fs.existsSync(scriptPath)) {
    console.error(`node script not found: ${scriptPath}`);
    process.exit(1);
  }

  if (!fs.existsSync(inputPath)) {
    console.error(`input file not found: ${inputPath}`);
    process.exit(1);
  }

  const problemModule = require(scriptPath);

  if (typeof problemModule.solve !== 'function') {
    console.error(`${scriptPath} does not export solve(text)`);
    process.exit(1);
  }

  const text = fs.readFileSync(inputPath, 'utf-8');
  const answer = problemModule.solve(text);
  console.log(answer);
}

main();
