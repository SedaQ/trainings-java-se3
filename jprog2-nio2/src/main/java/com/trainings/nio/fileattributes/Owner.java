package com.trainings.nio.fileattributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserPrincipal;

/**
 * 
 * @author Pavel Seda
 *
 */
public class Owner {

	public static void main(String[] args) {
		try {
			Path p1 = Paths.get("./src/main/resources", "file-attributes/java-image.jpg");
			System.out.println(Files.getOwner(p1).getName());
			// set new owner
			UserPrincipal owner = p1.getFileSystem().getUserPrincipalLookupService().lookupPrincipalByName("SedaQ");
			Files.setOwner(p1, owner);
			System.out.println(Files.getOwner(p1).getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
