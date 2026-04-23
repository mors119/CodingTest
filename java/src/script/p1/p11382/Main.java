package script.p1.p11382;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] str = br.readLine().split(" ");

        long rs = 0;

        for (String str1 : str) {
            rs += Long.parseLong(str1);
        }

        System.out.println(rs);
    }
}