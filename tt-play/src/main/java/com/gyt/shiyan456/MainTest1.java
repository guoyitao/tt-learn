package com.gyt.shiyan456;

public class MainTest1 {
    public static void main(String[] args) {
        Counting ca = new Counting("Li M",60);
        ca.plus(5);
        ca.minus(10);
        System.out.println(ca.getGrade());
        System.out.println(ca);

        System.out.println("\n\n-------------------------2--------------------------");

        Ranking ra = new Ranking("Wang s", 60);
        Ranking rb = new Ranking("Zhang s", 60);
        ra.plus(15);
        rb.plus(30);
        rb.minus(5);
        ra.show();
        rb.show();


        System.out.println("\n\n-------------------------3--------------------------");
        ClassCounting classA = new ClassCounting(10, -3, "小明");
        classA.showA();
        ClassCounting classB = new ClassCounting(-5, 20, 10,"小明");
        classB.showB();

    }
}
