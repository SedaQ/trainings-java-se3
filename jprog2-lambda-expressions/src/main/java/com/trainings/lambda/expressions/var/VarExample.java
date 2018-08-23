package com.trainings.lambda.expressions.var;

import java.util.ArrayList;

/**
 * 
 * @author Pavel Šeda
 *
 */
public class VarExample {

	// private var myVariable;

	public static void main(String[] args) {
		VarExample vE = new VarExample();
		vE.useVar();
	}

	public void useVar() {
		var myList = new ArrayList<String>();
		myList.add("test");
		// myList.add(1);

		myList.forEach(System.out::println);
	}
}
