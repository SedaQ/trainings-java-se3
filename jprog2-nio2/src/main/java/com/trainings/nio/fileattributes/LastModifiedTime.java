package com.trainings.nio.fileattributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.time.Instant;

/**
 * 
 * @author Pavel Seda
 *
 */
public class LastModifiedTime {

	public static void main(String[] args) {
		try {
			Path p1 = Paths.get("./src/main/resources", "file-attributes/java-image.jpg");
			Files.setLastModifiedTime(p1, FileTime.from(Instant.now()));
			System.out.println(Files.getLastModifiedTime(p1));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
