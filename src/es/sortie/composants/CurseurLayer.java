package es.sortie.composants;

import es.dataManager.ImageManager;
import es.sortie.FocusView;
import es.sortie.FrameManager;
import utilitaire.Vector2i;

import java.awt.*;

public class CurseurLayer extends AbstractBufferComposant {

	private Vector2i curseurPos;
	private String sprite;
	protected FrameManager fm;

	public CurseurLayer(FrameManager fm, String sprite, Vector2i vs) {
		this.sprite = sprite;
		this.curseurPos = vs;
		this.fm = fm;
	}

	//Non thread safe
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		FocusView fw = fm.getFocusView();

		int xDecalage = fw.getXDeplacement();
		int yDecalage = fw.getYDeplacement();

		int spriteWidth = fm.getSpriteLength();
		int spriteHeight = fm.getSpriteHeigt();

		// Pas de verifications par rapport a si oui ou non c'est hors de l'image

		int xCoordImgDraw, yCoordImgDraw;
		synchronized (curseurPos) {
			xCoordImgDraw = fm.getSpriteLength() * curseurPos.x + xDecalage;
			yCoordImgDraw = fm.getSpriteHeigt() * curseurPos.y + yDecalage;
		}

		g2.drawImage(ImageManager.getImage(sprite), xCoordImgDraw, yCoordImgDraw,
					spriteWidth, spriteHeight, this);

	}

	@Override
	public void dessiner(final Graphics g) {
		this.paintComponent(g);
	}
}
