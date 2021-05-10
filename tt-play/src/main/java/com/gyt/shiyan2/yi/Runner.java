package com.gyt.shiyan2.yi;

public class Runner {
    public static void main(String[] args) {
        Test1 test1 = new Test1(5,3,-2);
        System.out.println(test1.result1()   + "   " + test1.result2());

        Test1 test2 = new Test1(4,6,3);
        System.out.println(test2.result1()   + "   " + test2.result2());
    }
}
