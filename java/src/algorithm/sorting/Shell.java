package algorithm.sorting;
// javac algorithm/sorting/Shell.java
// java algorithm/sorting/Shell

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

class Shell {

    static void shellSort1(int[] a) {
        for (int h = a.length / 2; h > 0; h /= 2) { // 간격을 점점 줄이기
            for (int i = h; i < a.length; i++) { // h 간격으로 삽입 정렬
                int j;
                int tmp = a[i];
                for (j = i - h; j >= 0 && a[j] > tmp; j -= h) {
                    a[j + h] = a[j];
                }
                a[j + h] = tmp;
            }
        }
    }

    @SuppressWarnings("empty-statement")
    static void shellSort2(int[] a) {
        int h;
        // 커누스 시퀀스를 이용해 최적의 갭 찾기
        for (h = 1; h < a.length; h = h * 3 + 1);

        for (; h > 0; h /= 3) {
            for (int i = h; i < a.length; i++) {
                int j;
                int tmp = a[i];
                for (j = i - h; j >= 0 && a[j] > tmp; j -= h) {
                    a[j + h] = a[j];
                }
                a[j + h] = tmp;
            }
        }
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

        shellSort2(n);

        for(int i = 0; i < n.length; i++) {
            System.out.print("a[" + i +  "] = " + n[i] + " / ");
        }

    }
}
