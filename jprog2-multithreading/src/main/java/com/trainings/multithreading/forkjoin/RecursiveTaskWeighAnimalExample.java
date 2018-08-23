package com.trainings.multithreading.forkjoin;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 
 * Applying the fork/join framework requires us to perform three steps:
    1. Create a ForkJoinTask.
    2. Create the ForkJoinPool.
    3. Start the ForkJoinTask.
 * 
 * @author Pavel Šeda
 *
 */
public class RecursiveTaskWeighAnimalExample extends RecursiveTask<Double> {
    private int start;
    private int end;
    private Double[] weights;
    
    public RecursiveTaskWeighAnimalExample(Double[] weights, int start, int end) {
        this.start = start;
        this.end = end;
        this.weights = weights;
    }
    
    protected Double compute() {
        if(end-start <= 3) {
            double sum = 0;
            for(int i=start; i<end; i++) {
                weights[i] = (double) new Random().nextInt(100);
                sum += weights[i];
            }
            return sum;
        } else {
            int middle = start+((end-start)/2);
            RecursiveTask<Double> otherTask = new RecursiveTaskWeighAnimalExample(weights,start,middle);
            otherTask.fork();
        return new RecursiveTaskWeighAnimalExample(weights,middle,end).compute() + otherTask.join();
       }
    }
    
    public static void main(String[] args){
        Double[] weights = new Double[10];
        ForkJoinTask<Double> task = new RecursiveTaskWeighAnimalExample(weights,0,weights.length);
        ForkJoinPool pool = new ForkJoinPool();
        Double sum = pool.invoke(task);
        System.out.println("Sum: "+sum);    
    }
}
