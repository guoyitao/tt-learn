package com.gyt;

import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double sum = 0;
        int m = 0;
        while (scanner.hasNextDouble()){
            m += 1;
            sum += scanner.nextDouble();
        }
        System.out.println(sum);
        System.out.println(m);
    }
}
