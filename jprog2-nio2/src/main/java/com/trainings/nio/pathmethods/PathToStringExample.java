package com.trainings.nio.pathmethods;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 
 * @author Pavel Seda
 *
 */
public class PathToStringExample {

	public static void main(String[] args) {
		Path p1 = Paths.get("/home/trainings");
		// automatically invokes toString() method
		System.out.println("Name of the path is: " + p1);
	}
}
