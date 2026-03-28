const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/);

const str = input.filter((_, i) => i % 2 == 0 && i != 0);
const num = input.filter((_, i) => i % 2 == 1);
let rs = [];

for (let i = 0; i < input[0]; i++) {
  let s = str[i].split('');
  for (let j = 0; j < s.length; j++) {
    for (let k = 0; k < num[i]; k++) {
      rs.push(s[j]);
    }
  }
  console.log(rs.join(''));
  rs = [];
}
