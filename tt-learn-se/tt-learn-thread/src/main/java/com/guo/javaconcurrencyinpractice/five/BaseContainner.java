package com.guo.javaconcurrencyinpractice.five;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

public class BaseContainner<T,B> implements Executor {
    public   List<T> vector = new Vector<>();
    public  ArrayList<T> arrayList = new ArrayList<>();
    public Map<T,B> currentMap = new ConcurrentHashMap<>();

    @Override
    public void execute(Runnable command) {
        new Thread(command).start();
    }
}
