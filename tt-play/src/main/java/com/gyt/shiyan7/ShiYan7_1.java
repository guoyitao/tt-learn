package com.gyt.shiyan7;

import java.util.Arrays;


public class ShiYan7_1 {
    private int[] arr;
    private int size = 0;
    private int maxSize = 10;

    public int maxSize() {
        return this.maxSize;
    }

    public void add(int n){

        if (size >= maxSize){
            System.out.printf("扩容 arr:[%s] size:[%d]  maxSize:[%d]\n",this.toString(),size, maxSize);
            //扩容
            maxSize = maxSize * 2;
            int[] newArray = new int[maxSize];
            System.arraycopy(arr, 0, newArray, 0, arr.length);
            arr = newArray;
        }
        for (int i = 0; i < size; i++) {
            if (arr[i] > n  ){
                for (int j = size-1; j >= i; j--) {
                    arr[j+1] = arr[j];
                }
                arr[i] = n;
                size++;
                return;
            }
        }
    }

    public ShiYan7_1(int[] array) {
        this.arr = new int[maxSize];
        for (int i = 0; i < array.length; i++) {
            arr[i] = array[i];
        }
        size = array.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(arr);
    }

    public static void main(String[] args) {
        int[] array = {5,8,23,45,89,136};
        ShiYan7_1 shiYan7_1 = new ShiYan7_1(array);
        shiYan7_1.add(56);
//        shiYan7_1.add(56);
//        shiYan7_1.add(56);
//        shiYan7_1.add(56);
//        shiYan7_1.add(561);
//        shiYan7_1.add(77);
//        shiYan7_1.add(66);
        System.out.println(shiYan7_1);
    }
}
