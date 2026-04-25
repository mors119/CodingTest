package algorithm.recursive;
// javac algorithm/recursive/EuclidGCD.java
// java algorithm/recursive/EuclidGCD

import java.io.IOException;
import java.util.Scanner;

// 유클리드 호재법 (재귀적으로 최대 공약수 구하기)
class EuclidGCD {

    static int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }

    // 재귀 없이 최대 공약수 구하기
    // gcd(a, b) = gcd(b, a mod b)
    // a와 b의 최대공약수 = b와 (a % b)의 최대공약수
    static int gcdFn(int x, int y) {
        int a = x;
        int b = y;

        while(b != 0) {
            int r = a % b; // 나머지
            a = b;
            b = r;
        }
        return a;
    }
    public static void main(String[] args) throws IOException {
        Scanner stdIn = new Scanner(System.in);

        System.out.println("정수를 입력하세요:");
        int x = stdIn.nextInt();
        System.out.println("정수를 입력하세요:");
        int y = stdIn.nextInt();

        System.out.println(x + "와" + y + "의 최대 공약수는 " + gcd(x, y));

        System.out.println(gcdFn(x, y));
    }
}
