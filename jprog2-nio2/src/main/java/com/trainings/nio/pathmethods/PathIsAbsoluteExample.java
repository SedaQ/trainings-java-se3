package com.trainings.nio.pathmethods;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 
 * @author Pavel Seda
 *
 */
public class PathIsAbsoluteExample {

	public static void main(String[] args) {
		Path p1 = Paths.get("C:\\trainings");
		System.out.println("This path is absolute: " + p1.isAbsolute());
	}

}
