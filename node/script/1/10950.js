const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/).map(Number);
const rep = input.shift();
let a, b;

for (let i = 1; i <= rep; i++) {
  a = input.shift();
  b = input.shift();
  console.log(a + b);
}
