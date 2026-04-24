package algorithm.common;
// javac algorithm/common/ReverseArray.java
// java algorithm/common/ReverseArray

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class ReverseArray {

    static void swap(int[] arr, int idx1, int idx2) {
        int tmp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = tmp;
    }

    static void reverse(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            swap(arr, i, arr.length - i - 1);
        }
    }

    static int sum(int[] arr) {
        int sum = 0;
        for(int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }

    static void copy(int[] a, int[] b) {
        for(int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }
    }

    static void rcopy(int[] a, int[] b) {
        System.arraycopy(a, 0, b, 0, a.length);
        reverse(b);
    }

    public static void main(String[] args) throws IOException {
        Scanner stdIn = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("요소의 수:" );
        int n = stdIn.nextInt();

        int[] arr = new int[n];

        for(int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(20);
        }

        System.out.println("정렬 전: " + Arrays.toString(arr));
        
        reverse(arr);
        System.out.println("역순 정렬 후: " + Arrays.toString(arr));

        System.out.println("합:" + sum(arr));

        int[] b = new int[arr.length];

        copy(arr, b);

        System.out.println("arr copy - b: " + Arrays.toString(b));

        rcopy(arr, b);

        System.out.println("reverse arr copy - b: " + Arrays.toString(b));
    }
}
