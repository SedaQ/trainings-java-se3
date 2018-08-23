package com.trainings.io.newway;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 * @author Pavel Šeda
 *
 */
public class PathExample {

	public static void main(String[] args) throws URISyntaxException {
		PathExample pE = new PathExample();

		Path testFile = Paths.get(pE.getClass().getClassLoader().getResource("test.txt").toURI());

//		System.out.println(testFile.toAbsolutePath());
//
//		System.out.println(testFile.getParent().getParent());
//		System.out.println(testFile.getRoot());

//        pE.walkAllFile();
//        pE.findErrorsInLog();
//        pE.findErrorsInLogUsingPath(testFile);
		pE.readAllLines(testFile).forEach(System.out::println);
//        pE.readAllLinesEfficiently(testFile).forEach(System.out::println);
//		pE.readErrorsInLogWithTrimSolution(testFile);
//
//		pE.findFirstLineContainingError(testFile);

	}

	private void findFirstLineContainingError(Path path) {
		System.out.println("Finding particular information in file.");
		try (Stream<String> files = Files.lines(path)) {
			Optional<String> firstError = files.filter(line -> line.contains("ERROR")).findFirst();
			System.out.println(firstError.get());
		} catch (IOException e) {
		}
	}

	private void findErrorsInLog() {
		try (BufferedReader bReader = new BufferedReader(new FileReader(
				"C:\\Users\\Student\\Documents\\NetBeansProjects\\JavaApplication1\\gopas-trainings\\src\\main\\java\\com\\gopas\\trainings\\java\\io\\test.txt"))) {
			bReader.lines().peek(System.out::println).filter(line -> line.contains("ERROR"))
					.forEach(line -> System.out.println("Line with error: " + line));
		} catch (IOException io) {
		}
	}

	private void findErrorsInLogUsingPath(Path path) {
		System.out.println("Finding particular information in file.");
		try (Stream<String> files = Files.lines(path)) {
			files.filter(line -> line.contains("ERROR")).forEach(line -> System.out.println(line));
		} catch (IOException e) {
		}
	}

	private List<String> readAllLines(Path path) {
		System.out.println("Read all lines.");
		try {
			return Files.readAllLines(path);
		} catch (IOException e) {
			throw new IllegalArgumentException("..");
		}
	}

	private List<String> readAllLinesEfficiently(Path path) {
		System.out.println("Read all lines.");
		try (Stream<String> file = Files.lines(path)) {
			return file.collect(Collectors.toCollection(ArrayList::new));
		} catch (IOException e) {
			throw new IllegalArgumentException("..");
		}
	}

	private void walkAllFile() {
		try (Stream<Path> files = Files.walk(Paths.get("."))) {
			files.forEach(line -> System.out.println(line));
		} catch (Exception e) {
			System.out.println("Message: " + e.getMessage());
		}
	}

	private void readErrorsInLogWithTrimSolution(Path path) {
		System.out.println("Finding particular information in file.");
		try (Stream<String> files = Files.lines(path)) {
			files.filter(line -> line.contains("ERROR")).map(String::trim).forEach(line -> System.out.println(line));
		} catch (IOException e) {
		}
	}

}
