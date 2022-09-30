package main;

import java.util.concurrent.atomic.AtomicInteger;

public class Test18 {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(10);

        atomicInteger.compareAndSet();
    }
}
