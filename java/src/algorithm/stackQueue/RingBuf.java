package algorithm.stackQueue;
// javac algorithm/stackQueue/RingBuf.java
// java algorithm/stackQueue/RingBuf

import java.util.Scanner;

class RingBuf {
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        final int N = 10; // 링의 크기
        int[] a = new int[N];
        int cnt = 0;
        int retry;

        do { 
            System.out.println("몇 개의 정수를 넣을까요? (반복수)");
            int num = stdIn.nextInt();
            for(int i = 0; i < num; i++) {
                System.out.printf("%d 번째 정수: ", cnt + 1 % N);
                a[cnt++ % N] = stdIn.nextInt();
            }

            System.out.println("계속 할까요? 1. 예 / 2. 아니오");
            retry = stdIn.nextInt();
        } while (retry == 1);

        int i = cnt - N;
        if(i < 0) i = 0;

        while(i < cnt) {
            System.out.printf("%2d번째 정수 = %d\n", i + 1, a[i % N]);
            i++;
        }
    }
}