package com.trainings.nio.pathmethods;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 
 * @author Pavel Seda
 *
 */
public class PathGetFileNameExample {

	public static void main(String[] args) {
		Path p1 = Paths.get("/home/trainings.txt");
		System.out.println(p1.getFileName());
	}
}