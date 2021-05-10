package com.gyt.shiyan2.er;

public class Test2 {
    private double x;

    public Test2(double x) {
        this.x = x;
    }

    public double result1(){
        if (x>0){
            return 5*Math.sin(x) -3;
        }else if (x > -1 && x <=0){
            return (3*x -1)/Math.abs(Math.cos(x));
        }else {
            return Math.sqrt(Math.abs(x)) +Math.pow(x,4);
        }
    }
}
