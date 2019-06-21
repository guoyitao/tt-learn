package com.guo.javaconcurrencyinpractice.five;

import java.util.Map;

public class TestCurrentMap extends BaseContainner<String,String> {

    public TestCurrentMap() {
        for (int i = 0; i < 10; i++) {
            super.currentMap.put("key"+i,"value"+i);
        }

    }

    public void removeAll(Map<?,?> map){
        for (Object o : map.keySet()) {
            System.out.println("remove:" +map.remove(o));
        }
    }

    public void iterater(Map<?,?> map){
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "     :" + entry.getValue());
        }
    }

    public static void main(String[] args) {
        TestCurrentMap testCurrentMap = new TestCurrentMap();


        testCurrentMap.execute(()->{
            testCurrentMap.removeAll(testCurrentMap.currentMap);
        });
        testCurrentMap.execute(()->{
            testCurrentMap.iterater(testCurrentMap.currentMap);
        });

    }
}
