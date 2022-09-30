package main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Test12 {
    //volatile int b = 0;
    AtomicInteger a;
    public Test12(int a) {
        this.a = new AtomicInteger(a);

    }
    public static void main(String[] args) throws InterruptedException {
        Test12 test12 = new Test12(0);
//        test12.increment();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<150000; i++) {
                    if(!test12.incrementCAS()) {
                        i--;
                    };
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<150000; i++) {
                    if(!test12.incrementCAS()) {
                        i--;
                    };
                }
            }
        });


        System.out.println(test12.a+" start");
 //       System.out.println(test12.b+" start");
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(test12.a+" end");
 //       System.out.println(test12.b+" end");



    }

    void increment() {
        int current = a.get();
        int next = current + 1;


        a.compareAndSet(current,next);
//        System.out.println(a);
//        a.getAndAdd(1);
//        System.out.println(a);
//       a.incrementAndGet();
//        System.out.println(a);
//        this.b=b+1;
    }

    boolean incrementCAS() {
        int current = a.get();
        int next = current + 1;


        return (a.compareAndSet(current,next));
    }


}
