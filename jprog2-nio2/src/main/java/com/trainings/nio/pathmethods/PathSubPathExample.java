package com.trainings.nio.pathmethods;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 
 * @author Pavel Seda
 *
 */
public class PathSubPathExample {

	public static void main(String[] args) {
		Path path = Paths.get("C:\\trainings\\java\\path.image");
		System.out.println("Path is: " + path);
		System.out.println("Subpath from 0 to 3 is: " + path.subpath(0, 3));
		System.out.println("Subpath from 1 to 3 is: " + path.subpath(1, 3));
		System.out.println("Subpath from 1 to 2 is: " + path.subpath(1, 2));
	}

}
