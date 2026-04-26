package algorithm.recursive;
// javac algorithm/recursive/Recur.java
// java algorithm/recursive/Recur

import algorithm.stackQueue.Stack;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;


class Recur {
    static int cnt = 0;
    /*
        recur(3)
        ├─ recur(2)
        │  ├─ recur(1)
        │  │  ├─ recur(0)
        │  │  ├─ print 1
        │  │  └─ recur(-1)
        │  ├─ print 2
        │  └─ recur(0)
        ├─ print 3
        └─ recur(1)
            ├─ recur(0)
            ├─ print 1
            └─ recur(-1)
    반복 수: 9
    */

    static void recur(int n) {
        cnt++;
        if(n > 0) {
            recur(n - 1);
            System.out.println(n);
            recur(n - 2);
        }
    }

    static void recur1(int n) {
        while (n > 0) {
            cnt++;
            recur(n - 1);
            System.out.println(n);
            n = n - 2;
        }
    }

    // 재귀를 스택으로 변환
    // 원래 재귀는 JVM call stack에 n을 저장한다.
    // recur2는 직접 만든 Stack.IntStack에 n을 저장한다.
    static void recur2(int n) {
        Stack.IntStack s = new Stack.IntStack(n);

        while (true) { 
            cnt++;
            if (n > 0) {
                s.push(n);
                n = n - 1;
                continue;
            }
            if(s.isEmpty() != true) {
                n = s.pop();
                System.out.println(n);
                n = n - 2;
                continue;
            }
            break;
        }
    }

    // 메모화 memorization
    // 이미 계산한 내용은 다시 반복하지 않음. 
    // 시간 복잡도 측면에서 앞선 방법들 보다 훨씬 우위에 있음.
    static String[] memo;

    static void recurMemo(int n) {
        cnt++;
        if (memo[n + 1] != null) System.out.println(memo[n + 1]); // n + 1은 n이 음수로 내려가는 것을 방지
        else {
            if(n > 0) {
                recurMemo(n - 1);
                System.out.println(n);
                recurMemo(n - 2);
                // recur(n - 1); System.out.println(n); recur(n - 2);를 문자열로 변환
                memo[n + 1] = memo[n] + n + "\n" + memo[n - 1];
                // memo[n + 1] = recur(n)의 출력 결과
                // memo[n] = recur(n - 1)의 출력 결과
                // n + "\n" = 현재 n 출력
                // memo[n - 1] = recur(n - 2)의 출력 결과
            } else {
                memo[n + 1] = "";
            }
        }
    }

    // 비재귀를 스택으로 구현
    /*
    recur(3)
    ├─ state 0: recur(2)
    │  ├─ state 0: recur(1)
    │  │  ├─ state 0: recur(0) → 종료
    │  │  ├─ state 1: print 1
    │  │  └─ state 2: recur(-1) → 종료
    │  ├─ state 1: print 2
    │  └─ state 2: recur(0) → 종료
    ├─ state 1: print 3
    └─ state 2: recur(1)
        ├─ state 0: recur(0) → 종료
        ├─ state 1: print 1
        └─ state 2: recur(-1) → 종료
    */
    static class Frame {
        int n;
        int state; // 0: left, 1: print, 2: right

        Frame(int n, int state) {
            this.n = n;
            this.state = state;
        }
    }

    // 불필요한 락이나 성능 저하 가능성 있으므로 Deque을 쓰는게 좋음.
    static void recurIterative(int n) {
        java.util.Stack<Frame> stack = new java.util.Stack<>();
        stack.push(new Frame(n, 0));

        while (!stack.isEmpty()) {
            Frame current = stack.pop();

            if (current.n <= 0) continue;

            switch (current.state) {
                case 0 -> {
                    // 1단계: recur(n-1) 호출
                    stack.push(new Frame(current.n, 1));      // 돌아올 위치
                    stack.push(new Frame(current.n - 1, 0));  // recur(n-1)
                }
                case 1 -> {
                    // 2단계: print
                    System.out.println(current.n);
                    stack.push(new Frame(current.n, 2));      // 다음 상태
                }
                case 2 -> // 3단계: recur(n-2)
                    stack.push(new Frame(current.n - 2, 0));
                default -> {
                }
            }
        }
    }

    // 실무적으로 적합
    static void recurDequeIterative(int n) {
        Deque<Frame> stack = new ArrayDeque<>();
        stack.push(new Frame(n, 0));

        while (!stack.isEmpty()) {
            Frame current = stack.pop();

            if (current.n <= 0) continue;

            switch (current.state) {
                case 0 -> {
                    // 1단계: recur(n-1) 호출
                    stack.push(new Frame(current.n, 1));      // 돌아올 위치
                    stack.push(new Frame(current.n - 1, 0));  // recur(n-1)
                }
                case 1 -> {
                    // 2단계: print
                    System.out.println(current.n);
                    stack.push(new Frame(current.n, 2));      // 다음 상태
                }
                case 2 -> // 3단계: recur(n-2)
                    stack.push(new Frame(current.n - 2, 0));
                default -> {
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);

        System.out.println("정수를 입력: ");
        int x = stdIn.nextInt();

        // recur2(x);

        memo = new String[x + 2];
        recurMemo(x);

        System.out.println("반복 횟수: " + cnt);

        recurDequeIterative(x);
    }
}
