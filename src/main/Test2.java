package main;

public class Test2 {
    public static void main(String[] args) throws InterruptedException {
        Test2Ex test2Ex = new Test2Ex();

        Thread tread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                test2Ex.first();
            }
        });

        Thread tread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                test2Ex.second();
            }
        });

        Thread tread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                test2Ex.third();
            }
        });
        tread1.start();
        while(true) {if(!tread1.isAlive()) {tread2.start();  break;}}
        while(true) {if(!tread2.isAlive()) {tread3.start(); break;}}




    }
}

class Test2Ex {
    public void first() { System.out.print("First");}

    public void second()  { System.out.print("Second");  }

    public void third()  { System.out.print("Third"); }
}
