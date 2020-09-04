package com.guo.aqslearn;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MyLock implements Lock {

    private Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    /**
     * @Description:
     * @Author: guo
     * @CreateDate: 2019/6/9
     * @UpdateUser:
     */
    private class Sync extends AbstractQueuedSynchronizer{

        //使用CAS设置同步器的状态 期望值如果是0  到 新值 1
        //CAS其实就是乐观锁
        //CAS比较和交换的过程必须是原子操作，比较和交换的时候其他线程不能打断他
        //CAS使用的是native方法调用了现代CPU里面实现的CAS
        //CAS判断现在值是不是0，如果不是0就说明另外线程把这个值已经改变了，里面有个死循环会不断的检查（自旋），
        //CAS判断现在值是不是0，如果不是0就说明另外线程把这个值已经改变了
        //如果是0那就把值改为1，就说明当前线程占有了这把锁

        //CAS坏处：自旋会一直消耗cpu资源，高并发环境下竞争高会疯狂消耗cpu，建议锁竞争不高的时候用cas
        //好处:不会调用操作系统级别的锁，synchronized(){}是操作系统级别的

        //如果获取锁失败返回了false说明竞争不到，就会，加入到一个叫CLH的等待队列里面，排队去拿，排队也没拿到就中断当前线程
        @Override
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0,1)){
                //设置排他线程为当前线程
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        //释放锁
        @Override
        protected boolean tryRelease(int arg) {
            //如果当前线程没有持有锁
            if (! isHeldExclusively()){
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        //当前线程是否持有锁
        @Override
        protected boolean isHeldExclusively() {
            //获得 tryAcquire（int arg）设置过排他锁的线程 是否等于当前线程
            return getExclusiveOwnerThread() == Thread.currentThread();
        }
    }

}
