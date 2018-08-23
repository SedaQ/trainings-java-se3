package com.trainings.multithreading.collections;

import java.util.*;
import java.util.concurrent.*;

/**
 * 
 * @author Pavel Šeda
 *
 */
public class SynchronizedCollections {

	public static void main(String[] args) {
		List<String> notSynchronizedList = new ArrayList<>();
		List<String> synchronizedList = Collections.synchronizedList(notSynchronizedList);
		List<String> synchronizedArrayList = new CopyOnWriteArrayList<>();

		Set<String> notSynchronizedSet = new TreeSet<>();
		Set<String> synchronizedSet = new ConcurrentSkipListSet<>();
		Set<String> synchronizedSet2 = new CopyOnWriteArraySet<>();

		Map<Integer, String> notSynchronizedMap = new HashMap<>();
		Map<Integer, String> synchronizedMap = new ConcurrentHashMap<>();
		Map<Integer, String> synchronizedMap2 = new ConcurrentSkipListMap<>(); // TreeMap
	}
}
