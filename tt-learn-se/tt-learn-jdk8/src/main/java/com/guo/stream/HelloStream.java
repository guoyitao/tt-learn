package com.guo.stream;

import com.guo.DataClass;
import org.junit.Test;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



/**
 * @Description: 核心2 1.1  数数
 * @Author: guo
 * @CreateDate: 2019/4/1
 * @UpdateUser:
 */
public class HelloStream extends DataClass {

    @Test
    public void test1() throws IOException {
        String string = new String(Files.readAllBytes(Paths.get("F:\\code\\tt-learn\\tt-learn-se\\tt-learn-jdk8\\src\\main\\resources\\words.txt")), StandardCharsets.UTF_8);
        List<String> list = Arrays.asList(string.split("\r\n"));
        long l = System.nanoTime();

        startTime();
        long count = list.parallelStream().filter(w -> w.length() > 2).count();
        endTime();

    }

    @Test
    public void test2() throws IOException {
        String string = new String(Files.readAllBytes(Paths.get("F:\\code\\tt-learn\\tt-learn-se\\tt-learn-jdk8\\src\\main\\resources\\words.txt")), StandardCharsets.UTF_8);
        List<String> list = Arrays.asList(string.split("\r\n"));
        long l = System.nanoTime();

        startTime();
        long count = list.stream().filter(w -> w.length() > 2).count();
        endTime();
    }
}
