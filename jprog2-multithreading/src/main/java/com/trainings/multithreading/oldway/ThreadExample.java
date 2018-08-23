package com.trainings.multithreading.oldway;

/**
 * 
 * In general, you should extend the Thread class only under very specific
 * circumstances, such as when you are creating your own priority-based thread.
 * In most situations, you should implement the Runnable interface rather than
 * extend the Thread class.
 * 
 * @author Pavel Šeda
 *
 */
public class ThreadExample extends Thread {

	public void run() {
		System.out.println("Printing something using user-defined thread.");
	}

	public static void main(String[] args) {
		// (new ThreadExample()).start();
		Thread t1 = new ThreadExample();
		t1.start();
	}
}
