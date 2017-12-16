package com.pdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.tika.metadata.Metadata;

public class PdfTextParser {

	private PDFParser parser = null;
	Metadata metadata = new Metadata();
	
	
	//Parsing du Corpus
//		public PdfTextParser(String folderPath) {
//			File[] files = new File(folderPath).listFiles();
//
//			for (File source : files) {
////				File file = new File(fileName);
//				if (!source.isFile()) {
//					System.err.println("File " + source.getName() + " does not exist.");
//				}
//				try {
//					parser = new PDFParser(new FileInputStream(source));
//				} catch (IOException e) {
//					System.err.println("\"Unable to open PDF Parser. \""
//							+ e.getMessage());
//				}
//			}
//		}

	public PdfTextParser(File file) {
 
		try {
			parser = new PDFParser(new FileInputStream(file));
		} catch (IOException e) {
			System.err.println("\"Unable to open PDF Parser. \""
					+ e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public String getParsedText() {

		PDDocument pdDoc = null;
		COSDocument cosDoc = null;
		String parsedText = null;
		
		try {
			PDFTextStripper pdfStripper = new PDFTextStripper();

			parser.parse();
			cosDoc = parser.getDocument();
			pdDoc = new PDDocument(cosDoc);
			List<PDPage> list = pdDoc.getDocumentCatalog().getAllPages();
			pdfStripper.setStartPage(1);
			int length = list.size();
			pdfStripper.setEndPage(length);
			parsedText = pdfStripper.getText(pdDoc);
			
			
		} catch (IOException e) {
			System.err
					.println("\"An exception occured in parsing the PDF Document.\""
							+ e.getMessage());
		} finally {
			try {
				if (cosDoc != null)
					cosDoc.close();
				if (pdDoc != null)
					pdDoc.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return parsedText;
	}
}
