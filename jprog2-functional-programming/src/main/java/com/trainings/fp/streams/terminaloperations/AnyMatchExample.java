package com.trainings.fp.streams.terminaloperations;

import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Returns information about how the stream pertains to the predicate.
 * 
 * <pre>
	</code>
		boolean anyMatch(Predicate <? super T> predicate)
	</code>
 * </pre>
 * 
 * 
 * @author Pavel Seda
 *
 */
public class AnyMatchExample {

	public static void main(String[] args) {
		Stream<String> employees = Stream.of("Pavel Seda", "John Smith", "John Doe");
		Predicate<String> empSeda = emp -> emp.equals("Pavel Seda");
		boolean b1 = employees.anyMatch(empSeda);
		System.out.println(b1);
	}

}
