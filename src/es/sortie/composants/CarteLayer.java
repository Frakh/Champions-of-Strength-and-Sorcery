package es.sortie.composants;

import es.dataManager.ImageManager;
import es.interfaces.ISpriteDrawable;
import es.sortie.FrameManager;
import game.Carte;

import java.awt.*;

public class CarteLayer extends AbstractBufferComposant {

	// Friend, pattern permettant d'imiter le comportement du friend C++, parce que oui, c'est utile
	public static class Friend { private Friend(){}}
	private static Friend friend = new Friend();

	/**
	 * Constructeur de carte layer
	 * @param fm : le frame manager
	 * @param c : la carte
	 */
	public CarteLayer(FrameManager fm, Carte c) {
		super(fm, c);
	}

	/**
	 * Methode permettant de dessiner la carte sur l'objet graphique g
	 * @param g : l'objet graphique dans lequel on va dessiner
	 */
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		ISpriteDrawable[][] tableau = carte.getSol(friend);

		for (int i = gdb.iStartPos; i < gdb.iEndPos; ++i) {
			for (int j = gdb.jStartPos; j < gdb.jEndPos; ++j) {

				int xCoordImgDraw = gdb.spriteWidth * i + gdb.xDecalage,
						yCoordImgDraw = gdb.spriteHeight * j + gdb.yDecalage;
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

	// Voir javadoc de la classe abstract
	@Override
	public void dessiner(final Graphics g) {
		paintComponent(g);
	}

}
