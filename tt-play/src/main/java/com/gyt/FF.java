package com.gyt;

import java.util.Scanner;

public class FF {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(f(scanner.nextDouble()));
    }
    public static double f(double x){
        if (x < 0){
            return (2*Math.abs(x))/3;
        }else if (x>=0 && x <5){
            return 1+2* Math.exp(x);
        }else if (x>=5 && x <10){
            return 3*Math.log10(x) + Math.pow(x,2);
        }else {
            return Math.sqrt(x) +Math.cos(x);
        }
    }
}
