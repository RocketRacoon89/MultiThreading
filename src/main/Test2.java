package main;

public class Test2 {
    public static void main(String[] args) throws InterruptedException {
        Test2Ex test2Ex = new Test2Ex();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                test2Ex.first();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                test2Ex.second();
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                test2Ex.third();
            }
        });
        thread1.start();
        while(true) {if(!thread1.isAlive()) {thread2.start();  break;}}
        while(true) {if(!thread2.isAlive()) {thread3.start(); break;}}




    }
}

class Test2Ex {
    public void first() { System.out.print("First");}

    public void second()  { System.out.print("Second");  }

    public void third()  { System.out.print("Third"); }
}
