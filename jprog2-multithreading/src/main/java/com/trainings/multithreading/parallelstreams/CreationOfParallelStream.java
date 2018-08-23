package com.trainings.multithreading.parallelstreams;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 
 * <p>
 * By default, the number of threads available in a parallel stream is related
 * to the number of available CPUs in your environment. In order to increase the
 * thread count, you would need to create your own custom class.
 * </p>
 * 
 * <p>
 * The Stream interface includes a method isParallel() that can be used to test
 * if the instance of a stream supports parallel processing. Some operations on
 * streams preserve the parallel attribute, while others do not. For example,
 * the Stream.concat(Stream s1, Stream s2) is parallel if either s1 or s2 is
 * parallel. On the other hand, flatMap() creates a new stream that is not
 * parallel by default, regardless of whether the underlying elements were
 * parallel.
 * </p>
 * 
 * @author Pavel Šeda
 *
 */
public class CreationOfParallelStream {

	/**
	 * This one is using parallel() method on stream instance.
	 */
	private void firstWay() {
		Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5, 6).stream();
		Stream<Integer> parallelStream = stream.parallel();
	}

	/**
	 * This one is used on collections using parallelStream() method.
	 */
	private void secondWay() {
		Stream<Integer> parallelStream = Arrays.asList(1, 2, 3, 4, 5, 6).parallelStream();
	}
}
