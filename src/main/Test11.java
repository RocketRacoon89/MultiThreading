package main;

//CompletableFuture My Test.

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Test11 {
    public static void main(String[] args) {

        Test_cal11 tc = new Test_cal11(5);
        Test_cal11 tc1 = new Test_cal11(52);

        CompletableFuture<Void> cf1 = CompletableFuture.runAsync(new Runnable() {

            @Override
            public void run() {
                System.out.println("Work");
            }
            }).thenAccept((r)->{System.out.println("dsadas");});

        CompletableFuture<Void> cf11 = CompletableFuture.runAsync(()->System.out.println("Hello"));

        CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(tc::calculate).thenApply((res)->(res*21));


        CompletableFuture<Void> cf3 = cf2.thenCompose((res) -> CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                tc.setX(res);
            }
        }));

        CompletableFuture<Integer> cf4 = cf3.thenCompose((res) -> CompletableFuture.supplyAsync(tc::calculate)).thenApply((res)->(res/2));

        CompletableFuture<Integer> sup = CompletableFuture.supplyAsync(tc1::calculate);

        CompletableFuture<Integer> cf5 = sup.thenApplyAsync((res)->(res*999312/120-54+5432-2352+999*100/20+421-2132+9999-212*3));
        CompletableFuture<Integer> cf6 = sup.thenApplyAsync((res)->(res*0));
        //CompletableFuture<Integer> cf5 = CompletableFuture.supplyAsync(tc1::calculate).thenApplyAsync((res)->(res*99996));
        //CompletableFuture<Integer> cf6 = CompletableFuture.supplyAsync(tc1::calculate).thenApplyAsync((res)->(res*1));
        CompletableFuture<?> winner = CompletableFuture.anyOf(cf5, cf6);

        //CompletableFuture<Integer> cf7 = (CompletableFuture<Integer>) CompletableFuture.supplyAsync(tc1::calculate).thenAccept((res)->{tc1.setX(res);}).thenApply(tc1::calculate);
        //CompletableFuture<Void> cf8 = cf7.thenCompose((res) -> CompletableFuture.supplyAsync({tc1.setX(res);}));

        try {
//            cf1.get();
            Integer f = cf2.get();
            System.out.println(f+" cf2");
//            Integer d = cf4.get();
//            System.out.println(d+" cf4");
//            System.out.println(cf7.get()+" cf7");
//
//            System.out.println(winner.get()+" winner");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}

class Test_cal11 {
    Integer x;
    public Test_cal11(Integer x){
        this.x=x;
    }

    public Integer calculate() {
        return x*10;
    }

    public void setX(Integer x) {
        this.x=x;
    }
}
