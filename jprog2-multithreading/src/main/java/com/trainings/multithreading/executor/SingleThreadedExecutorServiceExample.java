package com.trainings.multithreading.executor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author Pavel Šeda
 *
 */
public class SingleThreadedExecutorServiceExample {

	public static void main(String[] args) {
		singleThreadedExecuteMethod();
		singleThreadedSubmitMethod();
	}

	/**
	 * Instantiating single threaded ExecutorService.
	 * 
	 * With a single-thread executor, results are guaranteed to be executed in
	 * the order in which they are added to the executor service. Notice that
	 * the end text is output while our thread executor tasks are still running.
	 * This is because the main() method is still an independent thread from the
	 * ExecutorService, and it can perform tasks while the other thread is
	 * running.
	 */
	private static void singleThreadedExecuteMethod() {
		ExecutorService service = null;
		try {
			service = Executors.newSingleThreadExecutor();
			System.out.println("begin");
			service.execute(() -> System.out.println("Printing zoo inventory"));
			service.execute(() -> {
				for (int i = 0; i < 3; i++)
					System.out.println("Printing record: " + i);
			});
			service.execute(() -> System.out.println("Printing zoo inventory"));
			System.out.println("end"); // this is running in main thread
		} finally {
			if (service != null)
				service.shutdown();
		}
	}

	/**
	 * Executes a Callable task at some point in the future and returns a Future
	 * representing the pending results of the task
	 */
	private static void singleThreadedSubmitMethod() {
		ExecutorService service = null;
		try {
			service = Executors.newSingleThreadExecutor();
			System.out.println("begin");
			service.submit(() -> System.out.println("Printing zoo inventory"));
			service.submit(() -> {
				for (int i = 0; i < 3; i++)
					System.out.println("Printing record: " + i);
			});
			Future<Integer> result = service.submit(() -> 30 + 11);
			try {
				System.out.println(result.get());
				// Retrieves the result of a task, waiting endlessly if it is
				// not yet available.
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}

			System.out.println("end"); // this is running in main thread
		} finally {
			try {
				service.awaitTermination(1, TimeUnit.MINUTES);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// Check whether all tasks are finished
			if (service.isTerminated())
				System.out.println("All tasks finished");
			else
				System.out.println("At least one task is still running");
		}

	}
}
