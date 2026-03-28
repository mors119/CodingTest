const [a, b] = require('fs')
  .readFileSync(0, 'utf8')
  .trim()
  .split(/\s+/)
  .map(Number);

if (a > b) console.log('>');
if (a == b) console.log('==');
if (a < b) console.log('<');
