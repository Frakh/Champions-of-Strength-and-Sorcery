package es.sortie;

import utilitaire.IPosition;
import es.sortie.composants.AntiTearBuffer;
import es.sortie.composants.AbstractBufferComposant;

import javax.swing.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class FrameManager {

	private JFrame jFrame;
	private int length, height;
	private int spriteHeigt, spriteLength;
	private FocusView fw;
	private BufferedImage screenBuffer;

	public static final int DEF_LEN = 1280, DEF_HEI = 720, DEF_SPR_HEI = 32, DEF_SPR_LEN = 32;

	public FrameManager() {
		jFrame = new JFrame("Champions of strength and sorcery");
		length = DEF_LEN;
		height = DEF_HEI;
		spriteHeigt = DEF_SPR_HEI;
		spriteLength = DEF_SPR_LEN;
		//Permet de mettre en fullscreen
		//GraphicsEnvironment.getLocalGraphicsEnvironment().
		//		getDefaultScreenDevice().setFullScreenWindow(jFrame);
	}

	public int getLength() {
		return length;
	}

	public int getHeight() {
		return height;
	}

	/**
	 * Permet de paramétrer la taille de la fenêtre. PRENDS EN COMPTE LES BORDURES WINDOWS
	 * @param l : length ( width )
	 * @param h : height
	 */
	public void setDimensions(int l, int h) {
		length = l;
		height = h;
	}

	/**
	 * Permet de paramétrer la dimension des sprites.
	 * Vas automatiquement redimensionner la taille des sprites au drawtime
	 * @param height
	 * @param len
	 */
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

	/**
	 * Methode d'initalisation du gestionnaire d'image
	 * @param jComponents liste des composants
	 */
	public void init(AbstractBufferComposant... jComponents) {
		jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jFrame.setSize(length, height);
		screenBuffer = new BufferedImage(length, height, BufferedImage.TYPE_INT_ARGB);

		AntiTearBuffer gp = new AntiTearBuffer(screenBuffer);
		for (AbstractBufferComposant j : jComponents) {
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

	public void setPositionToFollow(IPosition ip) {
		this.fw = new FocusView(this, ip);
	}

	public FocusView getFw() {
		return fw;
	}
}
