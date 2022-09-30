package main;

import java.util.concurrent.Semaphore;

public class Test7 {
    public static void main(String[] args) {
        Calc7 calc7 = new Calc7(35);

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    calc7.mul();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    calc7.div();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

//        thread2.start();
//        thread1.start();

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    calc7.umn();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    calc7.del();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread5 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    calc7.otn();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread3.start();
        thread4.start();
        thread5.start();

    }
}

class Calc7 {
    volatile int x;
    Semaphore semaphore = new Semaphore(1);
    public Calc7(int x) {
        this.x=x;

    }

    public void mul() throws InterruptedException {
        semaphore.acquire();
        while (x<100) {
            x++;
            System.out.println(x);
        }
        semaphore.release();
    }

    public void div() throws InterruptedException {
        semaphore.acquire();
        while (x>5) {
            x=x-15;
            System.out.println(x);
        }
        semaphore.release();
    }

    public void umn() throws InterruptedException {
        semaphore.acquire();
        x=x*100;
        System.out.println(x);
        semaphore.release();
    }

    public void del() throws InterruptedException {
        semaphore.acquire();

            x=x/2;
            System.out.println(x);

        semaphore.release();
    }

    public void otn() throws InterruptedException {
        semaphore.acquire();

            x=x-1;
            System.out.println(x);

        semaphore.release();
    }
}
