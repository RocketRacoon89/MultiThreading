package main;

import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class Test15 {
    public static void main(String[] args) {
        int[] s = {10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000,
                10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000,
                10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000};
        ForkJoinPool fjp = new ForkJoinPool();

        CalcZP task = new CalcZP(s, 0, s.length);

        System.out.println(fjp.invoke(task));

    }
}

class CalcZP extends RecursiveTask<Integer> {
    int[] sal;
    int start;
    int end;

    public CalcZP(int[] array, int s, int e) {
        sal=array;
        start=s;
        end=e;
    }


    @Override
    protected Integer compute() {
        int response = 0;
        int sumMonth = sal.length;
        if((end-start)<13) {
            for(int i=start; i<end; i++) {
                response +=sal[i];
            }
        } else {
            int middle = start+end/2; //24+0/2=12

            CalcZP c1 = new CalcZP(sal, start, middle);
            CalcZP c2 = new CalcZP(sal,  middle, end);

            c1.fork();
            c2.fork();

            response = c1.join()+c2.join();
        }
        System.out.println(sal.length);
        return response;
    }
}
