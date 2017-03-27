package es.sortie;

import es.dataManager.ImageManager;
import utilitaire.Vector2i;

public class ImageConteneur implements Comparable {

	private String imagePath;
	private Vector2i imgDispSize;
	private Vector2i imgDisp00Point;
	private int priority;

	public ImageConteneur(String imagePath, Vector2i imgDispSize, Vector2i imgDisp00Point, int priority) {
		this.imagePath = imagePath;
		this.imgDispSize = imgDispSize;
		this.imgDisp00Point = imgDisp00Point;
		this.priority = priority;
	}

	public ImageConteneur(String imagePath, int priority) {
		this(
				imagePath,
				new Vector2i(ImageManager.getImage(imagePath).getWidth(), ImageManager.getImage(imagePath).getHeight()),
				new Vector2i(0,0),
				priority
		);
	}

	public ImageConteneur(String imagePath) {
		this(imagePath, 0);
	}


	public String getImagePath() {
		return imagePath;
	}

	public Vector2i getImgDispSize() {
		return imgDispSize;
	}

	public Vector2i getImgDisp00Point() {
		return imgDisp00Point;
	}

	public int getPriority() {
		return priority;
	}

	@Override
	public int compareTo(final Object o) {
		return priority-((ImageConteneur)o).priority;
	}
}
