package com.trainings.nio.newstreammethods;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * @author Pavel Seda
 *
 */
public class FilesLinesExample {

	public static void main(String[] args) {
		printFileContent();

		System.out.println();

		storeFile();
	}

	private static void printFileContent() {
		Path path = Paths.get("./src/main/resources/test-files/file-to-read.txt");
		try {
			Files.lines(path).forEach(System.out::println);
		} catch (IOException e) {
		}
	}

	private static List<String> storeFile() {
		Path path = Paths.get("./src/main/resources/test-files/file-to-read.txt");
		try {
			return Files.lines(path).collect(Collectors.toList());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
