package algorithm.common;
// javac algorithm/common/PhysicalExamination.java
// java algorithm/common/PhysicalExamination

import java.io.IOException;

class PhysicalExamination {

    static final int VMAX = 21;

    class Id {
        private static int counter = 0;
        private int id;

        public Id() {
            id = ++counter;
        }

        public static int getCounter() {
            return counter;
        }

        public int getId() {
            return id;
        }
    }

    static class YMD {
        int y;
        int m;
        int d;

        YMD(int y, int m, int d) {
            this.y = y;
            this.m = m;
            this.d = d;
        }
    }

    static class PhyscData {
        String name;
        int height;
        double vision;
        YMD ymd;

        PhyscData(String name, int height, double vision, int y, int m, int d) {
            this.name = name;
            this.height = height;
            this.vision = vision;
            this.ymd = new YMD(y, m, d);
        }
    }

    static double aveHeight(PhyscData[] data) {
        double sum = 0;

        for (int i = 0; i < data.length; i++) {
            sum += data[i].height;
        }

        return sum / data.length;
    }

    static void distVision(PhyscData[] data, int[] dist) {
        int i = 0;
        dist[i] = 0;
        for (; i < data.length; i++) {
            if (data[i].vision >= 0.0 && data[i].vision <= VMAX / 10.0) {
                dist[(int)(data[i].vision * 10)]++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        PhyscData[] x = {
            new PhyscData("make", 187, 0.4, 96, 02, 23),
            new PhyscData("mika", 197, 1.4, 91, 02, 28),
            new PhyscData("monaka", 166, 1.4, 93, 06, 27),
            new PhyscData("moka", 163, 0.8, 84, 12, 15),
        };

        int[] vdist = new int[VMAX];

        for (int i = 0; i < x.length; i++) {
            System.out.printf("%-8s%3d%5.1f%6d%2d%2d\n", x[i].name, x[i].height, x[i].vision, x[i].ymd.y, x[i].ymd.m, x[i].ymd.d);
        }
        System.out.printf("average height: %5.1f\n", aveHeight(x) );

        distVision(x, vdist);

        for(int i = 0; i < VMAX; i++) {
            System.out.printf("%3.1f~: %2d명 \n", i / 10.0, vdist[i]);
        }

        for(int i = 0; i < VMAX; i++) {
            System.out.printf("%3.1f~: %s \n", i / 10.0, "*".repeat(vdist[i]));
        }

        // 객체는 외부부터 접근가능하기 때문에 static이 아닌 inner class로 만들기 위해서는 바깥부터 접근
        // static class = "바깥 클래스 인스턴스 없이도 생성 가능한 중첩 클래스"
        PhysicalExamination outer = new PhysicalExamination();

        Id a = outer.new Id();
        Id b = outer.new Id();

        System.out.println("a의 아이디: " + a.getId());
        System.out.println("b의 아이디: " + b.getId());

        System.out.println("부여한 아이디의 개수" + Id.getCounter());
        System.out.println("부여한 아이디의 개수" + Id.counter); // 같은 클래스 내부이므로 접근 가능
    }
}
