package chx.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tedt {
    public static void main(String[] args) {
        List<Stu> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);


        for (int i = 0; i < 3; i++) {
            System.out.println("输入");
            double a = scanner.nextDouble();
            double b = scanner.nextDouble();
            double c = scanner.nextDouble();
            list.add(new Stu(a,b,c,a+b+c));
        }

        int max = 0;
        for (int i = 1; i < 3; i++) {
           if (list.get(max).getSum() > list.get(i).getSum()){
               max = i;
           }
        }

        for (int i = 0; i < 3; i++) {
            System.out.println(list.get(0));
        }

        System.out.println("max:" + list.get(max));

    }
}
