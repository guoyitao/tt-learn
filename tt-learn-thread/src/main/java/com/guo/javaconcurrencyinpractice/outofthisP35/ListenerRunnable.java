package com.guo.javaconcurrencyinpractice.outofthisP35;

import java.util.EventListener;
import java.util.List;

public class ListenerRunnable implements Runnable {

    private EventSource<EventListener> source;

    public ListenerRunnable(EventSource<EventListener> source) {
        this.source = source;
    }

    @Override
    public void run() {
        List<EventListener> listeners = null;

        try {
            listeners = this.source.retrieveListeners();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for (EventListener listener : listeners) {
//            listener.onEvent(new Object());
        }
    }
}

