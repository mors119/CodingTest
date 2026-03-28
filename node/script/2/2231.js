const fs = require('fs');
const input = Number(fs.readFileSync(0, 'utf8').trim());

let rs = 0;

for (let i = 1; i < input; i++) {
  let a = i
    .toString()
    .split('')
    .map(Number)
    .reduce((a, c) => (a += c));

  if (i + a == input) {
    rs = i;
    break;
  }
}

console.log(rs);
