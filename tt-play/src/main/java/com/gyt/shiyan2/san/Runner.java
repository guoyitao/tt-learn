package com.gyt.shiyan2.san;

public class Runner {
    public static void main(String[] args) {
        Test3 test3 = new Test3();
        test3.factor(8);
        System.out.println(test3.getHashSet());

        Test3 test33 = new Test3();
        test33.factor(20);
        System.out.println(test33.getHashSet());


        Test3 test333 = new Test3();
        test333.factor(9724);
        System.out.println(test33.getHashSet());
    }
}
