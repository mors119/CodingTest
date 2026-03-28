const fs = require('fs');
const s = fs.readFileSync(0, 'utf8').trim().split('');
const a = 'abcdefghijklmnopqrstuvwxyz'.split('');

for (let i = 0; i < s.length; i++) {
  for (let j = 0; j < a.length; j++) {
    if (s[i] == a[j]) a[j] = i;
  }
}
let arr = a.map((v) => {
  if (typeof v == 'string') return -1;
  else return v;
});

console.log(arr.join(' '));
