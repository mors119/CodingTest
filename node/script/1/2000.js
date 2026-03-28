function solve(text) {
  const lines = text
    .split(/\r?\n/)
    .map((line) => line.trim())
    .filter(Boolean);

  // TODO: 문제 풀이 작성
  return JSON.stringify(lines);
}

module.exports = { solve };
