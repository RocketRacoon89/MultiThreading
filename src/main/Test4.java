package main;

import java.util.concurrent.CountDownLatch;

public class Test4 {

    public static void main(String[] args) throws InterruptedException {
        Test4Ex test4Ex = new Test4Ex(1);


        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                test4Ex.first();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    thread1.join();
                    test4Ex.second();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    thread1.join();
                    thread2.join();
                    test4Ex.third();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();
        System.out.println(test4Ex.x);




    }
}

class Test4Ex{

    volatile int x;

    public Test4Ex(int x) {
        this.x=x;
    }

    public void first() {
        System.out.println("First");
        x=x*10-2;
    }

    public void second() {
        System.out.println("Second");
        x=x*10+5;
    }

    public void third() {
        System.out.println("Third");
        x=x*2+1;
    }

}
