const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/);

const time = new Date(2025, 10, 10, input[0], input[1]);
const a = new Date(2025, 10, 10, 0, 45);

const b = new Date(time - a);

console.log(b.getUTCHours(), b.getUTCMinutes());
