package com.gyt.shiyan2.yi;

public class Test1 {
    private double a;
    private double b;
    private double c;

    public Test1(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double result1(){
        return  (-b + Math.sqrt(Math.pow(b,2)-4*a*c))/(2*a);
    }
    public double result2(){
        return (-b - Math.sqrt(Math.pow(b,2)-4*a*c))/ (2*a);
    }
}
