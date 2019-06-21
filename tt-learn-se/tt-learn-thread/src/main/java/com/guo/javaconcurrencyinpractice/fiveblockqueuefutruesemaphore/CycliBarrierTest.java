package com.guo.javaconcurrencyinpractice.fiveblockqueuefutruesemaphore;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**p84 TODO 没看懂
 * 栅栏和闭锁和类似，闭锁是等待事件，栅栏是等待其他线程
 *
 * @Description:
 * @Author: guo
 * @CreateDate: 2019/4/7
 * @UpdateUser:
 */
public class CycliBarrierTest {
    private final Board mainBoard;
    private final CyclicBarrier barrier;
    private final Worker[] workers;

    public CycliBarrierTest(Board board) {
        this.mainBoard = board;
        int count = Runtime.getRuntime().availableProcessors();
        this.barrier = new CyclicBarrier(count,
                new Runnable() {
                    @Override
                    public void run() {
                        mainBoard.commitNewValues();
                    }});
        this.workers = new Worker[count];
        for (int i = 0; i < count; i++) {
            workers[i] = new Worker(mainBoard.getSubBoard(count, i));
        }
    }

    private class Worker implements Runnable {
        private final Board board;

        public Worker(Board board) { this.board = board; }

        @Override
        public void run() {
            while (!board.hasConverged()) {
                for (int x = 0; x < board.getMaxX(); x++) {
                    for (int y = 0; y < board.getMaxY(); y++) {
                        board.setNewValue(x, y, computeValue(x, y));
                    }
                }
                try {
                    //等待其他线程
                    barrier.await();
                } catch (InterruptedException ex) {
                    return;
                } catch (BrokenBarrierException ex) {
                    return;
                }
            }
        }

        private int computeValue(int x, int y) {
            // Compute the new value that goes in (x,y)
            return 0;
        }
    }

    public void start() {
        for (Worker worker : workers) {
            new Thread(worker).start();
        }
        mainBoard.waitForConvergence();
    }


    interface Board {
        int getMaxX();
        int getMaxY();
        int getValue(int x, int y);
        int setNewValue(int x, int y, int value);
        void commitNewValues();
        boolean hasConverged();
        void waitForConvergence();
        Board getSubBoard(int numPartitions, int index);
    }

    public static void main(String[] args) {
        CycliBarrierTest testCycliBarrier = new CycliBarrierTest(new Board() {
            @Override
            public int getMaxX() {
                return 2;
            }

            @Override
            public int getMaxY() {
                return 2;
            }

            @Override
            public int getValue(int x, int y) {
                return x+y;
            }

            @Override
            public int setNewValue(int x, int y, int value) {
                return x+y+value;
            }

            @Override
            public void commitNewValues() {
                System.out.println("commitNewValues");
            }

            @Override
            public boolean hasConverged() {
                return false;
            }

            @Override
            public void waitForConvergence() {
                System.out.println("waitForConvergence");
            }

            @Override
            public Board getSubBoard(int numPartitions, int index) {
                return this;
            }
        });
        testCycliBarrier.start();
    }
}


