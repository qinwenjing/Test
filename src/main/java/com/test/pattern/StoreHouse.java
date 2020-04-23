package com.test.pattern;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class StoreHouse {
    private static Integer count = 0;
    private static final Integer FULL = 10;
    private Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    class Producer implements Runnable {
        private int pid;

        public Producer(int pid) {
            this.pid = pid;
        }

        public void run() {
            lock.lock();
            try {
                while (count == FULL || pid < 0) {
                    // 等待不满的信号
                    notFull.await();
                }
                int total = count + pid;
                count = total > FULL ? FULL : total;
                // 发布不空的信息
                notEmpty.signalAll();
                System.out.println("Producer" + pid);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }
    }

    class Consumer implements Runnable {
        private int cid;

        public Consumer(int cid) {
            this.cid = cid;
        }

        public void run() {
            lock.lock();
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    while (count <= cid) {
                        System.out.println("Consumer" + cid + " waiting");
                        // 等待不空的信息
                        notEmpty.await();
                    }
                    count = count - cid;
                    // 发布不满的信息
                    notFull.signal();
                    System.out.println("Consumer" + cid + " consuming data ");

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        StoreHouse storeHouse = new StoreHouse();
        new Thread(storeHouse.new Producer(1)).start();
        new Thread(storeHouse.new Consumer(1)).start();
        new Thread(storeHouse.new Producer(2)).start();
        new Thread(storeHouse.new Consumer(2)).start();
        new Thread(storeHouse.new Producer(3)).start();
        new Thread(storeHouse.new Consumer(3)).start();
        new Thread(storeHouse.new Producer(4)).start();
        new Thread(storeHouse.new Consumer(4)).start();
        new Thread(storeHouse.new Producer(5)).start();
        new Thread(storeHouse.new Consumer(5)).start();


    }
}
