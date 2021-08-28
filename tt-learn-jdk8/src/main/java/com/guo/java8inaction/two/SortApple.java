package com.guo.java8inaction.two;

import java.util.List;

public class SortApple {
    public static void main(String[] args) {
        List<Apple> apples = Apple.getApples();
        apples.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
        System.out.println(apples);
    }
}
