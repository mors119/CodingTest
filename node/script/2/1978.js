const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/);

let rs = 0;
for (let i = 1; i <= input[0]; i++) {
  let a = 0;
  for (let j = 1; j <= input[i]; j++) {
    if (input[i] % j == 0) a += 1;
  }
  if (a == 2) rs += 1;
  a = 0;
}
console.log(rs);
