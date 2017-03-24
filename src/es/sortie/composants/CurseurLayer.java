package es.sortie.composants;

import es.dataManager.ImageManager;
import es.sortie.FrameManager;
import utilitaire.Vector2i;

import java.awt.*;

public class CurseurLayer extends AbstractBufferComposant {

	private Vector2i curseurPos;
	private String sprite;

	public CurseurLayer(FrameManager fm, String sprite, Vector2i vs) {
		super(fm, null);
		this.sprite = sprite;
		this.curseurPos = vs;
		this.fm = fm;
	}

	//Non thread safe
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

	/**
	 * Methode pour dessiner dans l'objet Graphics passé
	 * @param g = l'objet graphics
	 */
	@Override
	public void dessiner(final Graphics g) {
		this.paintComponent(g);
	}
}
