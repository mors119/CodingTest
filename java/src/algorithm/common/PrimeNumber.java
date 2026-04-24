package algorithm.common;
// javac algorithm/common/PrimeNumber.java
// java algorithm/common/PrimeNumber

import java.io.IOException;

class PrimeNumber {
    static void primeNumber1(int num) {
        // 소수 찾기
        int cnt = 0;

        for (int n = 2; n <= num; n++) {
            int i;
            for (i = 2; i < n; i++) {
                cnt++;
                if(n % i == 0) break; 
            }
            if(n == i) {
                System.out.println(n);
            }
        }
        System.out.println("나눗셈 수행 횟수: " + cnt);
    }

    static void primeNumber2(int num) {
        int cnt = 0;
        int ptr = 0;
        int[] prime = new int[500];

        prime[ptr++] = 2; // 2는 소수이므로 미리 추가

        for (int n = 3; n <= num; n += 2) { // 소수만 조사
            int i;
            for (i = 1; i < ptr; i++) {
                cnt++;
                if(n % prime[i] == 0) break; 
            }
            if(ptr == i) {
                prime[ptr++] = n;
            }
        }

        for(int i = 0; i < ptr; i++) System.out.println(prime[i]);
        System.out.println("나눗셈 수행 횟수: " + cnt);
    }

    static void primeNumber3(int num) {
        int cnt = 0;
        int ptr = 0;
        int[] prime = new int[500];

        prime[ptr++] = 2; // 2는 소수이므로 미리 추가
        prime[ptr++] = 3; 

        for (int n = 5; n <= num; n += 2) { // 짝수는 모두 합성수 이므로 제외, 소수만 조사
            boolean flag = false;

            // √n까지만 나눠봐서 안 나눠지면 → 무조건 소수
            // 이미 구한 소수로만 나눔 ( 2, 3, 5, 7, 11 ...)
            for (int i = 1; prime[i] * prime[i] <= n; i++) {
                // % 연산 1번 * 비교 1번이므로 연산 + 2
                cnt += 2;

                if(n % prime[i] == 0) {
                    flag = true;
                    break; 
                }
            }
            if(!flag) {
                prime[ptr++] = n;
                cnt++;
            }
        }

        for(int i = 0; i < ptr; i++) System.out.println(prime[i]);
        System.out.println("나눗셈 수행 횟수: " + cnt);
    }

    public static void main(String[] args) throws IOException {
        int num = 100;

        primeNumber3(num);
    }
}
