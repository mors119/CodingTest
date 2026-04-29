package algorithm.sorting;
// javac algorithm/sorting/Counting.java
// java algorithm/sorting/Counting


import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

class Counting {

    // 20개 이하는 빠르게 정렬 가능하다. (현재 코드 음수 불가)
    static void countingSort(int [] a) {
        // 가장 큰 값 찾기
        int max = a[0];
        for (int i = 1; i < a.length; i++) {
            if(a[i] > max) max = a[i];
        }

        // 도수 배열 생성
        int[] f = new int[max + 1]; // 숫자가 몇 번 나왔는지 저장
        int[] b = new int[a.length];

        for (int i = 0; i < a.length; i++) f[a[i]]++; // 각 숫자의 개수 세기
        for (int i = 1; i <= max; i++) f[i] += f[i -1]; // 누적합 만들기
        for (int i = a.length - 1; i >= 0; i--) b[--f[a[i]]] = a[i];  // 마지막 부터 결과 배열에 배치
        System.arraycopy(b, 0, a, 0, a.length); // 정렬 결과를 원본 배열에 복사
    }

    // 향상된 도수 정렬 (음수 가능)
    static void countingSortImproved(int[] a) {
        if (a == null || a.length <= 1) {
            return;
        }

        int min = a[0];
        int max = a[0];

        // 최소값과 최대값 찾기
        for (int i = 1; i < a.length; i++) {
            if (a[i] < min) min = a[i];
            if (a[i] > max) max = a[i];
        }

        int range = max - min + 1;

        int[] count = new int[range];
        int[] result = new int[a.length];

        // 개수 세기
        for (int value : a) {
            count[value - min]++;
        }

        // 누적합 만들기
        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }

        // 뒤에서부터 배치해서 안정성 유지
        for (int i = a.length - 1; i >= 0; i--) {
            int value = a[i];
            int countIndex = value - min;

            result[--count[countIndex]] = value;
        }

        System.arraycopy(result, 0, a, 0, a.length);
    }

    public static void main(String[] args) throws IOException {
        Scanner stdIn = new Scanner(System.in);

        System.out.println("요소의 수: ");
        int nx = stdIn.nextInt();
        int[] n = new int[nx];

        Random r = new Random();

        for (int i = 0; i < nx; i++) {
            n[i] = r.nextInt(20);
            System.out.printf("%2d / ", n[i]);
        }
        System.out.println();

        countingSortImproved(n);

        for(int i = 0; i < n.length; i++) {
            System.out.print("a[" + i +  "] = " + n[i] + " / ");
        }

    }
}
