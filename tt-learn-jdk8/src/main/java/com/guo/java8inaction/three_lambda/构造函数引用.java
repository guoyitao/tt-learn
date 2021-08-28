package com.guo.java8inaction.three_lambda;

import com.guo.java8inaction.two.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class 构造函数引用 {
    public static void main(String[] args) {
        //无参
        Supplier<Apple> aNew = Apple::new;
        Apple apple = aNew.get();

        Supplier<Apple> supplier = () -> new Apple();
        Apple apple1 = supplier.get();

        //有参
        Function<Integer, Apple> youcan1 = Apple::new;
        Function<Integer, Apple> youcan11 = (weight)-> new Apple(weight);
        youcan11.apply(12);
        youcan1.apply(12);

        //构造list
        List<Integer> weights = Arrays.asList(7, 3, 4, 10);
        List<Apple> map = map(weights, Apple::new);
        System.out.printf("构造list: %s",map);


        BiFunction<Integer, String, Apple> you2 = Apple::new;
        Apple green = you2.apply(12, "green");

        

    }

    public static List<Apple> map(List<Integer> list,
                                  Function<Integer, Apple> f){
        List<Apple> result = new ArrayList<>();
        for(Integer e: list){
            result.add(f.apply(e));
        }
        return result;
    }
}
