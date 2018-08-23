package com.trainings.multithreading.parallelstreams;

import java.util.UUID;
import java.util.stream.Stream;

/**
 *
 * @author Pavel Šeda
 * 
 */
public class ReduceExample {

    public static void main(String[] args) {
        Stream<String> reduceExample = Stream.generate(() -> UUID.randomUUID().toString()).limit(10);
        //reduceExample.reduce(accumulator).ifPresent(s -> System.out.println(s));
        //String s = reduceExample.map(Object::toString).collect(Collectors.joining(","));

        reduceExample.reduce(",", String::concat);

    }
}
