package chx.calculator;

import java.util.Scanner;

/**
 * user: chx
 * date: 2020/11/23 16:51
 * version:1.0
 */
public class TestCalculator {
    public static void main(String[] args) {
        System.out.println("输入两个整形进行运算");
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        CHXCalculator chxCalculator = new CHXCalculator(a, b);
        chxCalculator.add();
        chxCalculator.substract();
        chxCalculator.multiple();
        chxCalculator.divide();

    }
}
