package com.guo.java8inaction.three_lambda;

import com.guo.java8inaction.two.Apple;

import java.util.Comparator;
import java.util.List;

public class 复合比较器 {
    public static void main(String[] args) {
//        1. 逆序
        Comparator<Apple> c = Comparator.comparing(Apple::getWeight);
        List<Apple> apples = Apple.getApples();
//        对苹果按重量递减排序
        apples.sort(Comparator.comparing(Apple::getWeight).reversed());
        System.out.println(apples);

//        2. 比较器链
        //如果苹果重量一样就按颜色比较
        apples.sort(Comparator.comparing(Apple::getWeight).reversed().thenComparing(Apple::getColor));
        System.out.println(apples);
    }
}
