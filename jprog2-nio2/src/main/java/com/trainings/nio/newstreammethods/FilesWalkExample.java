package com.trainings.nio.newstreammethods;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 
 * @author Pavel Seda
 *
 */
public class FilesWalkExample {

	public static void main(String[] args) {
		walk();
	}

	private static void walk() {
		Path path = Paths.get(".");
		try {
			Files.walk(path).filter(p -> p.toString().endsWith(".java")).forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void walkWithMaxLevel() {
		Path path = Paths.get(".");
		try {
			Files.walk(path, Integer.MAX_VALUE).filter(p -> p.toString().endsWith(".java")).forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
