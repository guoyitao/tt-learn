package com.guo.stream;

import com.guo.DataClass;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Collectors1_8 extends DataClass {

    @Test
    public void test1(){
        //变成list
        List<String> collect = stringList.stream().collect(Collectors.toList());
        System.out.println("collect(Collectors.toList()" + collect);

        //链接字符串
        String collect1 = stringList.stream().collect(Collectors.joining());
        System.out.println("collect(Collectors.joining())" + collect1);

        //控制种类
        HashSet<String> collect2 = stringList.stream().collect(Collectors.toCollection(HashSet::new));
        System.out.println("Collectors.toCollection(HashSet::new)" + collect2);

    }
}
