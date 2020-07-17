package com.intersect;

public class Test {
    public static void main(String[] args) {
        Object o = new Object();
        int maxCount = 10;
        Object oa = new Object();
        Object ob = new Object();
        Object oc = new Object();

        synchronized(ob) {

        }
        synchronized(oc) {
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                synchronized(o) {
                    synchronized(oa) {
                        System.out.print("A");
                    }
                    ob.notify();
                }

            }, "A").start();

            new Thread(() -> {
                synchronized(o) {
                    synchronized(ob) {
                        System.out.print("B");
                    }
                    oc.notify();
                }

            }, "B").start();

            new Thread(() -> {
                synchronized(o) {
                    synchronized(oc) {
                        System.out.print("C ");
                    }
                }

            }, "C").start();

        }
    }
}
