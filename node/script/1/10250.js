const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/);

const I = input[0];
const H = input.filter((_, i) => i % 3 == 1);
const W = input.filter((_, i) => i % 3 == 2);
const N = input.filter((_, i) => i % 3 == 0 && i != 0);

for (let i = 0; i < I; i++) {
  let array = [];
  for (let j = 1; j <= W[i]; j++) {
    const formattedNum = String(j).padStart(2, '0');
    for (let k = 1; k <= H[i]; k++) {
      array.push(k + formattedNum);
    }
  }
  console.log(array[N[i] - 1]);
}
