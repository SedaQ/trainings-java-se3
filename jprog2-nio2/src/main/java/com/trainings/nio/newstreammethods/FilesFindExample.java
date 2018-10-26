package com.trainings.nio.newstreammethods;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * 
 * @author Pavel Seda
 *
 */
public class FilesFindExample {

	public static void main(String[] args) {
		Path path = Paths.get(".");
		try {
			Stream<Path> streamPath = Files.find(path, 10, (p, a) -> p.toString().endsWith(".java") && a.lastModifiedTime().toMillis() > 0);
			streamPath.forEach(System.out::println);
		} catch (Exception e) {
		}
	}
}
