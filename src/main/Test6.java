package main;

import java.util.concurrent.Semaphore;

public class Test6 {
    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(1, true);

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.acquire();
                    first();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.acquire();
                    second();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.acquire();
                    third();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();

    }

    public static void first() {
        System.out.println("First");
    }

    public static void second() {
        System.out.println("Second");
    }

    public static void third() {
        System.out.println("Third");
    }
}
