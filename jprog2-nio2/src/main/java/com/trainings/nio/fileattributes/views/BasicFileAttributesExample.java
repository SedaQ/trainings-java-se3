package com.trainings.nio.fileattributes.views;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

/**
 * 
 * @author Pavel Seda
 *
 */
public class BasicFileAttributesExample {

	public static void main(String[] args) {
		Path p1 = Paths.get("./src/main/resources", "file-attributes/java-image.jpg");

		readingAttributes(p1);

		modifyingAttributes(p1);
	}

	private static void readingAttributes(Path p1) {
		try {
			BasicFileAttributes fileAttributes = Files.readAttributes(p1, BasicFileAttributes.class);
			System.out.println("Creation time of a file: " + fileAttributes.creationTime());
			System.out.println("Is path a directory? " + fileAttributes.isDirectory());
			System.out.println("Is path a regular file? " + fileAttributes.isRegularFile());
			System.out.println("Is path a symbolic link? " + fileAttributes.isSymbolicLink());
			System.out.println("Path not a file, directory, nor symbolic link? " + fileAttributes.isOther());
			System.out.println("Last modified date/time: " + fileAttributes.lastModifiedTime());
			System.out.println("Last accessed date/time: " + fileAttributes.lastAccessTime());
			System.out.println("Size (in bytes): " + fileAttributes.size());
			System.out.println("Unique file identifier (if available): " + fileAttributes.fileKey());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void modifyingAttributes(Path p1) {
		try {
			BasicFileAttributeView view = Files.getFileAttributeView(p1, BasicFileAttributeView.class);
			BasicFileAttributes data;
			data = view.readAttributes();
			FileTime lastModifiedTime = FileTime.fromMillis(data.lastModifiedTime().toMillis() + 10_000);
			view.setTimes(lastModifiedTime, null, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
