package algorithm.common;
// javac algorithm/common/Max3.java
// java algorithm/common/Max3

import java.io.IOException;
import java.util.Scanner;

public class Max3 {
    public static void main(String[] args) throws IOException {
        Scanner stdIn = new Scanner(System.in);

        System.out.println("세자리의 정수의 최댓값 구하기");
        System.out.println("a의 값:");
        // String str = stdIn.next(); 문자열 받기
        // String str = stdIn.nextLine(); 문자열 1줄 받기
        int a = stdIn.nextInt();
        System.out.println("b의 값:");
        int b = stdIn.nextInt();
        System.out.println("c의 값:");
        int c = stdIn.nextInt();

        int max = a;
        if (b > max) max = b;
        if (c > max) max = c;

        System.out.println("최대값:" + max);
    }
}
