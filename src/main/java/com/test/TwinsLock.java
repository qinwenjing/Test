package com.test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import com.sun.corba.se.impl.orbutil.concurrent.Sync;

public class TwinsLock implements Lock {
    Map<String, String> map = new ConcurrentHashMap<String, String>();
    DelayQueue queue001  = new DelayQueue();
    AtomicInteger integer = new AtomicInteger(13) ;
    AtomicBoolean bb = new AtomicBoolean();

    private static final class Sync extends AbstractQueuedSynchronizer{

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
