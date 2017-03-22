package es.sortie.composants;

import es.dataManager.ImageManager;
import es.interfaces.ISpriteDrawable;
import es.sortie.FrameManager;
import game.carte.Carte;

import java.awt.*;

public class CarteLayer extends AbstractBufferComposant {

	// Friend
	public static class Friend { private Friend(){}}
	private static Friend friend = new Friend();

	public CarteLayer(FrameManager fm, Carte c) {
		super(fm, c);
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		int nbOfRenderedBlocks = 0;
		ISpriteDrawable[][] tableau = carte.getSol(friend);

		for (int i = gdb.iStartPos; i < gdb.iEndPos; ++i) {
			for (int j = gdb.jStartPos; j < gdb.jEndPos; ++j) {

				int xCoordImgDraw = fm.getSpriteLength() * i + gdb.xDecalage,
						yCoordImgDraw = fm.getSpriteHeigt() * j + gdb.yDecalage;
				nbOfRenderedBlocks++;
				g2.drawImage(ImageManager.getImage(tableau[i][j].getImage()),
						xCoordImgDraw,
						yCoordImgDraw,
						gdb.spriteWidth,
						gdb.spriteHeight,
						this
				);
				++AntiTearBuffer.RENDERED_IMAGES;
			}
		}
	}

	@Override
	public void dessiner(final Graphics g) {
		paintComponent(g);
	}

}
