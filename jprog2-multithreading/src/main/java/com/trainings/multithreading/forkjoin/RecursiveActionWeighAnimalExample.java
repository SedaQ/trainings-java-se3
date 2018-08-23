package com.trainings.multithreading.forkjoin;

import java.util.*;
import java.util.concurrent.*;

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
public class RecursiveActionWeighAnimalExample extends RecursiveAction {
    private int start;
    private int end;
    private Double[] weights;
    
    public RecursiveActionWeighAnimalExample(Double[] weights, int start, int end) {
        this.start = start;
        this.end = end;
        this.weights = weights;
    }
    protected void compute() {
        if(end-start <= 3)
            for(int i=start; i<end; i++) {
                weights[i] = (double)new Random().nextInt(100);
                System.out.println("Animal Weighed: "+i);
        }
        else {
            int middle = start+((end-start)/2);
            System.out.println("[start="+start+",middle="+middle+",end="+end+"]");
            invokeAll(new RecursiveActionWeighAnimalExample(weights,start,middle),
            new RecursiveActionWeighAnimalExample(weights,middle,end));
        }
    }
    
    public static void main(String[] args) {
        Double[] weights = new Double[10];
        ForkJoinTask<?> task = new RecursiveActionWeighAnimalExample(weights,0,weights.length);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(task);
        // Print results
        System.out.println();
        System.out.print("Weights: ");
        Arrays.asList(weights).stream().forEach(
        d -> System.out.print(d.intValue()+" "));
    }
}