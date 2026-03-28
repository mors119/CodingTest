const fs = require('fs');
const input = fs
  .readFileSync(0, 'utf8')
  .trim()
  .split('\n')
  .map((v) => v.trim().split(/\s+/).map(Number));

let i = 0;

while (i < input.length - 1) {
  let max = Math.max(input[i][0], input[i][1], input[i][2]);
  let min = Math.min(input[i][0], input[i][1], input[i][2]);
  let mid = 0;
  for (let j = 0; j < 3; j++) {
    if (input[i][j] != max && input[i][j] != min) {
      mid = input[i][j];
    }
  }
  if (min ** 2 + mid ** 2 == max ** 2) {
    console.log('right');
  } else {
    console.log('wrong');
  }
  i++;
}
