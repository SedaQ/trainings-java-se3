package com.trainings.concurrency.managingprocesses.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

public class ParallelStreamForkJoin {

    public static void main(String[] args) {
//        IntStream.range(0, 10_000)
//                .forEach(System.out::println);

        ForkJoinPool fjp = new ForkJoinPool(100);

        fjp.submit(() -> {
        });
    }
}
