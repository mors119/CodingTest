const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/);

const num = input.slice(1).sort().map(Number);

num.forEach((v) => console.log(v));
