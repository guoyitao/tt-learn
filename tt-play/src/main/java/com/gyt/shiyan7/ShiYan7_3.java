package com.gyt.shiyan7;

import java.util.Arrays;

public class ShiYan7_3 {
    public static void main(String[] args) {
        int[] array = {1,2,4,5,3,-100,23,0,999,-6};
        chooseSort(array);
        System.out.println(Arrays.toString(array));

        int[] array2 = {0,2,99,5,3,-10,23,0,29,-2};
        popSort(array2);
        System.out.println(Arrays.toString(array2));
    }

    public static void chooseSort(int a[]){
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
        }
    }

    public static void popSort(int a[]){
        int tmp;
        //两两相比 如果大于第下一个就交换  一轮就把最大的放后面，下一轮不看这个最大的了
        for (int i = 0; i < a.length -1; ++i) {
            for (int j = 0; j < a.length - 1 - i; ++j) {
                if (a[j] > a[j+1]){
                    tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;
                }
            }
        }
    }
}
