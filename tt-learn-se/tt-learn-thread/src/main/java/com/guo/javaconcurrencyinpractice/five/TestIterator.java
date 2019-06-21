package com.guo.javaconcurrencyinpractice.five;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 解决在迭代的同时对容器增删改查
 * <p>
 * 迭代深拷贝后的对象
 *
 * @Description:
 * @Author: guo
 * @CreateDate: 2019/4/5
 * @UpdateUser:
 */
public class TestIterator extends BaseContainner<Integer,String> {

    public TestIterator() {
        this.arrayList = new ArrayList(1000000);
    }

    public List<Integer> deepCopy(List<Integer> list) {
        ArrayList<Integer> objects = new ArrayList<>();
        objects.addAll(list);
        return objects;
    }

    public static void main(String[] args) {
        TestIterator testIterator = new TestIterator();
        for (int i = 0; i < 1000000; i++) {
            testIterator.arrayList.add(i);
        }

//        new Thread(() -> {
//
//            synchronized (testIterator.arrayList) {
//                Iterator<Integer> iterator = testIterator.deepCopy(testIterator.arrayList).iterator();
//                while (iterator.hasNext()) {
//                    System.out.println("迭代：" + iterator.next());
//                }
//            }
//        }).start();
//
//
//        new Thread(() -> {
//            synchronized (testIterator.arrayList) {
//            for (int i = 0; i < 10000; i++) {
//                testIterator.arrayList.remove(i);
//            }
//            }
//
//        }).start();

        new Thread(() -> {
                Iterator<Integer> iterator = testIterator.deepCopy(testIterator.arrayList).iterator();
                while (iterator.hasNext()) {
                    System.out.println("迭代：" + iterator.next());
                }

        }).start();


        new Thread(() -> {
                for (int i = 0; i < 10000; i++) {
                    testIterator.arrayList.remove(i);
                }
        }).start();

    }
}
