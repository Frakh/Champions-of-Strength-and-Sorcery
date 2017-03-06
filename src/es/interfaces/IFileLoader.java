package es.interfaces;

import game.carte.Case;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public interface IFileLoader {

	static Case[][] loadCarteFile(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		// Utilisez les Buffered bidules
		BufferedInputStream bis = new BufferedInputStream(fis);
		Scanner sc = new Scanner(bis);

		/*
		  File format :
		  int // largeur
		  int // hauteur
		  Tableau séparés de , pour les noms de blocks
		 */

		int largeur = sc.nextInt();
		int hauteur = sc.nextInt();
		Case[][] tab = new Case[largeur][hauteur];
		sc.nextLine();

		try {
			// First loop
			for (int j = 0; j < hauteur; ++j) {
				String nxtL = sc.nextLine();
				try {
					// Loop loading a line
					for (int i = 0; i < largeur; ++i) {
						String caseName = nxtL.substring(0, nxtL.indexOf(','));
						nxtL = nxtL.substring(nxtL.indexOf(',')+1,nxtL.length());
						tab[i][j] = Case.valueOf(caseName);
					}
				} catch (IndexOutOfBoundsException ignored) {}
			}
		} catch (NoSuchElementException ignored) {}
		sc.close();
		return tab;
	}

}
