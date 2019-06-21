package com.guo.mashibing;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

public class TestMap {

    static Map<String,Integer> map = new ConcurrentHashMap<>(); //331
//  static  Map<String,Integer> map = new ConcurrentSkipListMap<>(); //419
//   static Map<String,Integer> map = new HashMap<>(); //error
//  static  Map<String,Integer> map = new Hashtable<>(); //399

    public static void main(String[] args) {
        Random random = new Random();
        Thread[] thread = new Thread[600];
        CountDownLatch latch = new CountDownLatch(thread.length);
        long start = System.currentTimeMillis();
        for (int i = 0; i < thread.length; i++) {
            Thread thread1 = new Thread(() -> {
                for (int j = 0; j < 40000; j++) {
                    map.put("a" + random.nextInt(10000), random.nextInt(10000) );
                }
                latch.countDown();
            });
            thread1.start();
            thread[i] = thread1;
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }
}
