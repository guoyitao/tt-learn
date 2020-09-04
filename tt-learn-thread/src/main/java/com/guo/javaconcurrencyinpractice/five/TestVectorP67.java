package com.guo.javaconcurrencyinpractice.five;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

public class TestVectorP67 extends BaseContainner<Integer,String>{

    public  Integer getLast(){
        synchronized (arrayList) {
            int lastIndex = arrayList.size() - 1;
            return arrayList.get(lastIndex);
        }
    }

    public  Integer removeLast(){
        synchronized (arrayList) {
            int lastIndex = arrayList.size() - 1;
            return arrayList.remove(lastIndex);
        }
    }

    public static void main(String[] args) {
        TestVectorP67 a = new TestVectorP67();
        for (int i = 0; i < 1000; i++) {
            a.arrayList.add(i);
        }

        for (int i = 0; i < 1000; i++) {
            new Thread(()->{
                System.out.println(a.getLast());
            }).start();
            new Thread(()->{
                System.out.println(a.removeLast());
            }).start();
        }

    }
}
