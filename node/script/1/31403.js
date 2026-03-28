const fs = require('fs');
const [a, b, c] = fs.readFileSync(0, 'utf8').trim().split(/\s+/).map(Number);
console.log(a + b - c);
const ab = String(a) + String(b);
console.log(Number(ab) - c);
