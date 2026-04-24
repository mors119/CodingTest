package algorithm.common;
// javac algorithm/common/StarTable.java
// java algorithm/common/StarTable


import java.io.IOException;
import java.util.Scanner;

public class StarTable {

    static void Multi99Table () {
        // 구구단
        for(int i = 1; i <= 9; i++) {
            for(int j = 1; j <= 9; j++) {
                System.out.print(i * j + " ");
            }
            System.out.println();
        }
    }

    static void TriangleLB (int n) {
        System.out.println("----------- LB ---------");

        for (int i = 1; i <= n; i++) {
            for(int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    static void TriangleLU (int n) {
        System.out.println("----------- LU ---------");

        for (int i = n; i > 0; i--) {
            for(int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    static void TriangleRU (int n) {
        System.out.println("----------- RU ---------");

        for (int i = n; i >= 0; i--) {
            for(int j = 0; j < n - i; j++) {
                System.out.print(" ");
            }
            for(int j = i; j > 0; j--) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    static void TriangleRB (int n) {
        System.out.println("----------- RB ---------");

        for (int i = 0; i < n; i++) {
            for(int j = 0; j < n - i; j++) {
                System.out.print(" ");
            }
            for(int j = i; j >= 0; j--) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    static void spira (int n) {
        System.out.println("----------- spira ---------");

        for (int i = 0; i < n; i++) {
            for(int j = (n / 2) - i + 1; j >= 0; j--) {
                System.out.print(" ");
            }
            for(int j = 1 + (i * 2); j > 0; j--) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    static void npira (int n) {
        System.out.println("----------- spira ---------");

        for (int i = 1; i <= n; i++) {
            for(int j = (n / 2) - i + 1; j >= 0; j--) {
                System.out.print(" ");
            }
            for(int j = (i * 2) - 1; j > 0; j--) {
                System.out.print(i);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner stdIn = new Scanner(System.in);
        
        int n; 

        System.out.println("n의 값:");
        n = stdIn.nextInt();
        
        // Multi99Table();
        // TriangleLB(n);
        // TriangleLU(n);
        // TriangleRU(n);
        // TriangleRB(n);
        // spira(n);
        npira(n);
    }
}
