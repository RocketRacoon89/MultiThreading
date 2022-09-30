package main;

import java.util.concurrent.*;

public class Test16 {
    public static void main(String[] args) {
//        ExecutorService es1 = Executors.newFixedThreadPool(15);
        ExecutorService es1 = Executors.newCachedThreadPool();
        AddCalc16 ac16 = new AddCalc16(5);
        AddCalc16 ac161 = new AddCalc16(3);
        AddCalc16 ac162 = new AddCalc16(4);
        AddCalc16 ac163 = new AddCalc16(6);
        Future<Integer> f1 = es1.submit(ac16);
        Future<Integer> f2 = es1.submit(ac161);
        Future<Integer> f3 = es1.submit(ac162);
        Future<Integer> f4 = es1.submit(ac163);

        try{
            System.out.println(es1);
            int b = f1.get();
            int c = f2.get();
            int e = f3.get();
            int f = f4.get();
            System.out.println(b+" "+c+" "+e+" "+f);
            System.out.println(es1);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            es1.shutdown();

        }


    }
}

class AddCalc16 implements Callable<Integer> {
    int a;

    public AddCalc16(int a) {
        this.a=a;
    }


    @Override
    public Integer call() throws Exception {
        for(int i=1; i<20; i++) {
            a*=i;
        }
        return a;
    }
}