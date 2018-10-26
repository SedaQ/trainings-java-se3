package com.trainings.nio.pathmethods;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 
 * @author Pavel Seda
 *
 */
public class PathGetNameCountAndGetNameExample {

	public static void main(String[] args) {
		Path p1 = Paths.get("/home/trainings");
		for (int i = 0; i < p1.getNameCount(); i++) {
			System.out.println("Element " + i + " is: " + p1.getName(i));
		}
	}

}
