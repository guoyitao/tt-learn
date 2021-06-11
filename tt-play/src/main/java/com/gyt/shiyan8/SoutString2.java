package com.gyt.shiyan8;

public class SoutString2 {
    public static void main(String[] args) {
        String string = "A8RrR12B3aZaCa6ZzZ6A6bCb5c5ja5AJp*7";
        int[][] array = new int[26][2];
        for (char c : string.toCharArray()) {
            if (c >= 'a' && c <= 'z' ) {
                array[c-97][1] +=1;
            }
            if (c >= 'A' && c <= 'Z') {
                array[c-65][0] +=1;
            }
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i][0]; j++) {
                System.out.print((char)(i+65));
            }
            for (int j = 0; j < array[i][1]; j++) {
                System.out.print((char)(i+97));
            }
        }
    }

}
