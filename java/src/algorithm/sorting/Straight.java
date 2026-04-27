package algorithm.sorting;
// javac algorithm/sorting/Straight.java
// java algorithm/sorting/Straight

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

class Straight {

    static int changeValue = 0;

    static void swap(int[] a, int idx1, int idx2) {
        System.out.println("a[" + idx1 + "] " + a[idx1] + "  -   " + "a[" + idx2 + "] " + a[idx2] + " 교환");
        int t = a[idx1];
        a[idx1] = a[idx2];
        a[idx2] = t;
        changeValue++;
    }

    // 단순 선택 정렬
    static void straightSelectionSort(int[] a) {
        for(int i = 0; i < a.length - 1; i++) {
            int min = i;
            for(int j = i + 1; j < a.length; j++ ) {
                if(a[j]< a[min]) {
                    min = j;
                }
                swap(a, i, min);
            }
        }
    }

    // 단순 삽입 정렬
    static void insertionSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int tmp = a[i];
            int j = i;

            while (j > 0 && a[j - 1] > tmp) {
                a[j] = a[j - 1];
                j--;
            }

            a[j] = tmp;
        }
    }

    // Sentinel 최적화
    static void insertionSortWithSentinel(int[] a) {
        // 1. 최소값을 0번으로 이동 (sentinel)
        int minIdx = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i] < a[minIdx]) {
                minIdx = i;
            }
        }

        swap(a, 0, minIdx);

        // 2. 삽입 정렬
        for (int i = 2; i < a.length; i++) {
            int tmp = a[i];
            int j = i;

            // j > 0 체크 필요 없음
            while (a[j - 1] > tmp) {
                a[j] = a[j - 1];
                j--;
            }

            a[j] = tmp;
        }
    }

    static void printArray(int[] a) {
        for (int x : a) System.out.print(x + " ");
        System.out.println();
    }

    // 이진 삽입 정렬
    static void binaryInsertionSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int tmp = a[i];

            System.out.println("\n=== i = " + i + ", tmp = " + tmp + " ===");
            printArray(a);

            int left = 0;
            int right = i;

            // 삽입 위치를 이진 탐색 (위치 이진 탐색)
            while (left < right) {
                //  ">>> 1" (부호없는 비트 연산, 우측 시프트)은 "/ 2" (나누기 2)와 같은 역할을 하게 된다.
                int mid = (left + right) >>> 1; // overflow 방지용 비트 연산

                System.out.println("  left=" + left + ", right=" + right + ", mid=" + mid + ", a[mid]=" + a[mid]);

                if (a[mid] <= tmp) left = mid + 1;
                else right = mid;
            }

            System.out.println("  → insert position = " + left);

            // 뒤로 밀기
            for (int j = i; j > left; j--) {
                a[j] = a[j - 1];
            }

            a[left] = tmp;

            System.out.println("  → after insert");
            printArray(a);
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

        // long startTime = System.currentTimeMillis();
        binaryInsertionSort(n);
        // long endTime = System.currentTimeMillis();

        for(int i = 0; i < n.length; i++) {
            System.out.print("a[" + i +  "] = " + n[i] + " / ");
        }
        System.out.println();
        System.out.println("총 교환 횟수: " + changeValue);

        // long duration = endTime - startTime; 
        // System.out.println("실행 시간: " + duration + "ms");
    }
}
