package com.guo.stream;

import com.guo.DataClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilterMapFlatMap1_3 extends DataClass {

    @Test
    public void test1(){
        //filter(Predicate<T>)
        Stream<String> stringStream = stringList.stream().filter(s -> s.length() > 1);
        //s 是流产生的结果
        stringStream.map(s -> s.substring(0,1));

        stringStream.collect(Collectors.toList()).forEach(System.out::println);
    }
    //FlatMap
    @Test
    public void test2(){
        List<String> list = Arrays.asList("a", "b","c");
        //使用map会返回嵌套map
        Stream<Stream<String>> streamStream = list.stream().map(s -> letter(s));
        //flatMap可以把它们摊平
        Stream<String> stringStream = list.stream().flatMap(s -> letter(s));


        stringStream.forEach(System.out::println);


    }
    public static Stream<String> letter(String s){
        List<String> result = new ArrayList<>();
        for (int i = 0; i <s.length() ; i++) {
            result.add(s+"1");
        }
        return result.stream();
    }




}
