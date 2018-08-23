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
public class CheckResults {

	private static int counter = 0;

	public static void main(String[] args) {
		withoutPolling();
		// polling();
	}

	/**
	 * Question: How many times will the while() loop in this code execute and
	 * output Not reached yet?
	 * 
	 * Answer: The answer is, we don’t know! It could output zero, ten, or a
	 * million times. If our thread scheduler is particularly poor, it could
	 * operate infinitely!
	 * 
	 */
	private static void withoutPolling() {
		new Thread(() -> {
			for (int i = 0; i < 500; i++)
				CheckResults.counter++;
		}).start();
		while (CheckResults.counter < 100) {
			System.out.println("Not reached yet");
		}
		System.out.println("Reached!");
	}

	private static void polling() throws InterruptedException {
		new Thread(() -> {
			for (int i = 0; i < 500; i++)
				CheckResults.counter++;
		}).start();
		while (CheckResults.counter < 100) {
			System.out.println("Not reached yet");
			try {
				Thread.sleep(1000); // 1 SECOND
			} catch (InterruptedException ie) {
			}
		}
		System.out.println("Reached!");
	}
}