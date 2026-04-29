package algorithm.sorting;
// javac algorithm/sorting/Merge.java
// java algorithm/sorting/Merge

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

class Merge {

    // 대략적 정렬 후 병합
    static void merge(int[] a, int na, int[] b, int nb, int[] c) {
        int pa = 0;
        int pb = 0;
        int pc = 0;

        while (pa < na && pb < nb) {
            c[pc++] = (a[pa] <= b[pb]) ? a[pa++] : b[pb++];
        }
        while (pa < na) {
            c[pc++] = a[pa++];
        }
        while (pb < nb) {
            c[pc++] = b[pb++];
        }
    }

    static int[] buff; // 작업용 배열

    static void mergeSortRecursive(int[] a, int left, int right) {
        if (left >= right) {
            return;
        }

        int center = (left + right) / 2;

        mergeSortRecursive(a, left, center);
        mergeSortRecursive(a, center + 1, right);

        int leftSize = 0;

        // 왼쪽 절반을 buff에 복사
        for (int idx = left; idx <= center; idx++) {
            buff[leftSize++] = a[idx];
        }

        int buffIndex = 0;          // 왼쪽 배열 buff 읽는 위치
        int rightIndex = center + 1; // 오른쪽 배열 a 읽는 위치
        int writeIndex = left;       // 결과를 a에 다시 쓰는 위치

        while (buffIndex < leftSize && rightIndex <= right) {
            if (buff[buffIndex] <= a[rightIndex]) {
                a[writeIndex++] = buff[buffIndex++];
            } else {
                a[writeIndex++] = a[rightIndex++];
            }
        }

        // 왼쪽 배열에 남은 값 복사
        while (buffIndex < leftSize) {
            a[writeIndex++] = buff[buffIndex++];
        }
    }

    static void mergeSort(int[] a) {
        buff = new int[a.length];
        mergeSortRecursive(a, 0, a.length - 1);
        buff = null;
    }

    // TimSort(삽입 정렬 + 병합 정렬) 간단한 버전
    private static final int RUN = 32;

    static void insertionSort(int[] a, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int tmp = a[i];
            int j = i - 1;

            while (j >= left && a[j] > tmp) {
                a[j + 1] = a[j];
                j--;
            }

            a[j + 1] = tmp;
        }
    }

    static void merge(int[] a, int left, int mid, int right) {
        int leftSize = mid - left + 1;
        int rightSize = right - mid;

        int[] leftArr = new int[leftSize];
        int[] rightArr = new int[rightSize];

        for (int i = 0; i < leftSize; i++) {
            leftArr[i] = a[left + i];
        }

        for (int i = 0; i < rightSize; i++) {
            rightArr[i] = a[mid + 1 + i];
        }

        int i = 0;
        int j = 0;
        int k = left;

        while (i < leftSize && j < rightSize) {
            if (leftArr[i] <= rightArr[j]) {
                a[k++] = leftArr[i++];
            } else {
                a[k++] = rightArr[j++];
            }
        }

        while (i < leftSize) {
            a[k++] = leftArr[i++];
        }

        while (j < rightSize) {
            a[k++] = rightArr[j++];
        }
    }

    static void timSort(int[] a) {
        int n = a.length;

        // 1. 작은 구간들을 insertion sort로 정렬
        for (int left = 0; left < n; left += RUN) {
            int right = Math.min(left + RUN - 1, n - 1);
            insertionSort(a, left, right);
        }

        // 2. 정렬된 run들을 병합
        for (int size = RUN; size < n; size *= 2) {
            for (int left = 0; left < n; left += size * 2) {
                int mid = left + size - 1;
                int right = Math.min(left + size * 2 - 1, n - 1);

                if (mid < right) {
                    merge(a, left, mid, right);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner stdIn = new Scanner(System.in);

        System.out.println("요소의 수: ");
        int nx = stdIn.nextInt();
        // int[] a = new int[nx / 2];
        // int[] b = new int[nx / 2];
        // int[] c = new int[nx];
        int[] n = new int[nx];

        Random r = new Random();

        // for (int i = 0; i < a.length; i++) {
        //     a[i] = r.nextInt(10);
        //     System.out.printf("a = %2d / ", a[i]);
        // }
        // for (int i = 0; i < b.length; i++) {
        //     b[i]= r.nextInt(10);
        //     System.out.printf("b = %2d / ", b[i]);
        // }

        // merge(a, a.length, b, b.length, c);

        for (int i = 0; i < n.length; i++) {
            n[i] = r.nextInt(10);
            System.out.printf("a = %2d / ", n[i]);
        }
        System.out.println();

        timSort(n);

        for(int i = 0; i < n.length; i++) {
            System.out.print("n[" + i +  "] = " + n[i] + " / ");
        }

    }
}
