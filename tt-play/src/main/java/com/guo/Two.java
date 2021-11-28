package com.guo;

public class Two {
    public static void main(String[] args) {
        Two two = new Two();
        two.dowork(100,2,2,2);
        two.dowork(9,3,0,0);
        two.dowork(8,-1,-1,-1);
        two.dowork(7,-1,-1,10);
    }

    void dowork(int s, int x, int y, int z) {
        System.out.print(" (1) ");
        while (s++ < 10) {
            System.out.print(" (2) ");
            if ((x > 1) || (y == 0)) {
                System.out.print(" (3) ");
                z = z / x;
                System.out.print(" (4) ");
            } else {
                System.out.print(" (5) ");
                if ((z < 0) && (s > 1)) {
                    System.out.print(" (6) ");
                    s++;
                    System.out.print(" (7) ");
                    x = y + z;
                    System.out.print(" (8) ");
                }
            }
            System.out.print(" (9) ");
        }
        System.out.println(" (10) ");
    }
}
