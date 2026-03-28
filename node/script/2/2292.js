const fs = require('fs');
const input = Number(fs.readFileSync(0, 'utf8').trim());

const arr = [];
let i = 1;

while (3 * i ** 2 - 3 * i + 1 < input) {
  arr.push(3 * i ** 2 - 3 * i + 1);
  i++;
}

console.log(arr.length + 1);
