package algorithm.common;
// javac algorithm/common/CardConv.java
// java algorithm/common/CardConv

import java.io.IOException;
import java.util.Scanner;

class CardConv {
    // x를 r진수로 변환
    static int CardConv (int x, int r, char[] d) {
        int digits = 0;
        String dchar = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        do {
            System.out.println(r + " )  " + x + (digits > 0 ? "         --- " + (x % r): ""));
            System.out.println("  ------------");
            d[digits++] = dchar.charAt(x % r); // 나머지를 저장
            x /= r;
            if(x == 0) System.out.println("               --- " + (x % r));
        } while (x != 0);
        

        for (int i = 0; i < digits / 2; i++) {
            char t = d[i];
            d[i] = d[digits - i - 1];
            d[digits - i - 1] = t;
        }

        return digits;
    }

    public static void main(String[] args) throws IOException {
        Scanner stdIn = new Scanner(System.in);
        int retry;
        int num;
        int cd;
        int dno;
        char[] cno = new char[32];

        System.out.println("10진수를 기수로 변환");
        do { 
            do { 
                System.out.println("정수: ");
                num = stdIn.nextInt();
            } while (num < 0);
            
            do { 
                System.out.println("변환 할 진수: ");
                cd = stdIn.nextInt();
            } while (num < 0);

            dno = CardConv(num, cd, cno);
            for (int j = 0; j < dno; j++) {
                System.out.print(cno[j]);
            }            

            System.out.println();

            System.out.println("한 번 더? 1: 예 / 0: 아니오 (숫자를 입력하세요.)");
            retry = stdIn.nextInt();
        } while (retry == 1);
    }
}
