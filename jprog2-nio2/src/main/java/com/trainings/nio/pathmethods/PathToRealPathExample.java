package com.trainings.nio.pathmethods;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * 
 * @author Pavel Seda
 *
 */
public class PathToRealPathExample {

	public static void main(String[] args) {
		try {
			System.out.println(Paths.get(".").toRealPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
