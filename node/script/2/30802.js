const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/);

const N = input[0];
const T = input[input.length - 2];
const P = input[input.length - 1];
const S = input.filter((_, i) => i != 0 && i < input.length - 2);

console.log(
  S.map((v) => Math.ceil(v / T)).reduce((a, c) => {
    return (a += c);
  }, 0),
);
console.log(Math.floor(N / P), N % P);
