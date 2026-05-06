package algorithm.stringSearch;
// javac algorithm/stringSearch/KMP.java
// java algorithm/stringSearch/KMP

import java.io.IOException;
import java.util.Scanner;

// KMP: Knuth-Morris-Pratt 문자열 검색 알고리즘
// 중복된 문자열을 파악해서 미리 검사된 문자열은 제외하기
public class KMP {

    static int kmpMatch(String txt, String pat) {
        int txtPointer = 1; // 패턴 내부에서 skip 테이블을 만들 때 사용하는 포인터
        int patPointer = 0; // 패턴의 접두사 위치를 가리키는 포인터

        int[] skip = new int[pat.length() + 1];

        // 1. skip 테이블 만들기
        skip[txtPointer] = 0;

        while (txtPointer != pat.length()) {
            if (pat.charAt(txtPointer) == pat.charAt(patPointer)) {
                skip[++txtPointer] = ++patPointer;
            } else if (patPointer == 0) { // 처음부터 틀렸을 때
                skip[++txtPointer] = patPointer;
            } else { // skip표 위치로 이동
                patPointer = skip[patPointer];
            }
        }

        /*
            패턴: A B A B C A B A B
            인덱: 0 1 2 3 4 5 6 7 8

            skip:0 0 1 2 0 1 2 3 4
            이런 테이블을 만듦
        */

        // 2. 실제 문자열 검색
        txtPointer = 0; // txt를 읽는 포인터
        patPointer = 0; // pat를 읽는 포인터

        while (txtPointer != txt.length() && patPointer != pat.length()) {
            if (txt.charAt(txtPointer) == pat.charAt(patPointer)) {
                txtPointer++;
                patPointer++;
            } else if (patPointer == 0) {
                txtPointer++;
            } else {
                patPointer = skip[patPointer];
            }
        }

        if (patPointer == pat.length()) {
            return txtPointer - patPointer;
        }

        return -1;
    }

    public static void main(String[] args) throws IOException { 
        Scanner stdIn = new Scanner(System.in);

        System.out.print("Text: ");
        // String s1 = stdIn.next();
        String s1 = "ABABDABACDABABCABAB";

        System.out.print("Pattern: ");
        // String s2 = stdIn.next();
        String s2 = "ABABCABAB";

        int idx = kmpMatch(s1, s2);

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
