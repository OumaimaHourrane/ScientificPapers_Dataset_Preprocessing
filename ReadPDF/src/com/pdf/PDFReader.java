package com.pdf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class PDFReader {

	// read file pdf
	public void ReadPDF(File pdf_url, PrintWriter output) {

		// TODO File f = new File("stopWordsListe.txt");

		String[] stopWordList = { 
//				"a", "about", "above", "above", "across",
//				"after", "afterwards", "again", "against", "all", "almost",
//				"alone", "along", "already", "also", "although", "always",
//				"am", "among", "amongst", "amoungst", "amount", "an", "and",
//				"another", "any", "anyhow", "anyone", "anything", "anyway",
//				"anywhere", "are", "around", "as", "at", "back", "be",
//				"became", "because", "become", "becomes", "becoming", "been",
//				"before", "beforehand", "behind", "being", "below", "beside",
//				"besides", "between", "beyond", "bill", "both", "bottom",
//				"but", "by", "call", "can", "cannot", "cant", "co", "con",
//				"could", "couldnt", "cry", "de", "describe", "detail", "do",
//				"done", "down", "due", "during", "each", "eg", "eight",
//				"either", "eleven", "else", "elsewhere", "empty", "enough",
//				"etc", "even", "ever", "every", "everyone", "everything",
//				"everywhere", "except", "few", "fifteen", "fify", "fill",
//				"find", "fire", "first", "five", "for", "former", "formerly",
//				"forty", "found", "four", "from", "front", "full", "further",
//				"get", "give", "go", "had", "has", "hasnt", "have", "he",
//				"hence", "her", "here", "hereafter", "hereby", "herein",
//				"hereupon", "hers", "herself", "him", "himself", "his", "how",
//				"however", "hundred", "ie", "if", "in", "inc", "indeed",
//				"interest", "into", "is", "it", "its", "itself", "keep",
//				"last", "latter", "latterly", "least", "less", "ltd", "made",
//				"many", "may", "me", "meanwhile", "might", "mill", "mine",
//				"more", "moreover", "most", "mostly", "move", "much", "must",
//				"my", "myself", "name", "namely", "neither", "never",
//				"nevertheless", "next", "nine", "no", "nobody", "none",
//				"noone", "nor", "not", "nothing", "now", "nowhere", "of",
//				"off", "often", "on", "once", "one", "only", "onto", "or",
//				"other", "others", "otherwise", "our", "ours", "ourselves",
//				"out", "over", "own", "part", "per", "perhaps", "please",
//				"put", "rather", "re", "same", "see", "seem", "seemed",
//				"seeming", "seems", "serious", "several", "she", "should",
//				"show", "side", "since", "sincere", "six", "sixty", "so",
//				"some", "somehow", "someone", "something", "sometime",
//				"sometimes", "somewhere", "still", "such", "system", "take",
//				"ten", "than", "that", "the", "their", "them", "themselves",
//				"then", "thence", "there", "thereafter", "thereby",
//				"therefore", "therein", "thereupon", "these", "they", "thickv",
//				"thin", "third", "this", "those", "though", "three", "through",
//				"throughout", "thru", "thus", "to", "together", "too", "top",
//				"toward", "towards", "twelve", "twenty", "two", "un", "under",
//				"until", "up", "upon", "us", "very", "via", "was", "we",
//				"well", "were", "what", "whatever", "when", "whence",
//				"whenever", "where", "whereafter", "whereas", "whereby",
//				"wherein", "whereupon", "wherever", "whether", "which",
//				"while", "whither", "who", "whoever", "whole", "whom", "whose",
//				"why", "will", "with", "within", "without", "would", "yet",
//				"you", "your", "yours", "yourself", "yourselves", "1", "2",
//				"3", "4", "5", "6", "7", "8", "9", "10", "1.", "2.", "3.",
//				"4.", "5.", "6.", "7.", "8.", "9.", "10.", "A", "B", "C", "D",
//				"E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
//				"Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "terms",
//				"CONDITIONS", "conditions", "values", "interested.", "care",
				"sure", ".", "!", "@", "#", "$", "%", "^", "&", "'", "*", "(",
				")", "{", "}", 
//				"[", "]", 
				":", ";", ",", "<", ".", ">", "/",
				"?", "_", "-", "+", "=", "a", "b", "c", "d", "e", "f", "g",
				"h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
				"t", "u", "v", "w", "x", "y", "z", "•,", "•"};

		String[] phrases = null;
		String name = pdf_url.getName();
		PdfTextParser reader = new PdfTextParser(pdf_url);

		try {
			// String name = pdf_url.getName();
			// PdfTextParser reader = new PdfTextParser(name);
			// Scanner reader = new Scanner(pdf_url).useDelimiter("\\W+");

			phrases = reader.getParsedText().replaceAll("\\, [\\n|\\r]+", " ")
					.replaceAll("[\\n\\r]+[0-9]", "\n").replaceAll("\\(", "")
					.replaceAll("\\)", "").replaceAll("\\“|\\”", "")
					.replaceAll("[\\.\\n\\r]+( ?[A-Z])", "####$1")
					.replaceAll("[\\.\\n\\r]+( ?[0-9])", "####$1")
					.split("####");
			for (int i = 0; i < phrases.length; i++) {
				phrases[i] = phrases[i].replaceAll("\\r|\\n", "");
				String st = "References";
				if (phrases[i].contains(st.toLowerCase())
						|| phrases[i].contains(st.toUpperCase())
						|| phrases[i].contains(st)) {
					break;
				}

				if (phrases[i]
						.matches(".*\\[[0-9]+(\\–[0-9]+|,[0-9]+|, [0-9]+)*\\].*")
						|| phrases[i]
								.matches(".*\\([A-Z][a-z]+ (and) [A-Z][a-z]+ \\, [1000-9000]\\).*")) {
					WordNetClass netClass = new WordNetClass();
					ArrayList<String> distinctTerms = new ArrayList<String>();

					System.out.println("Phrase " + i + "  :" + phrases[i]);
					phrases[i] = phrases[i].trim().replaceAll("\\s", " ");
					String[] words = phrases[i].split(" ");
					for (String word : words) {
						distinctTerms.add(word.toLowerCase());
					}

					// for (int k = 0; k < distinctTerms.size(); k++) {
					// String term = distinctTerms.get(k).toLowerCase();
					// try{
					// InputStream ips=new FileInputStream(f.toString());
					// InputStreamReader ipsr=new InputStreamReader(ips);
					// BufferedReader br=new BufferedReader(ipsr);
					// String ligne;
					// while ((ligne=br.readLine())!=null){
					// if(f.toString().contains(term)){
					// distinctTerms.remove(k);
					// k = k == 0 ? k : k - 1;
					// }
					// }
					//
					// br.close();
					//
					// }
					// catch (Exception e){
					// System.out.println(e.toString());
					// }
					// }

					for (int k = 0; k < distinctTerms.size(); k++) {
						for (int j = 0; j < stopWordList.length; j++) {
							String term = distinctTerms.get(k).toLowerCase();
							if (stopWordList[j].contains(term)) {
								distinctTerms.remove(k);
								k = k == 0 ? k : k - 1;
							}
						}
					}
					Collections.sort(distinctTerms);

					System.out.println(distinctTerms);

					output.println(distinctTerms);

					netClass.WordNet(distinctTerms);
		
					System.out.println("\n");
				}

			}
		} catch (Exception err) {
			err.printStackTrace();
		}
		return;

	}

	public void listFiles(String directoryName) {
		File directory = new File(directoryName);
		listFiles(directory);
	}

	public void listFiles(File directory) {
		String outputCorpusPath = directory.getAbsolutePath().concat(".output");
		File directoryCorpus = new File(outputCorpusPath);
		if (!directoryCorpus.exists())
			directoryCorpus.mkdir();

		// get all the files from a directory
		File[] fList = directory.listFiles();
		for (File file : fList) {
			if (file.isFile()) {
				PrintWriter output = null;
				try {
					output = new PrintWriter(new File(directoryCorpus, file
							.getName().concat(".output.txt")));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println(file.getName());
				System.out.println("\n******************\n");

				output.println(file.getName());
				output.println("\n******************\n");

				ReadPDF(file, output);
				output.close();

			}
		}
	}

	// read corpus pdf
	// public void readCorpus(String folderPath) {
	// File[] files = new File(folderPath).listFiles();
	//
	// for (File source : files) {
	// ReadPDF(source);
	// }
	// }

	// Fin of read corpus

	public static void main(String[] args) {
		// if (args.length == 0) {
		// System.out.println("PDF NOT FOUND !!!");
		// } else {
		new com.ihm.MainFrame();
		// }
	}

}
