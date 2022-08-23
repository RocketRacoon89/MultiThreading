package main;


import java.util.concurrent.Semaphore;

public class Test5 {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1, true);

        Test5Ex test5Ex = new Test5Ex(semaphore);

        Thread thread1 =new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                test5Ex.first();
                semaphore.release();
            }
        });

        Thread thread2 =new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                test5Ex.second();
                semaphore.release();
            }
        });

        Thread thread3 =new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                test5Ex.third();
                semaphore.release();
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();

    }
}

class Test5Ex{

    Semaphore semaphore;

    public Test5Ex(Semaphore semaphore) {
        this.semaphore=semaphore;
    }

    public void first() {
        System.out.println("First");
    }

    public void second() {
        System.out.println("Second");
    }

    public void third() {
        System.out.println("Third");
    }

}