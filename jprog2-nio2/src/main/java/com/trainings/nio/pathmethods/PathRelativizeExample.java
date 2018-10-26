package com.trainings.nio.pathmethods;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 
 * @author Pavel Seda
 *
 */
public class PathRelativizeExample {

	public static void main(String[] args) {
		Path path1 = Paths.get("trainings.txt");
		Path path2 = Paths.get("holidays.txt");
		System.out.println(path1.relativize(path2));
		System.out.println(path2.relativize(path1));

		System.out.println();

		Path path3 = Paths.get("C:\\trainings.txt");
		Path path4 = Paths.get("C:\\trainings\\holidays.txt");
		System.out.println(path3.relativize(path4));
		System.out.println(path4.relativize(path3));
	}

}
