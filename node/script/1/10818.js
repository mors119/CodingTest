const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/).map(Number);

let max = input[1];
let min = input[1];

for (let i = 2; i <= input[0]; i++) {
  if (max < input[i]) max = input[i];
  if (min > input[i]) min = input[i];
}

console.log(min, max);
