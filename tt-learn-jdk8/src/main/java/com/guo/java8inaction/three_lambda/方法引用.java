package com.guo.java8inaction.three_lambda;

import com.guo.java8inaction.two.Apple;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class 方法引用 {
    public static void main(String[] args) {
        List<Apple> apples = Apple.getApples();
        //方法引用就是Lambda表达式(Apple a) -> a.getWeight()的快捷写法。
        Comparator<Apple> c = Comparator.comparing(Apple::getWeight);
//        Comparator<Apple> c = (a1, a2) -> a1.getWeight().compareTo(a2.getWeight());

        apples.sort(c);
        System.out.println(apples);


        List<String> str = Arrays.asList("a","b","A","B");
//        str.sort((s1, s2) -> s1.compareToIgnoreCase(s2));
        str.sort(String::compareToIgnoreCase); //等价于上面
    }
}
