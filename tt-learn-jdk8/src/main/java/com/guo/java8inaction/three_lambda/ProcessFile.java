package com.guo.java8inaction.three_lambda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


@FunctionalInterface
interface BufferedReaderProcessor {
    String process(BufferedReader b) throws IOException;
}

/*
行为参数化的例子
 */
public class ProcessFile {
    public static void main(String[] args) throws IOException {
        System.out.println(processFile());

        System.out.println(processFile((BufferedReader b) -> b.readLine() + b.readLine()));
        System.out.println(processFile((BufferedReader b) -> b.readLine() + b.readLine()+ b.readLine()));
    }

    public static String processFile() throws IOException {
        try (BufferedReader br =
                     new BufferedReader(new FileReader("OutFile.txt"))) {
            return br.readLine();
        }
    }

    //记得行为参数化  使用函数式接口来传递行为
    public static String processFile(BufferedReaderProcessor bufferedReaderProcessor) throws IOException {
        try (BufferedReader br =
                     new BufferedReader(new FileReader("Out1File.txt"))) {
            return bufferedReaderProcessor.process(br);
        }
    }
}
