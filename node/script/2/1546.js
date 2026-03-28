const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/).map(Number);

const n = input[0];
const scores = input.slice(1);
const max = scores.reduce((a, c) => (a < c ? c : a), 0);

let sum = 0;

for (let i = 0; i < n; i++) {
  sum += (scores[i] / max) * 100;
}

console.log(sum / n);
