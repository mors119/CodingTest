const fs = require('fs');
const [A, B, V] = fs.readFileSync(0, 'utf8').trim().split(/\s+/).map(Number);

// ---- Time over ----
// let i = 0;
// let rs = 0;

// while (rs < V) {
//   rs += A;
//   i++;
//   if (rs >= V) break;
//   rs -= B;
// }

// A는 올라감. B는 내려감. V는 목표
// 한 번 올라가면 한 번 내려감. (A - B)
// 목표에 도달하면 내려가지 않음. (마지막은 -B하지 않음.) V - B
// 총 몇 번에 도달하는 가?
// V - B / A - B
console.log(Math.ceil((V - B) / (A - B)));

// ceil(x/y) = floor((x + y - 1)/y)이므로
// console.log(Math.floor((V - B + (A - B) - 1) / (A - B)));
