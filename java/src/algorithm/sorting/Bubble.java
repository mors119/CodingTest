package algorithm.sorting;
// javac algorithm/sorting/Bubble.java
// java algorithm/sorting/Bubble

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

class Bubble {

    static int changeValue = 0;

    static void swap(int[] a, int idx1, int idx2) {
        System.out.println("a[" + idx1 + "] " + a[idx1] + "  -   " + "a[" + idx2 + "] " + a[idx2] + " 교환");
        int t = a[idx1];
        a[idx1] = a[idx2];
        a[idx2] = t;
        changeValue++;
    }

    // 오름차순 정렬
    static void bubbleSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int exchange = 0; // 교환 횟수 저장
            for (int j = a.length - 1; j > i; j--) {
                if(a[j - 1] > a[j]) {
                    swap(a, j - 1, j);
                    exchange++;
                }
            }
            if(exchange == 0) break; // 교환이 없으면 빠져나가기
        }
    }

    static void bubbleSort2(int[] a) {
        int k = 0; 
        while(k < a.length - 1) {
            int last = a.length - 1;
            for (int j = a.length - 1; j > k; j--) {
                if(a[j - 1] > a[j]) {
                    swap(a, j - 1, j);
                    last = j;
                }
            }
            k = last; // 마지막 교환 위치
        }
    }

    // 쉐이커 정렬 (양방향 버블 정렬)
    static void shackerSort(int[] a) {
        int left = 0;
        int right = a.length - 1;

        while (left < right) {
            int last = right;

            for (int j = right; j > left; j--) {
                if(a[j - 1] > a[j]) {
                    swap(a, j - 1, j);
                    last = j;
                }
            }

            left = last;
            last = left;

            for (int j = left; j < right; j++) {
                if(a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                    last = j;
                }
            }
            
            right = last;
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

        long startTime = System.currentTimeMillis();
        // bubbleSort(n); // 200개 난수 정렬 시 실행 시간: 약 63 ~ 66ms 
        // bubbleSort2(n); // 60 ~ 66
        shackerSort(n); // 56 ~ 64
        long endTime = System.currentTimeMillis();

        for(int i = 0; i < n.length; i++) {
            System.out.print("a[" + i +  "] = " + n[i] + " / ");
        }
        System.out.println();
        System.out.println("총 교환 횟수: " + changeValue);

        long duration = endTime - startTime; 
        System.out.println("실행 시간: " + duration + "ms");
    }
}
