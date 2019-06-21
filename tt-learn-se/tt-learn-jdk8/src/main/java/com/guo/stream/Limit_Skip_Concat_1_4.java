package com.guo.stream;

import com.guo.DataClass;
import org.junit.Test;

import java.util.stream.Stream;

public class Limit_Skip_Concat_1_4 extends DataClass {

    //截取前5个 包尾
    @Test
    public void test1(){
        Stream<Integer> limit = intList.stream().limit(5);
        print(limit);
    }
    //丢弃前5个 不包头 第一个是第n+1个
    @Test
    public void test2(){
        Stream<Integer> skip = intList.stream().skip(5);
        print(skip);
    }

    //链接两个流
    @Test
    public void test3(){
        Stream<Integer> limit = intList.stream().limit(5);
        Stream<Integer> skip = intList.stream().skip(5);
        //链接
        Stream<Integer> concat = Stream.concat(limit, skip);

        print(concat);
    }


}
