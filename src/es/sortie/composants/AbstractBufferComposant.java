package es.sortie.composants;

import es.sortie.FocusView;
import es.sortie.FrameManager;
import game.carte.Carte;
import utilitaire.IPosition;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

public abstract class AbstractBufferComposant extends JComponent {

	protected GraphicDataBlock gdb;
	protected FrameManager fm;
	protected Carte carte;

	public AbstractBufferComposant(FrameManager fm, Carte c) {
		this.fm = fm;
		this.carte = c;
	}

	public void setDataBlock() {

		if (fm != null) {
			GraphicDataBlock gdb = new GraphicDataBlock();

			FocusView fw = fm.getFocusView();

			gdb.winWidth = fm.getLength();
			gdb.winHeight = fm.getHeight();

			gdb.xDecalage = fw.getXDeplacement();
			gdb.yDecalage = fw.getYDeplacement();

			gdb.spriteWidth = fm.getSpriteLength();
			gdb.spriteHeight = fm.getSpriteHeigt();

			IPosition centre = fw.getCentralPos();
			int halfBlockLen = gdb.winWidth / gdb.spriteWidth; // Nb de blocks affichables a l'écran
			halfBlockLen /= 2;    // Num de block entre le milieu de l'écran et un coté de l'écran
			int halfBlockHeight = gdb.winHeight / gdb.spriteHeight;
			halfBlockHeight /= 2;

			// Obtention des limites pour le cadre de dessin
			gdb.iStartPos = (int) centre.deplace(-halfBlockLen, 0).getX() - 1;
			gdb.iEndPos = (int) centre.deplace(halfBlockLen, 0).getX() + 2;
			gdb.jStartPos = (int) centre.deplace(0, -halfBlockHeight).getY() - 1;
			gdb.jEndPos = (int) centre.deplace(0, halfBlockHeight).getY() + 2;
			if (gdb.iStartPos < 0)
				gdb.iStartPos = 0;
			if (gdb.jStartPos < 0)
				gdb.jStartPos = 0;
			if (carte!=null) {
				if (gdb.iEndPos > carte.getWidth())
					gdb.iEndPos = carte.getWidth();
				if (gdb.jEndPos > carte.getHeight())
					gdb.jEndPos = carte.getHeight();
			}

			this.gdb = gdb;
		}
	}

	// Pour la transparence
	// CC de Stack Overflow
	public static Image makeColorTransparent(BufferedImage im, final Color color) {
		ImageFilter filter = new RGBImageFilter() {

			// the color we are looking for... Alpha bits are set to opaque
			public int markerRGB = color.getRGB() | 0xFF000000;

			public final int filterRGB(int x, int y, int rgb) {
				if ((rgb | 0xFF000000) == markerRGB) {
					// Mark the alpha bits as zero - transparent
					return 0x00FFFFFF & rgb;
				} else {
					// nothing to do
					return rgb;
				}
			}
		};

		ImageProducer ip = new FilteredImageSource(im.getSource(), filter);
		return Toolkit.getDefaultToolkit().createImage(ip);
	}

	abstract void dessiner(Graphics g);
}
