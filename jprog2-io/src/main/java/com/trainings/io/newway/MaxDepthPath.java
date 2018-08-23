package com.trainings.io.newway;

import java.io.IOException;
import java.nio.file.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author Pavel Šeda
 * 
 */
public class MaxDepthPath {

	public static void main(String[] args) {
		Path start = Paths.get(".");
		int maxDepth = 5;
		try (Stream<Path> stream = Files.walk(start, maxDepth, FileVisitOption.FOLLOW_LINKS)) {
			stream.map(String::valueOf)
					// .filter(path -> path.endsWith(".java"))
					.forEach(System.out::println);
		} catch (IOException ex) {
			Logger.getLogger(MaxDepthPath.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
