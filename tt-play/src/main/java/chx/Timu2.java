package chx;

import java.util.Scanner;

public class Timu2 {
    public static void main(String[] args) {
        //随机1到100的整数
        int num = (int)(Math.random() * 100 +1);
        Scanner scanner = new Scanner(System.in);
        while (true){
            //输入
            int input = scanner.nextInt();
            if (input > num){
                System.out.println("猜大了");
            }else if (input < num){
                System.out.println("猜小了");
            }else{
                //正确 退出循环然后结束程序
                System.out.println("猜对了");
                break;
            }
        }
    }
}
