package es.dataManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ImageManager {

	private static Map<String, BufferedImage> imageMap;
	public static boolean CREATE_FLIPPED_IMG_ON_LOAD = true;

	static {
		imageMap = new ConcurrentHashMap<>();
	}

	/**
	 * Methode de chargement d'une image en mémoire a partir d'une url
	 * @param imageURL : la localisation de l'image
	 * @return true si correctement chargé, false sinon
	 */
	private static boolean imageLoad(String imageURL) {
		try {
			BufferedImage img = ImageIO.read(new File(imageURL));
			imageMap.put(imageURL, img);
			System.out.println("Image loaded at " + imageURL);
			if (CREATE_FLIPPED_IMG_ON_LOAD)
				flipImage(imageURL);
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public static String flippedURL(String url) {
		return url.substring(0,url.lastIndexOf('.')) + "_flip" + url.substring(url.lastIndexOf('.'));
	}

	/**
	 * Methode d'obtention d'image, sert a obtenir une image a partir d'un nom
	 * @param url : l'endroit de l'image sur le disque
	 * @return : l'image si trouvé, ou null;
	 */
	public static BufferedImage getImage(String url) {
		if (url == null)
			throw new IllegalArgumentException("URL NULL");
		if (!imageMap.containsKey(url)) {
			if (url.contains("_flip.")) {
				String newUrl = url.replace("_flip.", ".");
				imageLoad(newUrl);
				flipImage(newUrl);
				getImage(url);
			} else {
				if (!imageLoad(url))
					throw new RuntimeException("Pas trouvé image : " + url);
			}
		}
		return imageMap.get(url);
	}

	protected static void createVirtualImage(String newUrl, BufferedImage bm) {
		System.out.println("Vritual Image created at " + newUrl);
		imageMap.put(newUrl, bm);
	}

	private static String flipImage(String url) {
		String newUrl = flippedURL(url);
		if (imageMap.containsKey(newUrl))
			return newUrl;
		AffineTransform at = new AffineTransform();
		at.concatenate(AffineTransform.getScaleInstance(-1,1));
		at.concatenate(AffineTransform.getTranslateInstance(-getImage(url).getWidth(), 0));
		createVirtualImage(newUrl, createTransform(getImage(url), at));
		return newUrl;
	}

	private static BufferedImage createTransform(final BufferedImage image, final AffineTransform at) {
		BufferedImage nImg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = nImg.createGraphics();
		g2.transform(at);
		g2.drawImage(image, 0,0,null);
		g2.dispose();
		return nImg;
	}

}