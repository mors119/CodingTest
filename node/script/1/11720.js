const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/);

console.log(
  input[1].split('').reduce((a, c) => {
    return (a += Number(c));
  }, 0),
);
