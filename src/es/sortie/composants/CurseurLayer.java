package es.sortie.composants;

import es.dataManager.ImageManager;
import es.sortie.FrameManager;
import utilitaire.Vector2i;

import java.awt.*;

/**
 * Le
 */
public class CurseurLayer extends AbstractBufferComposant {

	private Vector2i curseurPos;
	private String sprite;

	/**
	 * Constructeur du curseur layer
	 * @param fm : le frame manager
	 * @param sprite : le sprite qui va servir pour dessiner
	 * @param vs : le pointeur vers l'objet ou le sprite va être dessiné
	 */
	public CurseurLayer(FrameManager fm, String sprite, Vector2i vs) {
		super(fm, null);
		this.sprite = sprite;
		this.curseurPos = vs;
		this.fm = fm;
	}

	/**
	 * Methode pour dessiner dans l'objet Graphics passé
	 * @param g = l'objet graphics
	 */
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		// Pas de verifications par rapport a si oui ou non c'est hors de l'image
		int xCoordImgDraw = gdb.spriteWidth * curseurPos.x + gdb.xDecalage,
			yCoordImgDraw = gdb.spriteHeight * curseurPos.y + gdb.yDecalage;

		g2.drawImage(ImageManager.getImage(sprite),
				xCoordImgDraw,
				yCoordImgDraw,
				gdb.spriteWidth,
				gdb.spriteHeight,
				this
		);
		++AntiTearBuffer.RENDERED_IMAGES;
	}

	//Voir javadoc dans la super classe abstraite
	@Override
	public void dessiner(final Graphics g) {
		this.paintComponent(g);
	}
}
