package algorithm.search;
// javac algorithm/search/SearchExam.java
// java algorithm/search/SearchExam

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class SearchExam {

    static class PhyscData {
        private String name;
        private int    height;
        private double vision;

        public PhyscData(String name, int height, double vision) {
            this.name = name; this.height = height; this.vision = vision;
        }

        public String toString() {
            return name + " " + height + " " + vision;
        }

        // 공통으로 쓰는 비교 규칙이기 때문에 static final로 만듦 
        // Comparator 두 객체를 어떻게 비교할지 규칙을 정의하는 객체
        // 사용자 정의로 객체를 비교하기 위해 Comparable 또는 Comparator을 쓴다.
        public static final Comparator<PhyscData> HEIGHT_ORDER = new HeightOrderComparator();

        private static class HeightOrderComparator implements Comparator<PhyscData> {
            public int compare(PhyscData d1, PhyscData d2) {
                return (d1.height > d2.height) ? 1: (d1.height < d2.height) ? -1 : 0;
            }
        }
    }

    // 제네릭 테스트
    static class GenericClass<T> {
        private T xyz;

        public GenericClass(T t) {
            this.xyz = t;
        }
        T getXyz() {
            return xyz;
        }
    }

    public static void main(String[] args) throws IOException { 
        Scanner stdIn = new Scanner(System.in);
        PhyscData[] x = {
            new PhyscData("kim",   172, 1.2),
            new PhyscData("lee",   165, 0.8),
            new PhyscData("park",  180, 1.5),
            new PhyscData("choi",  158, 0.6),
            new PhyscData("jung",  175, 1.0),
            new PhyscData("han",   168, 0.9),
            new PhyscData("kang",  182, 1.3),
            new PhyscData("yoon",  160, 0.7),
            new PhyscData("shin",  170, 1.1),
            new PhyscData("oh",    178, 1.4)
        };

        System.out.println("찾고 있는 키 입력: ");
        int height = stdIn.nextInt();
        int idx = Arrays.binarySearch(
            x,
            new PhyscData("", height, 0.0),
            PhyscData.HEIGHT_ORDER
        );

        if(idx < 0) {
            System.out.println("해당 키 없음.");
        }
        if(idx >= 0) {
            System.out.println(height + "은 x[" + idx + "]에 있습니다.");
            System.out.println("찾은 데이터: " + x[idx].name);
        }

        // 제네릭 테스트
        GenericClass<String> s = new GenericClass<>("ABC");
        GenericClass<Integer> i = new GenericClass<>(1);

        System.out.println(s.getXyz());
        System.out.println(i.getXyz());

    }
}
