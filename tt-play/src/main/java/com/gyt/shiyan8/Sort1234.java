package com.gyt.shiyan8;

import java.util.Arrays;

public class Sort1234 {
    public static void main(String[] args) {
        int[][] array = {
                {5, 18, 2, 28, 16, 374, 251, 221, 15, 40},
                {37, 25, 1, 2, 5, 81, 35, 60, 72, 66}
        };


        popSort(array[0],array);
//        chooseSort(array[1],array);
        for (int[] ints : array) {
            System.out.println(Arrays.toString(ints));
        }
    }

    public static void chooseSort(int a[],int array[][]){
        int k,tmp;
        for (int i = 0; i < a.length -1; ++i) {
            //把i后面的元素里面的最小找出来，与i位的元素交换
            k = i;
            for (int j = i+1; j < a.length; ++j) {
                if (a[j] < a[k]) {
                    k = j;
                }
            }
            tmp = a[k];
            a[k] = a[i];
            a[i] = tmp;

            tmp = array[0][k];
            array[0][k] = array[0][i];
            array[0][i] = tmp;
        }
    }

    public static void popSort(int a[],int array[][]){
        int tmp;
        //两两相比 如果大于第下一个就交换  一轮就把最大的放后面，下一轮不看这个最大的了
        for (int i = 0; i < a.length -1; ++i) {
            for (int j = 0; j < a.length - 1 - i; ++j) {
                if (a[j] > a[j+1]){
                    tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;

                    tmp = array[1][j];
                    array[1][j] = array[1][j+1];
                    array[1][j+1] = tmp;
                }
            }
        }
    }
}
