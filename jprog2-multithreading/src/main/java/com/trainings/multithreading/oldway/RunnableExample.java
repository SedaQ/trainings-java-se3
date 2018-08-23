package com.trainings.multithreading.oldway;

/**
 * Example of creating thread using Runnable interface (old way).
 * 
 * Since Java 8 Runnable is @FunctionalInterface (one abstract method)
 * 
 * @author Pavel Šeda
 *
 */
public class RunnableExample implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 3; i++)
			System.out.println("Printing record: " + i);
	}

	public static void main(String[] args) {
		// (new Thread(new RunnableExample())).start();
		Thread t1 = new Thread(new RunnableExample());
		t1.start();
		// do not call t1.run(); !!!
	}

}
