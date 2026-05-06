package algorithm.stringSearch;
// javac algorithm/stringSearch/BoyerMoore.java
// java algorithm/stringSearch/BoyerMoore

// 보어-무어
// 텍스트에서 패턴(길이부터 시작해서)을 뒤에서부터 비교하고 틀리면 패턴 길이만큼 점프
public class BoyerMoore {

    static int bmMatch(String txt, String pat) {

        // skip 테이블
        // ASCII 문자 기준
        int[] skip = new int[256];

        // 기본 이동 거리 = 패턴 길이
        for (int i = 0; i < 256; i++) {
            skip[i] = pat.length();
        }

        // 패턴 내부 문자들에 대한 이동 거리 설정
        for (int i = 0; i < pat.length() - 1; i++) {
            skip[pat.charAt(i)] = pat.length() - i - 1;
        }

        // txt를 검사할 위치
        int pt = pat.length() - 1;

        while (pt < txt.length()) {

            int pp = pat.length() - 1;

            int temp = pt;

            // 뒤에서 앞으로 비교
            while (txt.charAt(temp) == pat.charAt(pp)) {

                // 패턴 전부 일치
                if (pp == 0) {
                    return temp;
                }

                temp--;
                pp--;
            }

            // 불일치 발생 시 점프
            pt += Math.max(skip[txt.charAt(pt)], pat.length() - pp);
        }

        return -1;
    }

    public static void main(String[] args) {

        String txt = "HERE IS A SIMPLE EXAMPLE";
        String pat = "EXAMPLE";

        int idx = bmMatch(txt, pat);

        if (idx == -1) {
            System.out.println("패턴 없음");
        } else {
            System.out.println((idx + 1) + "번째 문자부터 일치");
        }
    }
}
