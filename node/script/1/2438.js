// console.log()가 자동 줄바꿈이 된다는 걸 생각하기.
// 파이썬 print()와 java system.out.println()과 동일
const n = Number(require('fs').readFileSync(0, 'utf8').trim());

let rs = '';

for (let i = 1; i <= n; i++) {
  for (let j = 1; j <= i; j++) {
    rs += '*';
  }
  rs += '\n';
}

console.log(rs);
