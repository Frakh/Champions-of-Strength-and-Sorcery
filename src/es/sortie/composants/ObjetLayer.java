package es.sortie.composants;

import es.dataManager.ImageManager;
import es.interfaces.IPosition;
import es.sortie.FocusView;
import es.sortie.FrameManager;
import game.carte.Carte;
import game.carte.IElement;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.Set;

public class ObjetLayer extends JComponent implements IGoodComp {

	public static class Friend { private Friend(){}}
	private static Friend friend = new Friend();
	private Carte carte;

	private FrameManager fm;

	public ObjetLayer(FrameManager fm, Carte c) {
		this.fm = fm;
		this.carte = c;
	}

	@Override
	public void dessiner(final Graphics g) {
		paintComponent(g);
	}

	@Override
	public void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		FocusView fw = fm.getFw();

		// Obtention du tableau de la carte

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
		if (iEndPos>carte.getHauteur())
			iEndPos = carte.getHauteur();
		if (jEndPos> carte.getLargeur())
			jEndPos = carte.getLargeur();

		Map<IPosition, IElement> elementMap = carte.getElements(friend);
		Set<IPosition> positions = elementMap.keySet();

		for (IPosition ip : positions) {

			int xCoordImgDraw = (int) (fm.getSpriteLength() * ip.getX() + xDecalage),
					yCoordImgDraw = (int) (fm.getSpriteHeigt() * ip.getY() + yDecalage);

			BufferedImage bi = ImageManager.getImage(elementMap.get(ip).getImage());
			g2.drawImage(bi, xCoordImgDraw, yCoordImgDraw, fm.getSpriteLength(), fm.getSpriteHeigt(), this);
		}

	}

}
