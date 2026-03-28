const input = require('fs').readFileSync(0, 'utf8');
const [a, b] = input.trim().split(/\s+/).map(Number);

console.log(parseInt(a + b));
console.log(parseInt(a - b));
console.log(parseInt(a * b));
console.log(parseInt(a / b));
console.log(parseInt(a % b));
