const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/).map(Number);

let temp = 0;
let n = 0;
for (let i = 0; i < input.length; i++) {
  if (input[i] > temp) {
    temp = input[i];
    n = i + 1;
  }
}
console.log(`${temp}\n${n}`);
