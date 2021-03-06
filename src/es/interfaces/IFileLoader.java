package es.interfaces;

import game.Carte;
import game.carte.Case;
import game.carte.IElement;
import game.carte.elements.ElementFactory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public interface IFileLoader {

	/**
	 * Ancien chargeur de carte : ne pas utiliser, utilisez loadCarte à la place
	 * @param path : pas touche
	 * @return : j'ai dit pas touche, utilisez loadCarte
	 * @throws IOException : Problème avec les fichiers
	 */
	@Deprecated
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

	/**
	 * Methode permettant de charger un fichier texte de 64k maximum en totalité
	 * @param filepath : l'endroit sur le disque ou se situe le fichier
	 * @return : la chaine de charactere tel qu'elle est dans le fichier
	 * @throws IOException : En cas de problème ( fichier non trouvé, problème de lecture )
	 */
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

	/**
	 * S'applique uniquement aux fichiers censé représenter les cartes
	 * Prends une chaine de charactère représentant le fichier de la carte
	 * @param file_content : le fichier contenant les données de la carte
	 * @return : le tableau des lignes de la carte
	 */
	static String[] getFileMapLines(String file_content) {
		int line_num;
		List<String> strings = new ArrayList<>();
		StringTokenizer stoken = new StringTokenizer(file_content, "\n\t\r;");
		while (stoken.hasMoreTokens()) {
			String token = stoken.nextToken();
			strings.add(token);
		}
		/*JAVA ET LA GENERICITE, TU FAIS CHIER
		return strings.toArray();*/
		String[] starr = new String[strings.size()];
		strings.toArray(starr);
		return starr;
	}

	/**
	 * Methode permettant de mettre sous forme matricielle les données extraites d'un fichier représentant la carte
	 * @param lines : le tableau des lignes de la carte
	 * @return : la matrice représentant sous forme de string chaque element de la carte
	 */
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

	/**
	 * Methode permettant de charger la carte
	 * @param filename : le nom du fichier
	 * @return : la carte
	 * @throws IOException en cas de problème ( fichier non trouvé, problème de lecture )
	 */
	static Carte loadCarte(String filename) throws IOException {

		String[][] carte_objs = getFileMapMatrix(getFileMapLines(fullyReadFile(filename)));

		Case[][] listeCases = new Case[carte_objs.length][carte_objs[0].length];
		IElement[][] elements = new IElement[carte_objs.length][carte_objs[0].length];


		for (int y = 0; y < carte_objs[0].length; ++y) {
			for (int x = 0; x < carte_objs.length; ++x) {
				StringTokenizer stoken = new StringTokenizer(carte_objs[x][y], "!");
				String lacase = null, lobjet = null;

				int count = stoken.countTokens();
				switch (count) {
					default:
						throw new RuntimeException("Erreur lors de la lecture de la carte stoken.countTokens = " + count);
					case 1:
						lacase = stoken.nextToken();
						break;
					case 2:
						lacase = stoken.nextToken();
						lobjet = stoken.nextToken();
						break;
				}
				listeCases[x][y] = Case.valueOf(lacase);
				elements[x][y] = ElementFactory.getIelement(lobjet);
			}
		}

		return new Carte(listeCases, elements);
	}

}
