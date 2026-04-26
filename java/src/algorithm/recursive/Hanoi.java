package algorithm.recursive;
// javac algorithm/recursive/Hanoi.java
// java algorithm/recursive/Hanoi

import algorithm.stackQueue.Stack;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


class Hanoi {

    static Map<Integer, Character> map = new HashMap<>() {{
        put(1, 'A');
        put(2, 'B');
        put(3, 'C');
    }};

    static void move(int no, int x, int y) {

        if (no > 1) move(no - 1, x, 6 - x- y);
        // System.out.println("원반 [" + no + "] 을(를) [" + x + "] 번 기둥에서 [" + y + "] 번 기둥으로 옮김");
        System.out.println("원반 [" + no + "] 을(를) [" + map.get(x) + "] 번 기둥에서 [" + map.get(y) + "] 번 기둥으로 옮김");

        if (no > 1) move(no - 1, 6 - x - y, y);
    }

    // 비재귀적인 방법으로 변환
    enum State {
        LEFT, PRINT, RIGHT
    }

    static class Frame {
        int n;       // 옮길 원반 번호
        int x;       // 출발 기둥
        int y;       // 도착 기둥
        State state; // 현재 실행 단계

        public Frame(int n, int x, int y, State state) {
            this.n = n;
            this.x = x;
            this.y = y;
            this.state = state;
        }
    }

    static void moveInteractive(int n, int x, int y) {
        // 하노이 이동 횟수는 2^n - 1이므로, 학습용이면 넉넉하게 잡는다.
        // n이 크면 이 방식은 용량 부족 가능성이 있으므로 ArrayDeque가 더 좋다.
        Stack.EStack<Frame> stk = new Stack.EStack<>((int) Math.pow(2, n + 1));

        stk.push(new Frame(n, x, y, State.LEFT));

        while (!stk.isEmpty()) {
            Frame current = stk.pop();

            if (current.n <= 0) {
                continue;
            }

            // 전체 합(6) - 현재 기둥 - 현재 기둥 = 남은 기둥
            int middle = 6 - current.x - current.y; 

            switch (current.state) {
                case LEFT -> {
                    // 원래 재귀:
                    // move(n - 1, x, middle);
                    // print(n);
                    // move(n - 1, middle, y);

                    // 스택은 나중에 넣은 것이 먼저 실행되므로
                    // "돌아올 위치"를 먼저 넣고, 실제 먼저 실행할 작업을 나중에 넣는다.

                    stk.push(new Frame(current.n, current.x, current.y, State.PRINT));
                    stk.push(new Frame(current.n - 1, current.x, middle, State.LEFT));
                }

                case PRINT -> {
                    System.out.println(
                        "원반 [" + current.n + "] 을(를) [" +
                        map.get(current.x) + "] 번 기둥에서 [" +
                        map.get(current.y) + "] 번 기둥으로 옮김"
                    );

                    stk.push(new Frame(current.n, current.x, current.y, State.RIGHT));
                }

                case RIGHT -> {
                    stk.push(new Frame(current.n - 1, middle, current.y, State.LEFT));
                }
            }
        }
    }


    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);

        System.out.println("원반의 개수: ");
        int n = stdIn.nextInt();
        // move(n, 1, 3);

        moveInteractive(n, 1, 3);
    }
}
