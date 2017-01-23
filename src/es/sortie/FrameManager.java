package es.sortie;

import es.interfaces.IPosition;
import es.sortie.composants.GeneralPainter;
import es.sortie.composants.IGoodComp;

import javax.swing.*;
import java.awt.geom.Point2D;

public class FrameManager {

	private JFrame jFrame;
	private int length, height;
	private int spriteHeigt, spriteLength;

	public static final int DEF_LEN = 1280, DEF_HEI = 720, DEF_SPR_HEI = 32, DEF_SPR_LEN = 32;

	public FrameManager() {
		jFrame = new JFrame("Shootest");
		length = DEF_LEN;
		height = DEF_HEI;
		spriteHeigt = DEF_SPR_HEI;
		spriteLength = DEF_SPR_LEN;
	}

	public void setDimensions(int l, int h) {
		length = l;
		height = h;
	}

	public void setSpriteDim(int height, int len) {
		this.spriteLength = len;
		this.spriteHeigt = height;
	}

	public int getSpriteHeigt() {
		return spriteHeigt;
	}

	public int getSpriteLength() {
		return spriteLength;
	}

	public void init(IGoodComp... jComponents) {
		jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jFrame.setSize(length, height);
		GeneralPainter gp = new GeneralPainter();
		for (IGoodComp j : jComponents) {
			gp.addComponents(j);
		}
		jFrame.add(gp);
		jFrame.setVisible(true);
	}

	public void repaint() {
		jFrame.repaint();
	}

	public Point2D getPoint2DFromIPos(IPosition ip) {
		return new Point2D.Double(ip.getX()*spriteLength, ip.getY()*spriteHeigt);
	}
}
