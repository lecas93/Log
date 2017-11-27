package Log;

import java.io.File;
import java.util.Date;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Bitacora {

	private static Bitacora bitacora;

	private String day, month, year;
	private int fileCounter;
	private String ruta;

	private Bitacora() {

	}

	public static synchronized Bitacora getInstance() {
		if (bitacora == null) {
			bitacora = new Bitacora();
		}
		return bitacora;
	}

	private void updateRuta() {
		String[] date = new Date().toString().split(" ");
		day = date[2];
		month = date[1];
		year = date[5];
		ruta = day + "-" + month + "-" + year + "_#" + fileCounter + ".txt";
	}

	private void checkFileSize() {
		updateRuta();
		File archivo = new File(ruta);
		if (archivo.exists()) {
			double kilobytes = (archivo.length() / 1024);
			if (kilobytes >= XMLParser.getFileSize()) {
				fileCounter++;
				checkFileSize();
			}
		}
	}

	public void writeRecord(String record) {
		fileCounter = 1;
		checkFileSize();
		File archivo = new File(ruta);
		BufferedWriter bw;
		try {
			if (!archivo.exists()) {
				archivo.createNewFile();
			}
			bw = new BufferedWriter(new FileWriter(archivo, true));
			bw.write(record + "\n");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getInformation() {
		/* Total number of processors or cores available to the JVM */
		System.out.println("Available processors (cores): " + Runtime.getRuntime().availableProcessors());

		/* Total amount of free memory available to the JVM */
		System.out.println("Free memory (bytes): " + Runtime.getRuntime().freeMemory());

		/* This will return Long.MAX_VALUE if there is no preset limit */
		long maxMemory = Runtime.getRuntime().maxMemory();
		/* Maximum amount of memory the JVM will attempt to use */
		System.out.println("Maximum memory (bytes): " + (maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory));

		/* Total memory currently available to the JVM */
		System.out.println("Total memory available to JVM (bytes): " + Runtime.getRuntime().totalMemory());

		/* Get a list of all filesystem roots on this system */
		File[] roots = File.listRoots();

		/* For each filesystem root, print some info */
		for (File root : roots) {
			System.out.println("File system root: " + root.getAbsolutePath());
			System.out.println("Total space (bytes): " + root.getTotalSpace());
			System.out.println("Free space (bytes): " + root.getFreeSpace());
			System.out.println("Usable space (bytes): " + root.getUsableSpace());
		}
	}
}