package com.guo;

import lombok.Data;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class ZaTest {
    private String name;



    public static void main(String[] args) {

        AtomicInteger a = new AtomicInteger(3);
        test1(a);
        System.out.println(a);

    }


    public static void test1(AtomicInteger i){
        i.incrementAndGet();
    }
}
