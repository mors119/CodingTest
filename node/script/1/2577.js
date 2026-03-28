const fs = require('fs');
const input = fs
  .readFileSync(0, 'utf8')
  .trim()
  .split(/\s+/)
  .reduce((a, c) => {
    return (a *= c);
  });

for (let i = 0; i <= 9; i++) {
  console.log(
    input
      .toString()
      .split('')
      .filter((v) => v == i).length,
  );
}
