package com.trainings.multithreading.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 
 * Oftentimes in Java, we need to schedule a task to happen at some future time.
 * We might even need to schedule the task to happen repeatedly, at some set
 * interval. For example, imagine that we want to check the supply of food for
 * zoo animals once an hour and fill it as needed. The ScheduledExecutorService,
 * which is a subinterface of ExecutorService, can be used for just such a task.
 * 
 * @author Pavel Šeda
 *
 */
public class SchedulingTasks {

	public static void main(String[] args) {
		scheduleMethod();
	}

	/**
	 * Note that we could implicitly cast an instance of
	 * ScheduledExecutorService to ExecutorService, although doing so would
	 * remove access to the scheduled methods that we want to use.
	 */
	private static void scheduleMethod() {
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		try {
			Runnable task1 = () -> System.out.println("Hello Zoo");
			Callable<String> task2 = () -> "Monkey";

			Future<?> result1 = service.schedule(task1, 10, TimeUnit.SECONDS);
			Future<?> result2 = service.schedule(task2, 8, TimeUnit.MINUTES);

			// The following example executes a Runnable task every minute,
			// following an initial five-minute delay:
			service.scheduleAtFixedRate(task1, 5, 1, TimeUnit.MINUTES);
			// On the other hand, the scheduleAtFixedDelay() method creates a
			// new task after the previous task has fi nished.
			service.scheduleWithFixedDelay(task1, 0, 2, TimeUnit.MINUTES);
		} finally {
			if (service != null) {
				service.shutdown();
			}
		}
	}
}
