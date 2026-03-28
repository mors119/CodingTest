const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/);
const n = parseInt(input.pop()) - 1;

console.log(input[0].substring(n, n + 1));
