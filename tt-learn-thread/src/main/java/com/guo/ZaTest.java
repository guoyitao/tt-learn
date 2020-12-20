package com.guo;

import java.util.ArrayList;
import java.util.Scanner;


public class ZaTest {

    String string;

    public ZaTest(String string) {
        this.string = string;
    }


    public String[] split(char i) {
        int start = 0;
        char[] chars = string.toCharArray();
        ArrayList<String> arrayList = new ArrayList<>(chars.length);

        for (int i1 = 0; i1 < chars.length; i1++) {
            int end = indexOf(chars, i,start -1);
            if (end == -1){
                end = chars.length;
            }
            arrayList.add(copy(chars, start, end));
            start = end +1;

            if (end == chars.length) break;
        }

        return arrayList.toArray(new String[arrayList.size()]);
    }

    public String copy(char[] c, int start, int end) {
        char[] newc = new char[end - start];
        int indexNewc = 0;
        for (int i = start; i < end; i++) {
            newc[indexNewc++] = c[i];
        }
        return new String(newc);
    }

    //查找切割元素的下标
    public int indexOf(char[] chars, char c,int start) {

        for (int i = start +1; i < chars.length; i++) {
            if (chars[i] == c) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
//        String[] split = new ZaTest("你as，a好b，as啊").split('，');
//        System.out.println(split[0]);
//        System.out.println(split[1]);
//        System.out.println(split[2]);


        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入性别（男）（女）");
        String sex = scanner.next();
        System.out.println("输入成绩");
        int time = scanner.nextInt();

        if ("男".equals(sex) && time < 11){
            System.out.println("男" +time);
        }else if ("女".equals(sex) && time< 12){
            System.out.println("女" +time);
        }else{
            System.out.println("姓名输入错误");
        }

    }
}
