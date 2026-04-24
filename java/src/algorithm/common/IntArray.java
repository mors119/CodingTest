package algorithm.common;
// javac algorithm/common/IntArray.java
// java algorithm/common/IntArray

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class IntArray {

    static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println("arr[" + i + "] = " + arr[i]);
        }
    }

    static void max(int[] arr) {
        int max = arr[0];

        for(int i = 1; i < arr.length; i++) {
            if(arr[i] > max) max = arr[i];
        }

        System.out.println("최대값: " + max);
    }

    static void maxHeight(Scanner stdIn, int num) {
        int[] height = new int[num];

        for(int i = 0; i < num; i++) {
            System.out.println(i + 1 + "번째 사람의 키");
            height[i] = stdIn.nextInt();
        }

        max(height);
    }

    static void randHeight(Scanner stdIn, int num) {
        Random rand = new Random();
        int[] height = new int[num];

        for(int i = 0; i < height.length; i++) {
            height[i] =  120 + rand.nextInt(90);
            System.out.println(i+ 1+"번의 키는 " + height[i] + " 입니다.");
        }

        max(height);
    }


    public static void main(String[] args) throws IOException {
        int[] arr = {1, 2, 3, 4, 5};

        // printArray(arr);
        // max(arr);

        Scanner stdIn = new Scanner(System.in);

        System.out.println("사람 수: ");
        int num = stdIn.nextInt();

        randHeight(stdIn, num);
    }
}
