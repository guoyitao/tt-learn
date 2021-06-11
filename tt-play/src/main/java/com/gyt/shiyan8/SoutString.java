package com.gyt.shiyan8;

import java.util.Arrays;

public class SoutString {
    public static void main(String[] args) {
        String string = "A8RrR12B3aZaCa6ZzZ6A6bCb5c5ja5AJp*7";
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : string.toCharArray()) {
            if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
                stringBuilder.append(c);
            }
        }
        char[] chars = stringBuilder.toString().toCharArray();
        popSort(chars);
        System.out.println(Arrays.toString(chars));

    }

    public static void popSort(char a[]) {
        char tmp;
        //两两相比 如果大于第下一个就交换  一轮就把最大的放后面，下一轮不看这个最大的了
        for (int i = 0; i < a.length - 1; ++i) {
            for (int j = 0; j < a.length - 1 - i; ++j) {
                char c = a[j];
                char c1 = a[j + 1];
                if (isXiaoxie(c)){
                    c = (char) (toDaxie(c)+1);
                }
                if (isXiaoxie(c1)){
                    c1 = (char) (toDaxie(c1));
                }
                if (c > c1) {
                    tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                }
            }
        }
    }

    public static boolean isXiaoxie(char s){
        return s >= 'a' && s <= 'z';
    }
    public static char toDaxie(char s) {
        if (s >= 'a' && s <= 'z') {
            return (char) (s - 32);
        }
        return s;
    }
}
