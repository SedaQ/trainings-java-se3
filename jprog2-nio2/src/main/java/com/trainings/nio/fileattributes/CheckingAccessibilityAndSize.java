package com.trainings.nio.fileattributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 
 * @author Pavel Seda
 *
 */
public class CheckingAccessibilityAndSize {

	public static void main(String[] args) {
		Path p1 = Paths.get("./src/main/resources", "file-attributes/java-image.jpg");
		System.out.println(Files.isReadable(p1));
		System.out.println(Files.isExecutable(p1));
		try {
			System.out.println(Files.size(p1));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
