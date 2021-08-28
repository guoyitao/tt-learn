package com.guo.java8inaction.three_lambda;

import com.guo.java8inaction.two.Apple;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * 你还可以进一步简化你的代码。Java编译器会从上下文（目标类型）推断出用什么函数式接
 * 口来配合Lambda表达式，这意味着它也可以推断出适合Lambda的签名，因为函数描述符可以通
 * 过目标类型来得到。这样做的好处在于，编译器可以了解Lambda表达式的参数类型，这样就可
 * 以在Lambda语法中省去标注参数类型。换句话说，Java编译器会像下面这样推断Lambda的参数
 * 类型：
 */
public class 类型推断 {
    public static void main(String[] args) {
        List<Apple> apples = Apple.getApples();
        System.out.println(filter(apples, a -> a.getWeight() > 100));


        Comparator<Apple> c =
                (a1, a2) -> a1.getWeight().compareTo(a2.getWeight());
        apples.sort(c);
        System.out.println(apples);
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
}
