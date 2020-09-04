package com.guo.stream;

import com.guo.DataClass;
import org.junit.Test;

import java.util.Optional;

public class Operation1_6____1_7 extends DataClass {

    @Test
    public void test1_6(){
        //最大值
        Optional<Integer> max = intList.stream().max(Integer::compareTo);
        Integer integer = max.get();
        System.out.println("最大值  " + integer+ intList);

        //第一个值
        Integer integer1 = intList.stream().findFirst().get();
        System.out.println("findFirst()  "+integer1);

        //找出所有能被2整除的数
        Optional<Integer> any = intList.stream().parallel().filter(s -> s / 2 == 0).findAny();
        System.out.println("//找出所有能被2整除的数   "+any.get());

        //是否存在匹配
        boolean isMatch = stringList.stream().anyMatch(s -> s.startsWith("哦"));
        System.out.println("//是否存在匹配    " + isMatch);
    }

    @Test
    public void test1_7_orEles(){
        //空的Optional
        Optional<String> empty = Optional.empty();
        System.out.println("///如果没有匹配默认值   "+empty.orElse("!"));


    }





}
