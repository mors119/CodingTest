const fs = require('fs');
const input = fs
  .readFileSync(0, 'utf8')
  .trim()
  .split(/\s+/)
  .map(Number)
  .map(String);

let i = 0;

while (input.length) {
  if (input[i] == '0') break;

  let l = input[i].length - 1;
  let s = input[i].split('');

  for (let j = 0; j < input[i].length; j++) {
    if (Number(s[j]) !== Number(s[l])) {
      input[i] = 'no';
      break;
    }
    l--;
  }

  if (input[i] !== 'no') input[i] = 'yes';

  console.log(input[i]);
  i++;
}
