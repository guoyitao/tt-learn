package com.gyt.shiyan7;

import java.util.Arrays;

public class ShiYan7_1 {
    int[] arr = new int[10];
    int index = 0;

    public void add(int n){
        arr[index++] = n;
    }

    public ShiYan7_1(int[] array) {
        for (int i : array) {
            add(i);
        }
    }

    @Override
    public String toString() {
        for (int i = 0; i < index; i++) {
            System.out.println(arr[i]);
        }

        return "ShiYan7_1{" +
                "arr=" + Arrays.toString(arr) +
                ", index=" + index +
                '}';
    }

    public static void main(String[] args) {
        int[] array = {5,8,23,45,89,136};
        ShiYan7_1 shiYan7_1 = new ShiYan7_1(array);

        shiYan7_1.add(56);


        System.out.println(shiYan7_1);
    }
}
