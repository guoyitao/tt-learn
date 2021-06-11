package com.gyt.shiyanend;

import java.util.Arrays;

public class Three {
    public static void main(String[] args) {
        int[][] array = new int[][]{
                {1,6},
                {21,-6},
                {-1,8},
                {-998,-76},
                {-1,-16},
                {33,-56},
                {2,0},
                {100,1}
        };
//        int[][] array2 = new int[8][2];
//        Scanner scanner = new Scanner(System.in);
//        for (int i = 0; i < array2.length; i++) {
//            System.out.printf("请输入第%d行    ",i);
//            for (int j = 0; j < 2; j++) {
//                System.out.printf("请输入第%d个",j+1);
//                System.out.println();
//                array2[i][j] = scanner.nextInt();
//            }
//        }
//        chooseSort(array);
        popSort(array);
        for (int[] ints : array) {
            System.out.printf("%s\n", Arrays.toString(ints));
        }

    }


    public static void chooseSort(int a[][]){
        int k,tmp[];
        for (int i = 0; i < a.length -1; ++i) {
            //把i后面的元素里面的最小找出来，与i位的元素交换
            k = i;
            for (int j = i+1; j < a.length; ++j) {
                if (a[j][1] < a[k][1]) {
                    k = j;
                }
            }
            tmp = a[k];
            a[k] = a[i];
            a[i] = tmp;

        }
    }

    public static void popSort(int a[][]){
        int tmp[];
        //两两相比 如果大于第下一个就交换  一轮就把最大的放后面，下一轮不看这个最大的了
        for (int i = 0; i < a.length -1; ++i) {
            for (int j = 0; j < a.length - 1 - i; ++j) {
                if (a[j][0] > a[j+1][0]){
                    tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;

                }
            }
        }
    }
}
