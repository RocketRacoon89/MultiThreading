package main;

public class Test17 {
    public static void main(String[] args) throws InterruptedException {
        T17 t17 = new T17(0);

        Thread thread1 = new Thread(t17);
        Thread thread2 = new Thread(t17);

            thread2.start();
            thread1.start();


        thread1.join();
        thread2.join();

        System.out.println(t17.a);
    }
}

class T17 implements Runnable{
    volatile int a;
    public T17(int a) {
        this.a=a;
    }

    @Override
    public void run() {
        synchronized (this) {
            for (int i = 0; i < 15000000; i++) {
                a++;
            }
        }

    }
}
