package com.guo.stream;

import com.guo.DataClass;
import org.junit.Test;

import java.util.Comparator;
import java.util.stream.Stream;

public class Distinct_Sort_Peek_1_5 extends DataClass {

    //Distinct 产生一个去重后的流
    @Test
    public void test3(){
        Stream<String> distinct = stringList.stream().distinct();

        print(distinct);
    }

    //Sort reversed()翻转
    @Test
    public void test1(){
        Stream<String> words = stringList.stream();

        Stream<String> sorted = words.sorted(Comparator.comparing(String::length).reversed());

        print(sorted);
    }

    //peek 产生一个新的流 在这个流里面 进行操作不改变 原流
    @Test
    public void test2(){
        Stream<Integer> peek = intList.stream().peek(integer -> integer++);
        print(peek,"    ");
    }


}
