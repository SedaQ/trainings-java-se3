package com.trainings.io;

import java.io.*;

/**
 * 
 * @author Pavel Seda
 *
 */
public class StreamOperations {

	public static void main(String[] args) {
		StreamOperations so = new StreamOperations();
		try {
			so.markingTheStream();
		} catch (IOException io) {
		}
	}

	/**
	 * Read file .... use just FileInputStream and then wrap it to BufferedInputStream instance, check
	 * what it does. Try methods mark and read and reset on that stream.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void markingTheStream() throws FileNotFoundException, IOException {
		try (InputStream in =
				new BufferedInputStream(new FileInputStream(getClass().getClassLoader().getResource("marking-the-stream.txt").getFile()));) {
			in.skip(4);
			if (in.markSupported()) {
				in.mark(4);
				System.out.print((char) in.read());
				System.out.print((char) in.read());
				in.reset();
				System.out.print((char) in.read());
			} else {
				System.out.println("mark is not supported.");
			}
		}
	}
}
