package com.trainings.concurrency.concurrentcollections;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Pavel Seda
 *
 */
public class ObtainingSynchronizedCollectionExample {

	public static void main(String[] args) {
		Map<Integer, String> employees = new HashMap<>();
		employees.put(1, "pavel");
		employees.put(2, "petr");
		Map<Integer, String> employeesSync = Collections.synchronizedMap(employees);
		for (Integer key : employeesSync.keySet())
			employeesSync.remove(key);
	}
}
