package com.trainings.multithreading.parallelstreams;

import java.util.Arrays;

/**
 * 
 * Parallel streams can improve performance because they rely on the property
 * that many stream operations can be executed independently. By independent
 * operations, we mean that the results of an operation on one element of a
 * stream do not require or impact the results of another element of the stream.
 * For example, in the previous example, each call to processRecord() can be
 * executed separately, without impacting any other invocation of the method. As
 * another example, consider the following lambda expression supplied to the
 * map() method, which maps the stream contents to uppercase strings:
 * 
 * @author Pavel Šeda
 *
 */
public class IndependentOperations {

	public static void main(String[] args) {
		independentOperation();
	}

	/**
	 * When using streams avoid any lambda expressions that can produce side
	 * effects!
	 */
	private static void independentOperation() {
		Arrays.asList("jackal", "kangaroo", "lemur").parallelStream().map(s -> s.toUpperCase())
				.forEach(System.out::println);

	}
}
