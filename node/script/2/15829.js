const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().toString().split(/\s+/);

const cn = 96;

const r = 31;
const M = 1234567891;

const str = input[1];
let hash = 0;
// r^i 값을 담는 변수 (초기값은 r^0 = 1)
let rPower = 1;

for (let i = 0; i < input[0]; i++) {
  const s = str.charCodeAt(i) - cn;

  // hash = hash + a_i * r^i (mod M)
  hash = (hash + s * rPower) % M;

  // 다음 항을 위한 r^(i+1) = r^i * r (mod M)
  rPower = (rPower * r) % M;
}

console.log(hash);

/*
const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/);

const L = Number(input[0]);
const s = input[1];

const r = 31n;                // BigInt literal (n 붙이기)
const M = 1234567891n;

let hash = 0n;
let rPower = 1n;

for (let i = 0; i < L; i++) {
  const a_i = BigInt(s.charCodeAt(i) - 96);
  hash = (hash + a_i * rPower) % M;
  rPower = (rPower * r) % M;
}

// BigInt를 그냥 출력해도 되지만, Number로 변환해서 출력할 수도 있음
console.log(Number(hash));
*/
