package homework1;

public class MyMax {
    public int getMaxValue(int a,int b,int c){
        int max = 0;
        if (a> b){
            max = a;
        }else{
            max = b;
        }
        if (c > max){
            max = c;
        }
        return max;
    }

    public double getMaxValue(double a,double b,double c){
        double max = 0;
        if (a> b){
            max = a;
        }else{
            max = b;
        }
        if (c > max){
            max = c;
        }
        return max;
    }


    public static void main(String[] args) {
        MyMax myMax = new MyMax();
        System.out.println(myMax.getMaxValue(-1,23,2));
        System.out.println(myMax.getMaxValue(0.6,-0.9999,-0.1));
    }
}
