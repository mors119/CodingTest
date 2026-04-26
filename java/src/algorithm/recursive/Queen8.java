package algorithm.recursive;
// javac algorithm/recursive/Queen8.java
// java algorithm/recursive/Queen8

// 8퀸 문제: 8*8의 판에 8개의 퀸이 각각 공격받지 않는 형태를 만드는 게 목적
class Queen8 {
    static final int SIZE = 8;
    static int[] pos = new int[SIZE];
    
    static void numPrint() {
        for(int i = 0; i < SIZE; i++) {
            System.out.printf("%2d", pos[i]);
        }
        System.out.println();
    }

    static void print() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (pos[row] == col) {
                    System.out.print(" Q");
                } else {
                    System.out.print(" .");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    // Queen이 공격받지 않는지 확인하는 로직
    static boolean isSafe(int row) {
        for (int prev = 0; prev < row; prev++) {
            if (pos[prev] == pos[row]) {
                return false;
            }

            if (Math.abs(row - prev) == Math.abs(pos[row] - pos[prev])) {
                return false;
            }
        }

        return true;
    }

    static void set(int row) {
        for (int col = 0; col < SIZE; col++) {
            pos[row] = col;

            if (isSafe(row)) {
                if (row == 7) {
                    print();
                } else {
                    set(row + 1);
                }
            }
        }
    }

    // true면 해당 열에 이미 퀸이 있다는 뜻
    static boolean[] flagA = new boolean[SIZE];  // 각 열에 퀸 배치 확인
    static boolean[] flagB = new boolean[SIZE * 2 - 1]; // / 방향 대각선 확인: i + j
    static boolean[] flagC = new boolean[SIZE * 2 - 1]; // \ 방향 대각선 확인: i - j + 7

    static boolean canPlace(int row, int col) {
        return !flagA[col]
            && !flagB[row + col]
            && !flagC[row - col + SIZE - 1];
    }

    static void place(int row, int col) {
        pos[row] = col;
        flagA[col] = true;
        flagB[row + col] = true;
        flagC[row - col + SIZE - 1] = true;
    }

    static void remove(int row, int col) {
        flagA[col] = false;
        flagB[row + col] = false;
        flagC[row - col + SIZE - 1] = false;
    }
    
    static void numSet(int i) {
        for (int j = 0; j < SIZE; j++) {
            if (canPlace(i, j)) {
                pos[i] = j;

                if (i == 7) {
                    numPrint();
                } else {
                    place(i, j);
                    numSet(i + 1);
                    remove(i, j);
                }
            }
        }
    }

    static void solveIterative() {
        int row = 0;
        int col = 0;

        while (row >= 0) {
            boolean placed = false;

            while (col < SIZE) {
                if (canPlace(row, col)) {
                    place(row, col);
                    placed = true;
                    break;
                }

                col++;
            }

            if (placed) {
                if (row == SIZE - 1) {
                    print();

                    // 마지막 행의 퀸을 제거하고 다음 열부터 다시 탐색
                    remove(row, pos[row]);
                    col = pos[row] + 1;
                } else {
                    // 다음 행으로 내려감
                    row++;
                    col = 0;
                }
            } else {
                // 현재 행에 놓을 곳이 없으면 이전 행으로 되돌아감
                row--;

                if (row >= 0) {
                    int prevCol = pos[row];

                    // 이전 행에 놓았던 퀸 제거
                    remove(row, prevCol);

                    // 이전 행에서 다음 열부터 다시 탐색
                    col = prevCol + 1;
                }
            }
        }
    }

    public static void main(String[] args) {
        // set(0);
        numSet(0);
    }
}
