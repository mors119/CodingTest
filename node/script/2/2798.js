const fs = require('fs');
const [N, M, ...C] = fs.readFileSync(0, 'utf8').trim().split(/\s+/);

// 모든 조합 찾기
function getCombinations(arr, k) {
  const result = [];
  // 재귀를 위한 헬퍼 함수
  function backtrack(start, currentCombination) {
    // 종료 조건: 현재 조합의 길이가 k에 도달하면 결과에 추가
    if (currentCombination.length === k) {
      result.push([...currentCombination]); // 깊은 복사본 저장
      return;
    }

    // 현재 인덱스(start)부터 배열 끝까지 탐색
    for (let i = start; i < arr.length; i++) {
      // 1. 현재 원소 추가
      currentCombination.push(arr[i]);
      // 2. 다음 원소는 현재 i+1부터 시작하도록 재귀 호출 (순서 중복 방지)
      backtrack(i + 1, currentCombination);
      // 3. 백트래킹 (현재 원소 제거하여 다른 조합 탐색)
      currentCombination.pop();
    }
  }

  backtrack(0, []); // 0번 인덱스부터 빈 배열로 시작
  return result;
}

const combinations = getCombinations(C, 3);

const numbers = combinations.map((v) =>
  v.reduce((a, c) => {
    return (a += Number(c));
  }, 0),
);

let tmp = 0;
let rs = 0;

for (let i = 0; i < numbers.length; i++) {
  if (numbers[i] > tmp && numbers[i] <= M) {
    tmp = numbers[i];
    rs = numbers[i];
  }
}
console.log(rs);
