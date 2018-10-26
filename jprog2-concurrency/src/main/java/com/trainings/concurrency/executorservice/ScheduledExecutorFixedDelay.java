package com.trainings.concurrency.executorservice;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author Pavel Seda
 *
 */
public class ScheduledExecutorFixedDelay {

	public static void main(String[] args) {
		ScheduledExecutorService service = null;
		try {
			service = Executors.newSingleThreadScheduledExecutor();
			service.scheduleWithFixedDelay(() -> System.out.println("Make a coffee"), 10, 10, TimeUnit.SECONDS);
		} finally {
			if (service != null)
				service.shutdown();
		}
	}

}
