package algorithm.sorting;
// javac algorithm/sorting/Heap.java
// java algorithm/sorting/Heap


import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

class Heap {

    static void swap(int[] a, int idx1, int idx2) {
        System.out.println("a[" + idx1 + "] " + a[idx1] + "  -   " + "a[" + idx2 + "] " + a[idx2] + " 교환");
        int t = a[idx1];
        a[idx1] = a[idx2];
        a[idx2] = t;
    }

    static void downHeap(int[] a, int left, int right) {
        int temp = a[left];
        int child;
        int parent;

        for (parent = left; parent < (right + 1) / 2; parent = child) {
            int cl = parent * 2 + 1;
            int cr = cl + 1;
            child = (cr <= right && a[cr] > a[cl]) ? cr : cl;
            if (temp >= a[child]) break;
            a[parent] = a[child];
        }
        a[parent] = temp;
    }

    static void heapSort(int[] a) {
        for (int i = (a.length - 1) / 2; i >= 0; i--) {
            downHeap(a, i, a.length - 1);
        }

        for (int i = a.length - 1; i > 0; i--) {
            swap(a, 0, i);
            downHeap(a, 0, i -1);
        }
    }

    static void heapSortImproved(int[] a) {
        int n = a.length;

        // 1. 최대 힙 생성
        for (int i = n / 2 - 1; i >= 0; i--) {
            downHeap2(a, i, n - 1);
        }

        // 2. 루트 최댓값을 뒤로 보내고, 남은 구간 다시 힙화
        for (int right = n - 1; right > 0; right--) {
            swap(a, 0, right);
            downHeap2(a, 0, right - 1);
        }
    }

    // 향상된 힙 정렬
    static void downHeap2(int[] a, int left, int right) {
        int temp = a[left];
        int parent = left;

        while (parent < (right + 1) / 2) {
            int leftChild = parent * 2 + 1;
            int rightChild = leftChild + 1;

            int biggerChild = leftChild;

            if (rightChild <= right && a[rightChild] > a[leftChild]) {
                biggerChild = rightChild;
            }

            if (temp >= a[biggerChild]) {
                break;
            }

            a[parent] = a[biggerChild];
            parent = biggerChild;
        }

        a[parent] = temp;
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

        heapSortImproved(n);

        for(int i = 0; i < n.length; i++) {
            System.out.print("a[" + i +  "] = " + n[i] + " / ");
        }

    }
}
