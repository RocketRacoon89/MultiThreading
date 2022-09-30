package main;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test6 {

    public static void main(String[] args) {
        Foo6 foo6 = new Foo6();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    foo6.first();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    foo6.second();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    foo6.third();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread3.start();
        thread1.start();
        thread2.start();
    }


}

class Foo6{

    private int threadNumber;
    private Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public Foo6() {
        this.threadNumber=1;
    }

    public void first() throws InterruptedException {
        lock.lock();
        try {
            while(this.threadNumber!=1) {
                condition.await();
            }
            System.out.println("First");
            threadNumber++;
            condition.signalAll();
        }
        finally {
            lock.unlock();
        }

    }

    public void second() throws InterruptedException {
        lock.lock();
        try{
            while (this.threadNumber!=2) {
                condition.await();
            }
            System.out.println("Second");
            threadNumber++;
            condition.signalAll();}
        finally {
            lock.unlock();
        }

    }

    public void third() throws InterruptedException {
        lock.lock();
        try{
            while (this.threadNumber!=3) {
                condition.await();
            }
            System.out.println("Third");
            condition.signalAll();
        }
        finally {
            lock.unlock();
        }

    }

}