package algorithm.common;
// javac algorithm/common/SumFor.java
// java algorithm/common/SumFor


import java.io.IOException;
import java.util.Scanner;

public class SumFor {
    static int sumFor1 (int a) {
        int sum = 0;
        for(int i = 0; i <= a; i++)
            sum += i;
        return sum;
    }

    public static void main(String[] args) throws IOException {
        Scanner stdIn = new Scanner(System.in);
        int a;

        System.out.println("1부터 입력된 정수의 합 구하기");

        do { 
            System.out.println("a의 값:");
            a = stdIn.nextInt();
        } while (a <= 0);

        System.out.println("SUM:" + sumFor1(a));
    }
}
