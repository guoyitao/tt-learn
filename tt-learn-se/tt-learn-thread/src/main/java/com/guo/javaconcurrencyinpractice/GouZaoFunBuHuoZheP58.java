package com.guo.javaconcurrencyinpractice;


public class GouZaoFunBuHuoZheP58 {

    public static void main(String[] args) {
        final SafePoint2 originalSafePoint = new SafePoint2(1, 1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                originalSafePoint.set(2, 2);
                System.out.println("Original : " + originalSafePoint.toString());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                SafePoint2 copySafePoint = new SafePoint2(originalSafePoint);
                System.out.println("Copy : " + copySafePoint.toString());
            }
        }).start();
    }

}

// 线程安全类
class SafePoint {

    private int x, y;

    private SafePoint(int[] a) {
        this(a[0], a[1]);
    }

    public SafePoint(SafePoint p) {
        this(p.get());
    }

    public SafePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public synchronized int[] get() {
        return new int[] { x, y };
    }

    public synchronized void set(int x, int y) {
        this.x = x;
        // Simulate some resource intensive work that starts EXACTLY at this
        // point, causing a small delay
        try {
            Thread.sleep(10 * 100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}

// 线程不安全类
class SafePoint2 {

    private int x;
    private int y;

    public SafePoint2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public SafePoint2(SafePoint2 safePoint2) {
        this(safePoint2.x, safePoint2.y);
    }

    public synchronized int[] get() {
        return new int[] { x, y };
    }

    public synchronized void set(int x, int y) {
        this.x = x;
        // Simulate some resource intensive work that starts EXACTLY at this
        // point, causing a small delay
        try {
            Thread.sleep(10 * 100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
