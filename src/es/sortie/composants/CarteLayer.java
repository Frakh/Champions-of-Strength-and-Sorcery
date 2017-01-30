package es.sortie.composants;

import es.dataManager.ImageManager;
import es.interfaces.IPosition;
import es.interfaces.ISpriteDrawable;
import es.sortie.FocusView;
import es.sortie.FrameManager;

import javax.swing.*;
import java.awt.*;

public class CarteLayer extends JComponent implements IGoodComp {

	private ISpriteDrawable[][] tableau;
	private FrameManager fm;

	public CarteLayer(FrameManager fm, ISpriteDrawable[][] tableau) {
		this.fm = fm;
		this.tableau = tableau;
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
		int halfBlockLen = fm.getLength()/fm.getSpriteLength();
		halfBlockLen/=2;
		int halfBlockHeight = fm.getHeight()/fm.getSpriteHeigt();
		halfBlockHeight/=2;

		int iStartPos = (int) centre.deplace(-halfBlockLen,0).getX()-1;
		int iEndPos = (int) centre.deplace(halfBlockLen,0).getX()+2;
		int jStartPos = (int) centre.deplace(0,-halfBlockHeight).getY()-1;
		int jEndPos = (int) centre.deplace(0,halfBlockHeight).getY()+2;
		if (iStartPos<0)
			iStartPos = 0;
		if (jStartPos<0)
			jStartPos = 0;
		if (iEndPos>tableau.length)
			iEndPos = tableau.length;
		if (jEndPos> tableau[0].length)
			jEndPos = tableau[0].length;

		int nbOfRenderedBlocks = 0;

		for (int i = iStartPos; i < iEndPos; ++i) {
			for (int j = jStartPos; j < jEndPos; ++j) {
					int xCoordImgDraw = fm.getSpriteLength() * i + xDecalage,
							yCoordImgDraw = fm.getSpriteHeigt() * j + yDecalage;
					{
						nbOfRenderedBlocks++;
						g2.drawImage(ImageManager.getImage(tableau[i][j].getImage()), xCoordImgDraw, yCoordImgDraw,
								spriteWidth, spriteHeight,this);
					}
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
