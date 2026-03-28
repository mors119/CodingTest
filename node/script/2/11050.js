const fs = require('fs');
const [n, k] = fs.readFileSync(0, 'utf8').trim().split(/\s+/).map(Number);

/**
                1
              1   1
            1   2   1
          1   3   3   1
        1   4   6   4   1
      1   5  10  10   5   1
 */
// 이항계수(조합): n명 중에서 k명을 고르는 방법이 몇 가지인가?
// 아무것도 안 뽑는 경우는 1가지, 모두 다 뽑는 경우도 1가지, 마지막을 뽑거나 뽑지 않는 경우

function buildPascalTriangle(rows) {
  // triangle
  const C = [];

  // 0번째 줄부터 rows-1번째 줄까지 채움
  for (let n = 0; n < rows; n++) {
    // 현재 줄을 저장
    const row = [];
    // 이 줄의 k번째 값을 채운다 (0부터 n까지)
    for (let k = 0; k <= n; k++) {
      // 규칙 1: 양 끝은 항상 1
      // (맨 처음 k === 0, 맨 끝 k === n)
      if (k === 0 || k === n) {
        row[k] = 1;
      } else {
        // 규칙 2: 나머지는 "왼쪽 위 두 칸"을 더한다
        // 바로 위 줄은 triangle[n - 1]
        // 그 줄에서 왼쪽 위: triangle[n - 1][k - 1]
        //           오른쪽 위: triangle[n - 1][k]
        row[k] = C[n - 1][k - 1] + C[n - 1][k];
      }
    }
    C[n] = row;
  }
  return C;
}

// 이항계수 nCk (조합) 구하기 n - 전체 개수 k - 뽑을 개수
function combination(n, k) {
  // 파스칼 삼각형을 n+1줄까지 만든다 (0~n줄)
  const triangle = buildPascalTriangle(n + 1);

  // n번째 줄의 k번째 값이 바로 nCk
  return triangle[n][k];
}

console.log(combination(n, k));
