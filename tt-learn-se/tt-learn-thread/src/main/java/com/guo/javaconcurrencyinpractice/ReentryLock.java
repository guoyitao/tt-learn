package com.guo.javaconcurrencyinpractice;

public class ReentryLock {

    public static synchronized void doSomeThing(){
        System.out.println("123");
    }

    static class A{
        static synchronized void doSome(){
            doSomeThing();
        }
    }

    public static void main(String[] args) {
        A.doSome();
    }
}
