const fs = require('fs');
const input = fs
  .readFileSync(0, 'utf8')
  .trim()
  .split(/\s+/)
  .map((v) => v % 42);

console.log(new Set(input).size);
