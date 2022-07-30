package main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Test3 {
    public static void main(String[] args) {
        Test3Ex t3 = new Test3Ex();
        ScheduledExecutorService ses1 = Executors.newScheduledThreadPool(1);
        ses1.schedule(new Runnable() {
            @Override
            public void run() {
                t3.first();
            }
        }, 0, TimeUnit.SECONDS);
        ses1.shutdown();

        ScheduledExecutorService ses2 = Executors.newScheduledThreadPool(1);
        ses2.schedule(new Runnable() {
            @Override
            public void run() {
                t3.second();
            }
        }, 1, TimeUnit.SECONDS);
        ses2.shutdown();
        ScheduledExecutorService ses3 = Executors.newScheduledThreadPool(1);
        ses3.schedule(new Runnable() {
            @Override
            public void run() {
                t3.third();
            }
        }, 2, TimeUnit.SECONDS);
        ses3.shutdown();
    }


}

class Test3Ex{

    public void first() {
                System.out.print("First");
    }

    public void second() {
                System.out.print("Second");
    }

    public void third() {
                System.out.print("Third");
    }

}
