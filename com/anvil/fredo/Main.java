package com.anvil.fredo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
	
	static long timeStart;// = System.currentTimeMillis();
	
	//static File file;
	static File directoryFiles[];
	static File parentDir;
	
	static long lngTotalBytes = 0;
	
	static String finalOutput;
	static String newline = System.lineSeparator();
	
	public static void main(String[] args) throws IOException {
			
		parentDir = new File(System.getProperty("user.dir"));
		
		//System.out.println(parentDir.getName() + ":");
		directoryFiles = parentDir.listFiles();
		
		timeStart = System.currentTimeMillis();
		countBytes(directoryFiles);
		
		
		StringBuilder sb = new StringBuilder("TOTAL BYTES: ");
		sb.append(lngTotalBytes + newline);
		sb.append("Kilobytes (KB) " + (lngTotalBytes / 1_024l) + newline);
		sb.append("\nMegabytes (MB): " + (lngTotalBytes / 1_048_576l) + newline);
		sb.append("\nGigabytes (GB): " + (lngTotalBytes / 1_073_741_824l) + newline);
		sb.append("\nELAPSED TIME: " + (System.currentTimeMillis() - timeStart) / 1000 + " seconds");
		
		finalOutput = sb.toString();	
		
		File outputFile = new File("Memory stored in this file.txt");
		outputFile.createNewFile();
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
		
		bw.write(finalOutput);
		
		bw.close();
		
	}
	
	static void countBytes(File directoryFiles[]) {
		
		for (File file : directoryFiles) {
			
			if (file.isFile()) {
				
				lngTotalBytes += file.length();

			} else if (file.isDirectory()) {
				
				countBytes(file.listFiles());
				
			}
		}
		
	}

}
