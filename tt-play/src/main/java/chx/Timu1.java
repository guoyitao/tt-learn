package chx;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Timu1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入：");
        String input = scanner.nextLine();
        //用流  把用户输入的用空格隔开并且映射成int 排序然后放入到数组
        List<Integer> score = Arrays.stream(input.split(" ")).map(Integer::parseInt).sorted().collect(Collectors.toList());
        //输出
        for (int i = 0; i < score.size(); i++) {
            if (i != score.size() -1){ //不是最后一个
                System.out.print(score.get(i) + " ");
            }else {//最后一个
                System.out.print(score.get(i));
            }
        }
    }
}
