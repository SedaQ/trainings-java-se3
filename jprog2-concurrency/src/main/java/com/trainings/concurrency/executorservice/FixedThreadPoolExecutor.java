package com.trainings.concurrency.executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @author Pavel Seda
 *
 */
public class FixedThreadPoolExecutor {

	public static void main(String[] args) {
		ExecutorService service = null;
		try {
			service = Executors.newFixedThreadPool(5);
			System.out.println("START");
			service.execute(() -> System.out.println("Printing employees"));
			service.execute(() -> {
				for (int i = 0; i < 5; i++)
					System.out.println("Employee record: " + i);
			});
			service.execute(() -> System.out.println("Printing employees"));
			System.out.println("END");
		} finally {
			if (service != null)
				service.shutdown();
		}
	}

}
