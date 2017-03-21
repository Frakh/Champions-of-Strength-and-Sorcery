package es.sortie.composants;

import es.dataManager.ImageManager;
import es.interfaces.ISpriteDrawable;
import es.sortie.FocusView;
import es.sortie.FrameManager;
import game.carte.Carte;
import utilitaire.IPosition;

import java.awt.*;

public class CarteLayer extends AbstractBufferComposant {

	// Friend
	public static class Friend { private Friend(){}}
	private static Friend friend = new Friend();

	private Carte carte;
	private FrameManager fm;

	public CarteLayer(FrameManager fm, Carte c) {
		this.fm = fm;
		this.carte = c;
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		FocusView fw = fm.getFw();

		int xDecalage = fw.getXDeplacement();
		int yDecalage = fw.getYDeplacement();

		int spriteWidth = fm.getSpriteLength();
		int spriteHeight = fm.getSpriteHeigt();

		IPosition centre = fw.getCentralPos();
		int halfBlockLen = fm.getLength()/fm.getSpriteLength(); // Nb de blocks affichables a l'écran
		halfBlockLen/=2;	// Num de block entre le milieu de l'écran et un coté de l'écran
		int halfBlockHeight = fm.getHeight()/fm.getSpriteHeigt();
		halfBlockHeight/=2;

		// Obtention des limites pour le cadre de dessin
		int iStartPos = (int) centre.deplace(-halfBlockLen,0).getX()-1;
		int iEndPos = (int) centre.deplace(halfBlockLen,0).getX()+2;
		int jStartPos = (int) centre.deplace(0,-halfBlockHeight).getY()-1;
		int jEndPos = (int) centre.deplace(0,halfBlockHeight).getY()+2;
		if (iStartPos<0)
			iStartPos = 0;
		if (jStartPos<0)
			jStartPos = 0;
		if (iEndPos>carte.getWidth())
			iEndPos = carte.getWidth();
		if (jEndPos> carte.getHeight())
			jEndPos = carte.getHeight();

		int nbOfRenderedBlocks = 0;

		ISpriteDrawable[][] tableau = carte.getSol(friend);

		for (int i = iStartPos; i < iEndPos; ++i) {
			for (int j = jStartPos; j < jEndPos; ++j) {

				int xCoordImgDraw = fm.getSpriteLength() * i + xDecalage,
						yCoordImgDraw = fm.getSpriteHeigt() * j + yDecalage;
				nbOfRenderedBlocks++;
				g2.drawImage(ImageManager.getImage(tableau[i][j].getImage()), xCoordImgDraw, yCoordImgDraw,
						spriteWidth, spriteHeight,this);
			}
		}
		g2.setColor(Color.RED);
		g2.drawString("Rendered components : " + nbOfRenderedBlocks, 10,20);
	}

	@Override
	public void dessiner(final Graphics g) {
		paintComponent(g);
	}
}
