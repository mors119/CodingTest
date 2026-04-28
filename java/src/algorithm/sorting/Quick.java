package algorithm.sorting;
// javac algorithm/sorting/Quick.java
// java algorithm/sorting/Quick

import algorithm.stackQueue.Stack;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

class Quick {

    static void swap(int[] a, int idx1, int idx2) {
        System.out.println("a[" + idx1 + "] " + a[idx1] + "  -   " + "a[" + idx2 + "] " + a[idx2] + " 교환");
        int t = a[idx1];
        a[idx1] = a[idx2];
        a[idx2] = t;
    }

    // 배열 나누는 연습
    static void partition(int[] a) {
        int pl = 0;
        int pr = a.length - 1;
        int x = a[a.length / 2];

        do {
            while (a[pl] < x) pl++;
            while (a[pr] > x) pr--;
            if (pl <= pr) swap(a, pl++, pr--);
        } while (pl <= pr);

        System.out.println("피벗 값: " + x);

        System.out.println("피벗 이하 그룹");
        for (int i = 0; i <= pl - 1; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();

        if(pl > pr + 1) {
            System.out.println("피벗과 같은 그룹");
            for (int i = pr + 1; i <= pl - 1; i++) {
                System.out.print(a[i] + " ");
            }
            System.out.println();
        }

        System.out.println("피벗 이상 그룹");
            for (int i = pr + 1; i < a.length; i++) {
                System.out.print(a[i] + " ");
            }
        System.out.println();
    }

    // 재귀 퀵 정렬
    static void quickSort1(int[] a, int left, int right) {
        int pl = left;
        int pr = right;
        int x = a[(left + right) / 2];

        do {
            while (a[pl] < x) pl++;
            while (a[pr] > x) pr--;
            if (pl <= pr) swap(a, pl++, pr--);
        } while (pl <= pr);

        if(left < pr) quickSort1(a, left, pr);
        if (pl < right) quickSort1(a, pl, right);
    }

    // 비재귀 퀵 정렬 
    static void quickSort2(int[] a, int left, int right) {
        Stack.IntStack lStack = new Stack.IntStack(right - left + 1);
        Stack.IntStack rStack = new Stack.IntStack(right - left + 1);

        lStack.push(left);
        rStack.push(right);

        while (!lStack.isEmpty()) {
            left = lStack.pop();
            right = rStack.pop();

            int pl = left;
            int pr = right;
            int x = a[(left + right) / 2];

            do {
                while (a[pl] < x) pl++;
                while (a[pr] > x) pr--;
                if (pl <= pr) swap(a, pl++, pr--);
            } while (pl <= pr);

            if(left < pr) {
                lStack.push(left);
                rStack.push(pr);
            }
            if (pl < right) {
                lStack.push(pl);
                rStack.push(right);
            }
        }
    }

    // 비재귀 퀵정렬2
    static class Range {
        int left;
        int right;

        Range(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    static void quickSort3(int[] a) {
        Range[] stack = new Range[a.length];
        int ptr = 0;

        stack[ptr++] = new Range(0, a.length - 1);

        while (ptr > 0) {
            Range range = stack[--ptr];

            int left = range.left;
            int right = range.right;

            int pl = left;
            int pr = right;
            int pivot = a[(left + right) / 2];

            do {
                while (a[pl] < pivot) pl++;
                while (a[pr] > pivot) pr--;

                if (pl <= pr) {
                    swap(a, pl, pr);
                    pl++;
                    pr--;
                }
            } while (pl <= pr);

            // 왼쪽 그룹
            if (left < pr) {
                stack[ptr++] = new Range(left, pr);
            }

            // 오른쪽 그룹
            if (pl < right) {
                stack[ptr++] = new Range(pl, right);
            }
        }
    }

    // 조건 세분화 퀵 정렬
    static void quickSort4(int[] a, int left, int right) {
        int pl = left, pr = right;
        int pivot = a[(left + right) / 2];

        while (pl <= pr) {
            while (a[pl] < pivot) pl++;
            while (a[pr] > pivot) pr--;

            if (pl <= pr) {
                if (a[pl] == a[pr]) {
                    // 값이 같으면 swap해도 의미 없음 → 건너뜀
                    pl++;
                    pr--;
                } else if (pl < pr) {
                    // 정상 교환
                    int t = a[pl];
                    a[pl] = a[pr];
                    a[pr] = t;
                    pl++;
                    pr--;
                } else {
                    // pl == pr 인 경우
                    pl++;
                    pr--;
                }
            }
        }

        if (left < pr)  quickSort4(a, left, pr);
        if (pl < right) quickSort4(a, pl, right);
    }

    // 퀵은 요소가 적으면 속도가 떨어짐. 요소가 적을 경우 단순 삽입 정렬을 실시한다.

    static int THRESHOLD = 12;

    // 삽입정렬
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

    static int medianOfThree(int[] a, int left, int right) {
        int mid = (left + right) / 2;

        if (a[mid] < a[left]) swap(a, mid, left);
        if (a[right] < a[mid]) swap(a, right, mid);
        if (a[mid] < a[left]) swap(a, mid, left);

        // 중간값을 pivot으로 사용 (mid 위치)
        return a[mid];
    }


    static void quickSort5(int[] a, int left, int right) {

        // 작은 구간이면 삽입정렬
        if (right - left <= THRESHOLD) {
            insertionSort(a, left, right);
            return;
        }

        int pl = left;
        int pr = right;

        int pivot = medianOfThree(a, left, right);

        while (pl <= pr) {
            while (a[pl] < pivot) pl++;
            while (a[pr] > pivot) pr--;

            if (pl <= pr) {
                swap(a, pl, pr);
                pl++;
                pr--;
            }
        }

        if (left < pr) quickSort5(a, left, pr);
        if (pl < right) quickSort5(a, pl, right);
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

        quickSort2(n, 0, n.length - 1);

        for(int i = 0; i < n.length; i++) {
            System.out.print("a[" + i +  "] = " + n[i] + " / ");
        }

    }
}
