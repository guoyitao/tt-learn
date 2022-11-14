package com.abc;

public class Work02 {
    public static void main(String[] args) {
        int[] arr = new int[]{100,50,90,60,80,70};
        int max = arr[0];
        int min = arr[0];
        int sum = 0;
        for (int i : arr) {
            if (i > max){
                max = i;
            }
            if (i < min){
                min = i;
            }
            sum += i;
        }

        System.out.println("平均值为：" +(sum-min-max)/(arr.length-2));



    }
}
