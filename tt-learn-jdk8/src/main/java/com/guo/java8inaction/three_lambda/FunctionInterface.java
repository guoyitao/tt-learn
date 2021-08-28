package com.guo.java8inaction.three_lambda;

import com.guo.java8inaction.two.Apple;

import java.util.concurrent.Callable;
import java.util.function.Predicate;

/**
 * 函数式接口就是只定义一个抽象方法的接口
 * 只要接口只定义了一个抽象方法，它就仍然是一个函数式接口
 */
public class FunctionInterface {
    public static void main(String[] args) throws Exception {
        Runnable runnable = ()-> System.out.println(1);
        processing(runnable);
        processing(()-> System.out.println(2));
        processing(()->{});

        Callable<String> fetch = fetch();
        System.out.println(fetch.call());

        Apple apple = new Apple(1,"green");
        Apple apple2 = new Apple(2,"green");
        Predicate<Apple> predicate = (Apple a) -> a.getWeight()>0;
        System.out.println(predicate);
    }

    public static void processing(Runnable runnable){
        runnable.run();
    }

    public static Callable<String> fetch() {
        return () -> "Tricky example ;-)";
    }
}
