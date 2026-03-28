const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/).map(Number);

let idx = 0;
const T = input[idx++];

let out = [];

// 누적합
function prefixSum(arr) {
  const n = arr.length;
  for (let i = 1; i < n; i++) {
    arr[i] = arr[i] + arr[i - 1];
  }
  return arr;
}

for (let t = 0; t < T; t++) {
  const k = input[idx++];
  const n = input[idx++];

  let arr = Array.from({ length: n }, (_, i) => i + 1);

  for (let floor = 0; floor < k; floor++) {
    prefixSum(arr);
  }

  out.push(String(arr[n - 1]));
}

console.log(out.join('\n'));
