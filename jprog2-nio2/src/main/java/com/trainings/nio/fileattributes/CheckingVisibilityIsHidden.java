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
public class CheckingVisibilityIsHidden {

	public static void main(String[] args) {
		try {
			Path p1 = Paths.get("./src/main/resources", "file-attributes/java-image.jpg");
			System.out.println(Files.isHidden(p1));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
