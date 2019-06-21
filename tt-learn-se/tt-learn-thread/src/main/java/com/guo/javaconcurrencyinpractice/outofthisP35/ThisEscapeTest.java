package com.guo.javaconcurrencyinpractice.outofthisP35;

import java.util.EventListener;

public class ThisEscapeTest {

    public static void main(String[] args) {
        EventSource<EventListener> source = new EventSource<EventListener>();
        ListenerRunnable listRun = new ListenerRunnable(source);
        Thread thread = new Thread(listRun);
        thread.start();
        ThisEscape escape1 = new ThisEscape(source);
    }
}
