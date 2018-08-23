package com.trainings.multithreading.oldway;

/**
 * 
 * @author Pavel Šeda
 *
 */
public class BothCreated {

	public static void main(String[] args) {
		runThreeThreads();
		runThreeThreadsInWrongWay();
	}

	/**
	 * Question: What is the output of this one?
	 * 
	 * Answer: We do not know... until runtime
	 * 
	 */
	private static void runThreeThreads() {
		System.out.println("begin");
		(new ThreadExample()).start();
		(new Thread(new RunnableExample())).start();
		(new ThreadExample()).start();
		System.out.println("end");
	}

	/**
	 * Question: What is the output of this one?
	 * 
	 * Answer: It is invoked in one thread, so...
	 * 
	 */
	private static void runThreeThreadsInWrongWay() {
		new RunnableExample().run();
		(new Thread(new RunnableExample())).run();
		(new ThreadExample()).run();
	}
}
