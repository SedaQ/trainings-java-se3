package com.trainings.multithreading.parallelstreams;

import java.util.Arrays;

/**
 * 
 * @author Pavel Šeda
 * 
 */
public class ProcessingTasksInParallel {

	public static void main(String[] args) {
		forEachListOfInts();
		forEachListOfIntsInParallel();
		orderingForEachInParallel();
	}

	/**
	 * As you might expect, the results are ordered and predictable because we
	 * are using a serial stream.
	 */
	private static void forEachListOfInts() {
		Arrays.asList(1, 2, 3, 4, 5, 6).stream().forEach(s -> System.out.print(s + " "));
	}

	/**
	 * With a parallel stream, the forEach() operation is applied across
	 * multiple elements of the stream concurrently
	 */
	private static void forEachListOfIntsInParallel() {
		Arrays.asList(1, 2, 3, 4, 5, 6).parallelStream().forEach(s -> System.out.print(s + " "));
	}

	/**
	 * Since we have ordered the results, we have lost some of the performance
	 * gains of using a parallel stream, so why use this method? You might be
	 * calling this method in a section of your application that takes both
	 * serial and parallel streams, and you need to ensure that the results are
	 * processed in a particular order. Also, stream operations that occur
	 * before/after the forEachOrdered() can still gain performance improvements
	 * for using a parallel stream.
	 */
	private static void orderingForEachInParallel() {
		Arrays.asList(1, 2, 3, 4, 5, 6).parallelStream().forEachOrdered(s -> System.out.print(s + " "));
	}
}
