const fs = require('fs');
const [a, b] = fs.readFileSync(0, 'utf8').trim().split(/\s+/).map(Number);

let gcd = 0;
let lcm = 0;

// 최대 공약수
for (let i = 1; i <= Math.min(a, b); i++) {
  if (a % i === 0 && b % i === 0) {
    gcd = i;
  }
}

// 최소 공배수
for (let i = 1; ; i++) {
  if (i % a === 0 && i % b === 0) {
    lcm = i;
    break;
  }
}

console.log(gcd);
console.log(lcm);
