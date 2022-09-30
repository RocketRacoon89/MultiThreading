package main;

import java.util.concurrent.Exchanger;

public class Test9 {
    public static void main(String[] args) {
        Exchanger<Integer> ex = new Exchanger<>();
        Thread thread1 = new Thread(new Test9_f(ex,3));
        Thread thread2 = new Thread(new Test9_e(ex,5));

        thread2.start();
        thread1.start();
    }
}

class Test9_f implements Runnable {
    Exchanger<Integer> exchanger;
    Integer a;
    Test9_f(Exchanger<Integer> exchanger, Integer a) {
        this.exchanger=exchanger;
        this.a=a;

    }
    public void run() {
        a=0;
        try {
            exchanger.exchange(a);
            System.out.println(a+" method F");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Test9_e implements Runnable {
    Exchanger<Integer> exchanger;
    Integer a;
    Test9_e(Exchanger<Integer> exchanger, Integer a) {
        this.exchanger=exchanger;
        this.a=a;

    }
    public void run() {
        a=a*5;
        try {
            exchanger.exchange(a);
            System.out.println(a+" method E");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}