const fs = require('fs');
const input = Number(fs.readFileSync(0, 'utf8').trim());

let line = '';

for (let i = 1; i <= input; i++) {
  for (let j = 1; j <= input; j++) {
    if (j + i > input) line += '*';
    else line += ' ';
  }
  console.log(line);
  line = '';
}
