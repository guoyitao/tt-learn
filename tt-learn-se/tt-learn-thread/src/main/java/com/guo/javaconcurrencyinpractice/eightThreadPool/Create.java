package com.guo.javaconcurrencyinpractice.eightThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Create {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
    }
}
