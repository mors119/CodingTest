const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/);

let rs = 0;

for (let i = 1; i <= input[0]; i++) {
  let c = input[i].split('X').filter((v) => v != '');
  for (let j = 0; j < c.length; j++) {
    rs += c[j]
      .split('')
      .map((v, i) => (v = i + 1))
      .reduce((pre, cur) => {
        return (pre += Number(cur));
      }, 0);
  }
  console.log(rs);
  rs = 0;
}
