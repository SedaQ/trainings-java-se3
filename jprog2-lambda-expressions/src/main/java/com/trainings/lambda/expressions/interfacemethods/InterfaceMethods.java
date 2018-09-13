package com.trainings.lambda.expressions.interfacemethods;

/**
 * 
 * @author Pavel Šeda
 *
 */
public interface InterfaceMethods {

	static void printStaticMethod() {
		System.out.println("static method..");
	}

	default void printDefaultMethod() {
		System.out.println("default method..");
	}

	// since Java 9
	/*
	 * private void printPrivateMethod() { System.out.println("private method.."); }
	 */

}
