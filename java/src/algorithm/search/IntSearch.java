package algorithm.search;
// javac algorithm/search/IntSearch.java
// java algorithm/search/IntSearch

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class IntSearch {
    // 선형탐색
    static void seqSearch(int[] x, int num, int key) {
        int idx = -1;
        for (int i = 0; i < num; i++) {
            if(x[i] == key) {
                idx = i;
                break;
            }
        }
        
        if(idx != -1) System.out.println("값이 x[" + idx + "]에 있습니다.");
        else System.out.println("숫자 없음");
    }

    // 이진 탐색
    static void binSearch(int[] x, int num, int key) {
        int pl = 0;
        int pr = num - 1;
        int idx = -1;

        do { 
            int pc = (pl + pr) / 2;
            if (x[pc] == key) {
                idx = pc;
                break;
            } else if (x[pc] < key) {
                pl = pc + 1;
            } else {
                pr = pc - 1;
            }
        } while (pl <= pr);

        // 이진 탐색 구현
        if(idx == -1) {
            System.out.println("숫자 없음");
        } else {
            System.out.print("  |");
            for(int i = 0; i < num; i++) {
                System.out.printf("%3d", i);
            }
            System.out.println();
            System.out.println("--------------------------");
            for (int i = 0; i < num; i++) {           
                System.out.print("  | ");
                for (int j = 0; j < num; j++) {
                    if(j == i) {
                        if(key == x[i]) System.out.print("[*]");
                        else System.out.print(" * ");
                        }
                    else System.out.print("   ");
                }
                System.out.println();
                System.out.print(i + " |");
                for(int j = 0; j < num; j++) {
                    System.out.printf("%3d", x[j]);
                }
                System.out.println();
            }

            System.out.println();
            System.out.println("값이 x[" + idx + "]에 있습니다.");
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner stdIn = new Scanner(System.in);
        System.out.println("요소의 수: ");
        int num = stdIn.nextInt();
        int[] x = new int[num];

        System.out.print("x[0] = ");
        x[0] = stdIn.nextInt();

        for (int i = 1; i < num; i++) {
            do{
                System.out.print("x[" + i + "] = ");
                x[i] = stdIn.nextInt();
            } while(x[i] < x[i - 1]);
        }

        System.out.println("검색할 값: ");
        int key = stdIn.nextInt();

        // seqSearch(x, num, key);

        // binSearch(x, num, key);

        // java.util binSearch 이용하기
        int idx = Arrays.binarySearch(x, key);
        if(idx != -1) System.out.println("값이 x[" + idx + "]에 있습니다.");
        else System.out.println("숫자 없음. 삽입값: " + key);
    }
}
