package chx.exam;

import java.util.HashMap;
import java.util.Map;

public class Stu {

    private Double a;
    private Double b;
    private Double c;

    private Double sum;

    public Stu(Double a, Double b, Double c, Double sum) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.sum = sum;
    }

    public Double getA() {
        return a;
    }

    public void setA(Double a) {
        this.a = a;
    }

    public Double getB() {
        return b;
    }

    public void setB(Double b) {
        this.b = b;
    }

    public Double getC() {
        return c;
    }

    public void setC(Double c) {
        this.c = c;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "Stu{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", avg=" + sum/3 +
                '}';
    }

    public static void main(String[] args) {
       Map<Long,String> map =  new HashMap<>();
       map.put(1L,"asd");
       System.out.println(map.get(1L));
    }
}
