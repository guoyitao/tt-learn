package com.guo.javaconcurrencyinpractice.sevencancel;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.*;

interface CancellableTask<T> extends Callable<T> {
    void cancel();
    RunnableFuture<T> newTask();
}

class CancellingExecutor extends ThreadPoolExecutor{

    public CancellingExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        if (callable instanceof CancellableTask){
            return ((CancellableTask<T>) callable).newTask();
        }
        return super.newTaskFor(callable);
    }
}

abstract class SocketUsingTask<T> implements CancellableTask{
    private Socket socket;

    protected synchronized void setSocket(Socket socket){
        this.socket = socket;
    }

    @Override
    public void cancel() {
        try {
            if (socket != null){
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public RunnableFuture newTask() {
        return new FutureTask(this){
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                try {
                    SocketUsingTask.this.cancel();
                } finally {
                    return super.cancel(mayInterruptIfRunning);
                }

            }
        };
    }
}

public class SocketCancleTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.submit(()->{});
    }
}
