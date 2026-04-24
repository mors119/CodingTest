package algorithm.common;
// javac algorithm/common/PrintStar.java
// java algorithm/common/PrintStar


import java.io.IOException;
import java.util.Scanner;

public class PrintStar {

    static void printStar1 (int x, int y) {
        // x를 y줄 만큼 출력
        for(int i = 0; i < y; i++) {
            for(int j = 0; j < x; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        if(x % y != 0) {
            System.out.println();
        }
    }

    static void printStar2 (int x, int y) {
        if (x > y) return;
        
        // y 개수를 출력하되 한 줄에 x개씩
        for(int i = 0; i < y / x; i++) {
            System.out.println("*".repeat(x));
            int rest = y % x;
            if(rest != 0) {
                System.err.println("*".repeat(rest));
            }
        }
        if(x % y != 0) {
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner stdIn = new Scanner(System.in);
        
        int x, y; 

        do { 
            System.out.println("x, y의 값:");
            x = stdIn.nextInt();
            y = stdIn.nextInt();
        } while (x <= 0 && (y <= 0 || y > x));
        
        printStar1(x, y);

        printStar2(x, y);
    }
}
