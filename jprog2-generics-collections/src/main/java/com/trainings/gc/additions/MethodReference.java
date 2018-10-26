package com.trainings.gc.additions;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 
 * Method references have to be used in following formats:
 * 
 * <ul>
 * <li>Static methods</li>
 * <li>Instance methods on a particular instance</li>
 * <li>Instance methods on an instance to be determined at runtime</li>
 * <li>Constructors, e.g. Supplier interface</li>
 * </ul>
 * 
 * @author Pavel Seda
 *
 */
public class MethodReference {

	public static void main(String[] args) {
		Consumer<String> c = System.out::println;
		c.accept("Consumer printing something to console");

		Supplier<ArrayList<String>> al = ArrayList::new;
	}
}
