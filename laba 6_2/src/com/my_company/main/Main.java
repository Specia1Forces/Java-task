package com.my_company.main;


import java.io.*;

public class Main {
	public static void main(String inFile, String outFile, String firstEncoding, String secondEncoding) {
		Reader reader = null;
		Writer writer = null;
		try {
			reader = new InputStreamReader(new FileInputStream(inFile), firstEncoding);
			writer = new OutputStreamWriter(new FileOutputStream(outFile), secondEncoding);
			int c = 0;
			while ((c = reader.read()) >= 0) {
				writer.write(c);
			}
			System.out.println("Completed!");
		} catch (IOException ie) {
			System.out.println("Error!");
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
				if (writer != null) {
					writer.close();
				}
			} catch (IOException ioe) {
				// can't do anything about it, ignore?
			}
		}
		System.out.println("READY!");
	}
}