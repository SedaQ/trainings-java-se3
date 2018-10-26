package com.trainings.nio.fileattributes;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 
 * @author Pavel Seda
 *
 */
public class CommonAttributesIsDirectoryIsRegularFileIsSymbolicLink {

	public static void main(String[] args) {
		Path p1 = Paths.get("./src/main/resources", "file-attributes/java-image.jpg");
		System.out.println("Is directory: " + Files.isDirectory(p1));
		System.out.println("Is symbolic link: " + Files.isSymbolicLink(p1));
		System.out.println("Is regular file: " + Files.isRegularFile(p1));
	}
}
