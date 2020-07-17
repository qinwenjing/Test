package com.intersect;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// 通过下面的分析，可以类比如有一个消费队列，生产者和消费者同时操作这个队列的场景
public class Test6 {
    // 标志的作用是指示 执行不同逻辑的线程 按照顺序执行，
    volatile private static String flag = "A";
    // 加锁的目的是多个线程同时执行一段相同的代码，保证同一时刻只能有一个线程可以执行这段代码。
    // 看下面的for循环实际上是启动了30个线程去执行，只不过平均10个线程执行的是相同的一段逻辑
    // 此处加锁的目的是为了让10个执行相同逻辑的线程，只能有一个线程获得执行的权利
    private static ReentrantLock lock = new ReentrantLock();
    final private static Condition conditionA = lock.newCondition();
    final private static Condition conditionB = lock.newCondition();
    final private static Condition conditionC = lock.newCondition();

    public static void main(String[] args) {
        Runnable runnableA = new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    for (int i = 0; i < 10; i++) {
                        while (!"A".equals(flag)) {
                            conditionA.await();
                        }
                        System.out.print(Thread.currentThread().getName());
                        flag = "B";
                        conditionB.signal();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        };

        Runnable runnableB = new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    for (int i = 0; i < 10; i++) {
                        while (!"B".equals(flag)) {
                            conditionB.await();
                        }
                        System.out.print(Thread.currentThread().getName());
                        flag = "C";
                        conditionC.signal();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        };

        Runnable runnableC = new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    for (int i = 0; i < 10; i++) {
                        while (!"C".equals(flag)) {
                            conditionC.await();
                        }
                        System.out.print(Thread.currentThread().getName());
                        flag = "A";
                        conditionA.signal();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        };

        // 这个地方必须写成new Thread(runnableA).start()的形式，而不是 new Thread().start()的形式（不能在上面直接写一个线程然后start）

        new Thread(runnableA, "A").start();
        new Thread(runnableB, "B").start();
        new Thread(runnableC, "C").start();

    }
}
