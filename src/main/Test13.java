package main;

import java.util.concurrent.CompletableFuture;

public class Test13 {
    public static void main(String[] args) throws InterruptedException {
        Test13RET tr = new Test13RET(15);
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(tr::calc);
        //Thread.sleep(1000);
        Integer s = null;
        System.out.println(future.join());
    }
}

class Test13RET {
    Integer a;
    public Test13RET(Integer a) {
        this.a=a;
    }
    Integer calc() {
        return a*10;
    }
}
