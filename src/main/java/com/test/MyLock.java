package com.test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyLock implements Lock {
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    private static class ReentrantLock2 extends ReentrantLock{

    }

    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static class Sync extends AbstractQueuedSynchronizer{

    }
    public void lock() {

    }

    public void lockInterruptibly() throws InterruptedException {

    }

    public boolean tryLock() {
        return false;
    }

    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    public void unlock() {

    }

    public Condition newCondition() {
        return null;
    }
}
