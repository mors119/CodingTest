package algorithm.recursive;
// javac algorithm/recursive/Recur.java
// java algorithm/recursive/Recur

import algorithm.stackQueue.Stack;
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

    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);

        System.out.println("정수를 입력: ");
        int x = stdIn.nextInt();

        // recur2(x);

        memo = new String[x + 2];
        recurMemo(x);

        System.out.println("반복 횟수: " + cnt);
    }
}
