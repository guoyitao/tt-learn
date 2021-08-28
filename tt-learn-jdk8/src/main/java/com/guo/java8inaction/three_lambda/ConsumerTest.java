package com.guo.java8inaction.three_lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * java.util.function.Consumer<T>定义了一个名叫accept的抽象方法，它接受泛型T
 * 的对象，没有返回（void）。你如果需要访问类型T的对象，并对其执行某些操作，就可以使用
 * 这个接口。比如，你可以用它来创建一个forEach方法，接受一个Integers的列表，并对其中
 * 每个元素执行操作。在下面的代码中，你就可以使用这个forEach方法，并配合Lambda来打印
 * 列表中的所有元素。
 */
public class ConsumerTest {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("1", "2", "3", "4", "5");
        forEach(list,(String a)-> System.out.println(a));

        // Predicate返回了一个boolean
        Predicate<String> p = s -> list.add(s);
        // Consumer返回了一个void
        Consumer<String> b = s -> list.add(s);
    }

    public static <T> void forEach(List<T> list, Consumer<T> c){
        for(T i: list){
            c.accept(i);
        }
    }
}
