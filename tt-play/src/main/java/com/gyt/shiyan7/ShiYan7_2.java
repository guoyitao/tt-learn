package com.gyt.shiyan7;

public class ShiYan7_2 {
    public static void main(String[] args) {
        int[][] array = {
                {1,2,3,4,1},
                {7,2,9,1,2},
                {1,2,1,4,5},
                {7,1,1,2,2},
                {1,2,3,4,5}
        };
        int sum = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (i ==j || i+j==array[0].length -1){
                    sum += array[i][j];
                }
            }
        }
        if (array.length % 2 != 0){
            sum -= array[array.length/2][array.length/2];
        }
        System.out.println(sum);
    }
}
