package es.sortie;

import es.dataManager.ImageManager;
import utilitaire.IntRect;

public class ImageConteneur implements Comparable {

	private String imagePath;
	private IntRect intRect;
	private int priority;

	public ImageConteneur(String imagePath, IntRect ir, int priority) {
		this.imagePath = imagePath;
		this.intRect = ir;
		this.priority = priority;
	}

	public ImageConteneur(String imagePath, int priority) {
		this(
				imagePath,
				new IntRect(
						0,0,ImageManager.getImage(imagePath).getWidth(), ImageManager.getImage(imagePath).getHeight()
				),
				priority
		);
	}

	public ImageConteneur(String imagePath) {
		this(imagePath, 0);
	}


	public String getImagePath() {
		return imagePath;
	}

	public IntRect getImageDrawingArea() {
		return this.intRect;
	}

	public int getPriority() {
		return priority;
	}

	@Override
	public int compareTo(final Object o) {
		return priority-((ImageConteneur)o).priority;
	}
}
