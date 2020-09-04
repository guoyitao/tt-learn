package com.guo.javaconcurrencyinpractice.outofthisP35;

import java.util.EventListener;

//unsafe P35
public class ThisEscape {

    public final int id;
    public final String name;
    public ThisEscape(EventSource<EventListener> source) {
        id = 1;
        source.registerListener(new EventListener() {
            public void onEvent(Object obj) {
                System.out.println("id: "+ThisEscape.this.id);
                System.out.println("name: "+ThisEscape.this.name);
            }
        });
        name = "flysqrlboy";

    }
}