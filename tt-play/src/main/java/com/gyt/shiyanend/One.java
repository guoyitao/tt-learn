package com.gyt.shiyanend;

import java.util.Scanner;

public class One {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double x = scanner.nextDouble();
        System.out.println("\n" + test(x));
    }

    public static double test(double x){
        if (x < 0){
            return Math.abs(x)/2;
        }else if(x>=0 && x <10 ){
            return 3 + Math.exp(x);
        }else if(x>=10 && x <20 ){
            return Math.log10(x) + Math.pow(x,1.5);
        }else {
            return Math.sqrt(x) + 3 * Math.cos(x);
        }
    }
}
