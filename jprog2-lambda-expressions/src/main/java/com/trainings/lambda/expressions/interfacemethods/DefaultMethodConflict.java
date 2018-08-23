package com.trainings.lambda.expressions.interfacemethods;

/**
 * 
 * @author Pavel Šeda
 *
 */
public class DefaultMethodConflict implements A, B {

	@Override
	public void someMethod() {
		A.super.someMethod();
	}

}

interface A {
	default void someMethod() {
		// something
	}
}

interface B {
	default void someMethod() {
		// something else
	}
}
