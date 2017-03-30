package es.dataManager;

import java.awt.image.BufferedImage;

public class TileDecoder {

	/**
	 * Fonction permettant de charger une tilemap
	 * @param path : l'endroit ou est situé la tilemap
	 * @param length la taille des sprites
	 * @param height : la hauteur des sprites
	 */
	public static void loadTile(String path, int length, int height) {

		BufferedImage bm = ImageManager.getImage(path);

		int bmWidth = bm.getWidth();
		int bmHeight = bm.getHeight();
		for (int i = 0; i < (bmWidth /length); ++i) {
			for (int j = 0; j < bmHeight /height; ++j) {
				ImageManager.createVirtualImage(
						newURL(path, length, height, i, j),
						bm.getSubimage(i*length, j*height, length, height)
				);
			}
		}
	}

	/**
	 * Formateur de nouvelle url pour les sous images
	 * @param path : l'endroit ou est stocké l'image
	 * @param wdt : la longueur de l'image ( nouvelle )
	 * @param hgt : la hauteur de l'image ( nouvelle )
	 * @param i : le décalage de l'image sur le plan horizontal ( en nombre d'images )
	 * @param j : le décalage de l'image sur le plan vertical ( en nombre d'images )
	 * @return la chaine de charactères
	 */
	private static String newURL(String path, int wdt, int hgt, int i, int j) {
		return path.substring(0, path.lastIndexOf(".png")) + '_' + wdt + 'x' + hgt + '<' + i + '-' + j +'>' + ".png";
	}

}
