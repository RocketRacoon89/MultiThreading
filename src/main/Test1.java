package main;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test1{

    public static void main(String[] args) throws InterruptedException {
        TestEx1 testEx1 = new TestEx1();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                testEx1.first();
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    testEx1.second();
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    testEx1.third();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
  }


}

class TestEx1 {

    public void first() { System.out.print("First"); }

    public void second() throws InterruptedException { Thread.sleep(500); System.out.print("Second");  }

    public void third() throws InterruptedException { Thread.sleep(1000); System.out.print("Third"); }
}