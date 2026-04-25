package algorithm.recursive;
// javac algorithm/recursive/Factorial.java
// java algorithm/recursive/Factorial

import java.io.IOException;
import java.util.Scanner;

class Factorial {
    // 팩토리얼: 1부터 양의 정수 n까지의 모든 자연수를 곱한 값
    // n! = n × (n-1)!
    // 0! = 1
    static int factorial(int n) {
        if (n < 0) throw new IllegalArgumentException("n must be >= 0");

        if (n == 0) return 1;

        return n * factorial(n - 1);
    }

    // 재귀 함수 없이 팩토리얼 값 구하기
    static int factorialFn(int n) {
        int rs = 1;
        for(int i = 1; i <= n; i++) {
            rs *= i;
        }
        return rs;
    }

    public static void main(String[] args) throws IOException {
        Scanner stdIn = new Scanner(System.in);

        System.out.println("정수를 입력하세요.:");
        int x = stdIn.nextInt();

        System.out.println(x + "의 팩토리얼은 " + factorial(x));


        System.out.println(factorialFn(x));
    }
}
