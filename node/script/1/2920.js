const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/).map(Number);

const ascending = Array.from({ length: input.length })
  .fill(0)
  .map((v, i) => (v = i + 1))
  .filter((v, i) => input[i] == v);
const descending = Array.from({ length: input.length })
  .fill(0)
  .map((v, i) => (v = input.length - i))
  .filter((v, i) => input[i] == v);

if (ascending.length == input.length && descending.length == 0)
  console.log('ascending');
else if (descending.length == input.length && ascending.length == 0)
  console.log('descending');
else console.log('mixed');
