package com.gyt.shiyanend;

import java.util.Scanner;

public class Two {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        System.out.println("\n" + test(x));
    }

    public static double test(double x) {
        if (x < 0) {
            return (Math.abs(x) + Math.sin(Math.abs(x))) / 5;
        } else if (x >= 0 && x < 5) {
            return (1 / x) + Math.exp(-x);
        } else if (x >= 5 && x < 10) {
            return Math.log10(x) + Math.pow(x, 2.5);
        } else {
            return 2 * Math.sqrt(x) + 5 * Math.cos(x);
        }
    }
}
