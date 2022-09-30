package main;

import java.util.concurrent.atomic.AtomicInteger;

public class Test14 {
    public static void main(String[] args) {
        AtomicInteger ai1 = new AtomicInteger(5);
        AtomicInteger ai2 = new AtomicInteger(10);
        AtomicInteger ai3 = new AtomicInteger(15);

        ai1.compareAndExchange(5,17);
        System.out.println(ai1);

    }
}
