package es.interfaces;

import game.carte.Carte;
import game.carte.Case;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

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

	char DATA_TYPE_SEPARATOR = '!';
	char NEXT_CASE = ',';
	char NEXT_LINE = ';';

	static String fullyReadFile(String filepath) throws IOException {
		File fichier = new File(filepath);
		FileInputStream fis = new FileInputStream(fichier);
		BufferedInputStream bis = new BufferedInputStream(fis);
		byte[] buffer = new byte[65536];
		int char_lus = bis.read(buffer, 0, 65536);
		byte[] texte = new byte[char_lus];
		System.arraycopy(buffer, 0, texte, 0, char_lus);
		return new String(texte);
	}

	static String[] getFileMapLines(String file_content) {
		int line_num;
		List<String> strings = new ArrayList<>();
		int current_string_pos = 0;
		while (current_string_pos<file_content.length()) {
			int next_semicolon_pos = file_content.indexOf(';', current_string_pos);
			strings.add(file_content.substring(current_string_pos, next_semicolon_pos));
			current_string_pos = next_semicolon_pos+1;
		}
		/*JAVA ET LA GENERICITE, TU FAIS CHIER
		return strings.toArray();*/
		String[] starr = new String[strings.size()];
		strings.toArray(starr);
		return starr;
	}

	static String[][] getFileMapMatrix(String[] lines) {

		int hauteurMatrice = lines.length;
		int largeurMatrice = 0;
		for (String l : lines) {
			StringTokenizer stoken = new StringTokenizer(l, ",");
			int count = stoken.countTokens();
			if (largeurMatrice<count) {
				largeurMatrice = count;
			}
		}

		String matrice[][] = new String[largeurMatrice][hauteurMatrice];

		for (int i = 0; i < hauteurMatrice; ++i) {

			StringTokenizer stoken = new StringTokenizer(lines[i], ",");

			int j_pos = 0;
			while (stoken.hasMoreTokens()) {
				String tkn = stoken.nextToken();
				matrice[j_pos][i] = tkn;
				++j_pos;
			}
		}

		return matrice;
	}

	static Carte loadCarte(String filename) throws IOException {

		String file_content = fullyReadFile(filename);


		return null;
	}

}
