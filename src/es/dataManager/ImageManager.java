package es.dataManager;

import es.eventlogger.LogSys;
import es.exception.ImageNonTrouveException;

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
			LogSys.log("Image chargée a : " + imageURL);
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
	 * @throws ImageNonTrouveException dans le cas ou l'image n'est pas trouvé
	 * @throws IllegalArgumentException dans le cas ou l'url est mauvaise
	 */
	public static BufferedImage getImage(String url) throws ImageNonTrouveException, IllegalArgumentException {
		if (url == null)
			throw new IllegalArgumentException("Url given is null");
		if (!imageMap.containsKey(url)) {
			if (url.contains("_flip.")) {
				String newUrl = url.replace("_flip.", ".");
				imageLoad(newUrl);
				flipImage(newUrl);
				getImage(url);
			} else {
				if (!imageLoad(url))
					throw new ImageNonTrouveException("Image at '" + url + "' unfound");
			}
		}
		return imageMap.get(url);
	}

	/**
	 * Permet de créer un eimage virtuelle, cad une image qui existe au runtime mais pas sur le disque
	 * @param newUrl : la nouvelle url de l'image
	 * @param bm : l'image
	 */
	protected static void createVirtualImage(String newUrl, BufferedImage bm) {
		LogSys.log("Created virtual image " + newUrl);
		imageMap.put(newUrl, bm);
	}

	/**
	 * Permet de faire flip l'image ( un 180 par rapport à l'axe vertical
	 * @param url : l'url de l'image a faire flip
	 * @return la nouvelle url de l'image
	 */
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

	/**
	 * Methode Ctrl C Ctrl V de stack overflow, magie vaudou, pas touché
	 * @param image : l'image a faire transformer
	 * @param at : les transformations a appliquer
	 * @return l'image transformé
	 */
	private static BufferedImage createTransform(final BufferedImage image, final AffineTransform at) {
		BufferedImage nImg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = nImg.createGraphics();
		g2.transform(at);
		g2.drawImage(image, 0,0,null);
		g2.dispose();
		return nImg;
	}

}