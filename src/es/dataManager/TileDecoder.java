package es.dataManager;

import java.awt.image.BufferedImage;

public class TileDecoder {

	public static void loadTile(String path, int length, int height) {

		BufferedImage bm = ImageManager.getImage(path);

		for (int i = 0; i < (bm.getWidth()/length); ++i) {
			for (int j= 0; j < bm.getHeight()/height; ++j) {
				ImageManager.createVirtualImage(
						newURL(path, length, height, i, j),
						bm.getSubimage(i*length, j*height, length, height)
				);
			}
		}
	}

	private static String newURL(String path, int wdt, int hgt, int i, int j) {
		return path.substring(0, path.lastIndexOf(".png")) + '_' + wdt + 'x' + hgt + '<' + i + '-' + j +'>' + ".png";
	}

}
