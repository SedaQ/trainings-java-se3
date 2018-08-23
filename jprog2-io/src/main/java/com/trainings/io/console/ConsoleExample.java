package com.trainings.io.console;

import java.io.Console;

/**
 *
 * To compile this app: javac ConsoleClassExample.java To run this app: java
 * ConsoleClassExample
 *
 * @author Pavel Šeda
 * 
 */
public class ConsoleExample {

	public static void main(String[] args) {

		Console console = System.console();
		if (console != null) {
			System.out.println("Your username please.");
			String username = console.readLine();
			System.out.println("Your password please.");
			char[] password = console.readPassword();
		}

	}
}
