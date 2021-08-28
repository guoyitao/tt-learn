package com.gyt;

public class Sushu {
    public static void main(String[] args) {
        int sum = 0;
        for (int i = 500; i <=1000 ; i++) {
            boolean isSushu = true;
            for (int j = 2; j < i ; j++) {
                if (i % j == 0){
                    isSushu = false;
                    break;
                }
            }
            if (isSushu) {
                sum++;
            }

        }
        System.out.println("sum=:"+sum);
    }
}
