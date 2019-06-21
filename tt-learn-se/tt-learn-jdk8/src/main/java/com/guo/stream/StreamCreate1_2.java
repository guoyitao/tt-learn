package com.guo.stream;

import com.guo.DataClass;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamCreate1_2  extends DataClass {

    public StreamCreate1_2() throws IOException {
    }

    @Test
    public void test1() throws IOException {
        String string = new String(Files.readAllBytes(Paths.get("F:\\code\\tt-learn\\tt-learn-se\\tt-learn-jdk8\\src\\main\\resources\\words.txt")), StandardCharsets.UTF_8);
        List<String> list = Arrays.asList(string.split("\r\n"));

        Stream<String> split = Stream.of(string.split("\r\n"));

        Stream<String> as = Stream.of("asd,", "as");

        //数组流 arr from to
        Stream<String> stream = Arrays.stream(new String[]{"1"}, 0, 1);

        //空的流
        Stream<Object> empty = Stream.empty();
    }
    //无限流
    @Test
    public void test2(){

        Stream<String> generate = Stream.generate(() -> "Echo");

        Stream<Double> generate1 = Stream.generate(Math::random);

        /*
        *
        * 无限流包含种子，
        *
        * */
        Stream<BigInteger> iterate = Stream.iterate(BigInteger.ZERO, bigInteger -> bigInteger.add(BigInteger.ONE));

        generate1.forEach(s -> {
            System.out.println(s);
        });
    }


}
