package com.pdf;


import java.util.ArrayList;

import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.WordNetDatabase;

public class WordNetClass {

	@SuppressWarnings("unused")
	public void WordNet(ArrayList<String> words) {

		if (words.size() > 0) {
			for (int i = 0; i < words.size(); i++) {
				String wordForm = words.get(i).toString();
				WordNetDatabase database = WordNetDatabase.getFileInstance();
if(System.getProperty("wordnet.database.dir")==null){
				System.setProperty("wordnet.database.dir",
						"C:\\Program Files\\WordNet\\2.1\\dict"
						// chemin pour linux "\\home\\sara\\Bureau\\WordNet-2.1\\dict"
						);
}
				Synset[] synsets = database.getSynsets(wordForm);

				if (synsets.length > 0) {
					for (int i1 = 0; i1 < synsets.length; i1++) 
					{
						String[] wordForms = synsets[i1].getWordForms();
						for (int j1 = 0; j1 < wordForms.length; j1++) 
						{
							
							if (wordForms[j1].equals("critic")) {
								System.out
										.println("\n*********** la classe sémantique est : Critique***********");
								break;
							} else if (wordForms[j1].equals("comparison")) {
								System.out
										.println("\n*********** la classe sémantique est : Comparison***********");
								break;
							} else if (wordForms[j1].equals("describe")) {
								System.out
										.println("\n*********** la classe sémantique est : Déscription***********");
								break;
							} else if (wordForms[j1].equals("base")) {
								System.out
										.println("\n*********** la classe sémantique est : Appui***********");
								break;
							} else if (wordForms[j1].equals("associate")) {
								System.out
										.println("\n*********** la classe sémantique est : Association***********");
								break;
							}
							break;
						}
					}
				}
			}

		} else {
			System.err.println("You must specify "
					+ "a word form for which to retrieve synsets.");
		}
	}
}
