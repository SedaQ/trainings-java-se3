package com.trainings.nio.pathmethods;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 
 * @author Pavel Seda
 *
 */
public class PathResolveExample {

	public static void main(String[] args) {
		final Path p1 = Paths.get("/home/trainings/../holidays");
		final Path p2 = Paths.get("pavel-seda");
		System.out.println(p1.resolve(p2));

		final Path p3 = Paths.get("/home/trainings/../holidays");
		final Path p4 = Paths.get("/home/trainings/");
		System.out.println(p3.resolve(p4));
	}

}
