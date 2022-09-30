package main;

import java.util.concurrent.CountDownLatch;

public class Test8 {
    public static void main(String[] args) {
        CountDownLatch cdl1 = new CountDownLatch(1);
        CountDownLatch cdl2 = new CountDownLatch(2);
        Foo8 foo8 = new Foo8();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                foo8.first();
                cdl1.countDown();
                cdl2.countDown();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    foo8.second(cdl1);
                    cdl2.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    foo8.third(cdl2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread3.start();
        thread2.start();
        thread1.start();

    }

}

class Foo8 {
    public void first() {
        System.out.println("First");
    }

    public void second(CountDownLatch cdl) throws InterruptedException {
        cdl.await();
        System.out.println("Second");
    }

    public void third(CountDownLatch cdl) throws InterruptedException {
        cdl.await();
        System.out.println("Third");
    }
}