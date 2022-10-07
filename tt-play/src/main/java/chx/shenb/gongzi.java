package chx.shenb;

public class gongzi {
    public static void main(String[] args) {
        double i = 880000;
        double tmpI = i;
        double jiangjin = 0;
        if (i > 0 && i <= 100000) {
            jiangjin += i * 0.1;
        }
        if (i > 100000 && i < 200000){
            tmpI -= 100000;
            jiangjin += tmpI * 0.075;
            jiangjin += 100000* 0.1;
        }
        if (i > 200000 && i <= 400000){
            tmpI -= 200000;
            jiangjin += tmpI * 0.05;
            jiangjin += (100000* 0.1 + 100000* 0.075);
        }
        if (i > 400000 && i <= 600000){
            tmpI -= 400000;
            jiangjin += tmpI * 0.03;
            jiangjin += (100000* 0.1 + 100000* 0.075  + 200000* 0.05);
        }
        if (i > 600000 && i <= 1000000){
            tmpI -= 600000;
            jiangjin += tmpI * 0.015;
            jiangjin += (100000* 0.1 + 100000* 0.075  + 200000* 0.05 +  200000* 0.03);
        }
        if (i > 1000000 ){
            tmpI -= 1000000;
            jiangjin += tmpI * 0.01;
            jiangjin += (100000* 0.1 + 100000* 0.075  + 200000* 0.05 +  200000* 0.03 +  400000* 0.015);
        }
        System.out.println(jiangjin);
    }
}
