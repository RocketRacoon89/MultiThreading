package main;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

public class Test5 {

    public static void main(String[] args)  throws InterruptedException, IllegalThreadStateException  {
        Foo5 foo = new Foo5();

        Runnable run1 = new Runnable() {
            @Override
            public void run() {
                try {
                    foo.first(this);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable run2 = new Runnable() {
            @Override
            public void run() {
                try {
                    foo.second(this);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable run3 = new Runnable() {
            @Override
            public void run() {
                try {
                    foo.third(this);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread1 = new Thread(run1);
        Thread thread2 = new Thread(run2);
        Thread thread3 = new Thread(run3);

        thread3.start();
        thread1.start();
        thread2.start();
}
}
class Foo5 {

    public int threadNumber;

    public Foo5() {
        this.threadNumber = 1;
    }

     synchronized public void first(Runnable run) throws InterruptedException {
        while(threadNumber!=1) {
            wait();
        }
        System.out.println("First "+Thread.currentThread());
        threadNumber++;
        notifyAll();
    }

    synchronized public void second(Runnable run) throws InterruptedException {
        while(threadNumber!=2) {
            wait();
        }
        System.out.println("Second "+Thread.currentThread());
        threadNumber++;
        notifyAll();
    }

    synchronized public void third(Runnable run) throws InterruptedException {
        while(threadNumber!=3) {
            wait();
        }
        System.out.println("Third "+Thread.currentThread());
    }

}
