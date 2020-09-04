package com.guo.javaconcurrencyinpractice.fiveblockqueuefutruesemaphore;

import lombok.Data;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

@Data
class BoundedHashSet<T>{
    private final Set<T> set;
    private final Semaphore semaphore;

    public BoundedHashSet(int bound) {
        this.set = Collections.synchronizedSet(new HashSet<>());
        semaphore = new Semaphore(bound);
    }

    public boolean add(T t) throws InterruptedException {
        //如果没有许可（bound 这里初始是10）剩余就阻塞，直到有许可
        //许可-1
        semaphore.acquire();
        boolean waAdded = false;
        try {
            waAdded = set.add(t);
            return waAdded;
        } finally {
            if (!waAdded){
                //添加失败就释放许可
                //许可+1
                semaphore.release();
            }
        }
    }
    //不阻塞，只返回删除成功或者失败
    public boolean remove(T t){
        boolean wasRemove = set.remove(t);
        if (wasRemove){
            //成功删除就是否许可
            semaphore.release();
        }
        return wasRemove;
    }
}

public class SemaphoreTest{

    public static void main(String[] args) {
        BoundedHashSet<String> boundedHashSet = new BoundedHashSet<>(10);

        new Thread(()->{
            for (int i = 0; i < 20; i++) {

                try {
                    Thread.sleep(2000);
                    boundedHashSet.add("add"+i);
                    System.out.println("add：   "+i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();

        new Thread(()->{
            int count =0;
            while (true){
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                boolean add = boundedHashSet.remove("add" + count);
                System.out.println("第" + count++ +"次remove：" + add);
            }
        }).start();
    }
}
