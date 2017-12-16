package com.pdf;

import java.io.File;

public class MainConsole {

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out
					.println("Missed argument , please pass the corpus' path as an argument :java -jar ReadPDF.jar <corpusPath>");
			System.exit(1);
		} else {
			String corpusPath = args[0];
			File corpus = new File(corpusPath);
			if (!corpus.exists() || !corpus.isDirectory()) {
				System.out
						.println("Corpus path is invalid , please pass a valid directory path");
				System.exit(1);
			}
			
			PDFReader pdfReader = new PDFReader();
 			pdfReader.listFiles(corpus);
		}
	}
}
