package com.gyt.shiyan2.si;

public class Test4 {
    private String s1;
    private String s2;

    public Test4(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;


    }
    public String reverses(String str) {
        char[] ch = str.toCharArray();
        String  str2 = "";
        for (int i = ch.length - 1; i >= 0; i --) {
            str2 = str2 + ch[i];
        }
        return str2;
    }
    public void print(){
        String reverses = reverses(s1);
        System.out.println("s1:" + reverses +"       "+  s1.equals(reverses));
        String reverses1 = reverses(s2);
        System.out.println("s2:" + reverses1 +"        "+ s2.equals(reverses1));


    }

}
