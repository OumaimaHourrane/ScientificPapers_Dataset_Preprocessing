package com.ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JTextField;

import com.pdf.PDFReader;

public class StartListener implements ActionListener {

	private JTextField _textField;

	public StartListener(JTextField param) {
		_textField = param;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		File f = new File(this._textField.getText()); // on récupere le fichier
														// au chemin absolu du
														// textfield
		if (f.exists() && f.isDirectory()) {
			this.packDirectory(f);
		}
		String corpus = (String) f.toString();
		PDFReader pdfReader = new PDFReader();
//		pdfReader.readCorpus(corpus);
		pdfReader.listFiles(corpus);

//		ListFilesUtil listFilesUtil = new ListFilesUtil();
//        listFilesUtil.listFiles(corpus);

	}

	private void packDirectory(File directory) {
		// On se chargera ici de créer l'archive
	}

}
