package algorithm.stringSearch;
// javac algorithm/stringSearch/BruteForceMethod.java
// java algorithm/stringSearch/BruteForceMethod

import java.io.IOException;
import java.util.Scanner;

// 일치하지 않으면 자리를 한칸 옮겨서 처음부터 검사 
class BruteForceMethod {
    static int bfMatch(String txt, String pat) {
        int pt = 0; // pointer txt
        int pp = 0; // pointer pat

        while (pt != txt.length() && pp != pat.length()) {
            System.out.println(txt + " " + pp);
            if (txt.charAt(pt) == pat.charAt(pp)) {
                System.out.println("+");
                pt++;
                pp++;
            } else {
                System.out.println("-");
                pt = pt - pp + 1;
                pp = 0;
            }
            System.out.printf(String.format("%%%ds\n", pt), pat);
        }
        if (pp == pat.length()) {
            return pt - pp;
        }
        return -1;
    }

    static int bfMatchLast(String txt, String pat) {
        int txtLen = txt.length();
        int patLen = pat.length();

        if (patLen == 0) return txtLen;
        if (patLen > txtLen) return -1;

        // 뒤에서부터 시작 가능한 위치
        int start = txtLen - patLen;

        while (start >= 0) {
            int pt = start + patLen - 1; // 현재 비교할 txt 위치
            int pp = patLen - 1;         // 현재 비교할 pat 위치

            while (pp >= 0 && txt.charAt(pt) == pat.charAt(pp)) {
                pt--;
                pp--;
            }

            // pp가 -1이면 패턴 전체가 일치했다는 뜻
            if (pp < 0) {
                return start;
            }

            start--;
        }

        return -1;
    }


    public static void main(String[] args) throws IOException { 
        Scanner stdIn = new Scanner(System.in);

        System.out.print("Text: ");
        // String s1 = stdIn.next();
        String s1 = "textpointer";

        System.out.print("Pattern: ");
        // String s2 = stdIn.next();
        String s2 = "poin";

        int idx = bfMatchLast(s1, s2);

        if(idx == - 1) System.out.println("일치하는 패턴 없음");
        else {
            int len = 0;
            for (int i = 0; i < idx; i++) {
                len += s1.substring(i, i + 1).getBytes().length;
            }
            len += s2.length();

            System.out.println((idx + 1) + "번째 문자부터 일치");
            System.out.println("텍스트: " + s1);
            System.out.printf(String.format("패  턴: %%%ds\n", len), s2);
        }
    }
}
