package com.guo.java8inaction.two;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * 筛选
 */
public class FileterApple {
    public static List<Apple> filterAppleGreen(List<Apple> apples){
        ArrayList<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if ("green".equals(apple.getColor())){
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterAppleGreen(List<Apple> apples,String color){
        ArrayList<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if (color.equals(apple.getColor())){
                result.add(apple);
            }
        }
        return result;
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p){
        List<T> result = new ArrayList<>();
        for(T e: list){
            if(p.test(e)){
                result.add(e);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Apple> appless = Apple.getApples();
        System.out.println(filterAppleGreen(appless));
        System.out.println(filterAppleGreen(appless,"green"));

        System.out.println(filter(appless,(Apple apple)-> "red".equals(apple.getColor())));
    }
}
