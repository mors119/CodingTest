package algorithm.common;
// javac algorithm/common/Median.java
// java algorithm/common/Median


import java.io.IOException;
import java.util.Scanner;

public class Median {
    static int med3 (int a, int b, int c) {
        if(a >= b) 
            if (b >= c)
                return b;
            else if (a <= c)
                return a;
            else
                return c;
        else if (a > c)
            return a;
        else if (b > c)
            return c;
        else 
            return b;
    }


    static int med3_2(int a, int b, int c) {
        if((b >= a && c <= a) || (b <= a && c >= a)) 
            return a;
        else if((b < a && c < b) || (b > a && c > b)) 
            return b;
        else
            return c;
    }

    public static void main(String[] args) throws IOException {
        Scanner stdIn = new Scanner(System.in);

        System.out.println("세자리의 정수의 최댓값 구하기");
        System.out.println("a의 값:");
        int a = stdIn.nextInt();
        System.out.println("b의 값:");
        int b = stdIn.nextInt();
        System.out.println("c의 값:");
        int c = stdIn.nextInt();

        System.out.println("중간값:" + med3(a, b, c));
    }
}
