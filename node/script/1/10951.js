const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/).map(Number);
let a, b;

while (input.length > 1) {
  a = input.shift();
  b = input.shift();
  console.log(a + b);
}
