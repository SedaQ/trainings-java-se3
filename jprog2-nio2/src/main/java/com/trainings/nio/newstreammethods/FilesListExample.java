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
public class FilesListExample {

	public static void main(String[] args) {
		try {
			Path path = Paths.get("./src/main/resources/test-files");
			Files.list(path).filter(p -> !Files.isDirectory(p)).map(p -> p.toAbsolutePath()).forEach(System.out::println);
		} catch (IOException io) {
		}
	}
}
