package chx.calculator;

/**
 * user: chx
 * date: 2020/11/23 16:45
 * version:1.0
 */
public class CHXCalculator {
    private int a;
    private int b;

    public CHXCalculator(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public void add(){
        System.out.printf("%d+%d=%d\n",a,b,a+b);
    }

    public void substract(){
        System.out.printf("%d-%d=%d\n",a,b,a-b);
    }

    public void multiple(){
        System.out.printf("%dX%d=%d\n",a,b,a*b);
    }

    public void divide(){
        System.out.printf("%d+%d=%f\n",a,b,a/(b*1.0));
    }
}
