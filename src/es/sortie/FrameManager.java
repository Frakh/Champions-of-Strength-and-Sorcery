package es.sortie;

import es.sortie.composants.AbstractBufferComposant;
import es.sortie.composants.AntiTearBuffer;
import utilitaire.IPosition;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class FrameManager {

	private JFrame jFrame;
	private int length, height;
	private int spriteHeigt, spriteLength;
	private FocusView fw;
	private List<MouseAdapter> listMouseAdapters;

	public static final int DEF_LEN = 1280, DEF_HEI = 720, DEF_SPR_HEI = 32, DEF_SPR_LEN = 32;

	public FrameManager() {
		jFrame = new JFrame("Champions of strength and sorcery");
		length = DEF_LEN;
		height = DEF_HEI;
		spriteHeigt = DEF_SPR_HEI;
		spriteLength = DEF_SPR_LEN;
		listMouseAdapters = new ArrayList<>();
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
		BufferedImage screenBuffer = new BufferedImage(length, height, BufferedImage.TYPE_INT_ARGB);

		AntiTearBuffer gp = new AntiTearBuffer(screenBuffer);
		for (AbstractBufferComposant j : jComponents) {
			gp.addComponents(j);
		}
		for (MouseAdapter ma : listMouseAdapters) {
			gp.addMouseListener(ma);
			gp.addMouseMotionListener(ma);
			gp.addMouseWheelListener(ma);
		}
		jFrame.add(gp);
		jFrame.setVisible(true);
	}

	/**
	 * Methode a appeler pour rafraishir l'image
	 */
	public void repaint() {
		jFrame.repaint();
	}

	/**
	 * Permet de créer un point 2D a partir d'une position ( point en pixel )
	 * @param ip la position
	 * @return le point 2d
	 */
	public Point2D getPoint2DFromIPos(IPosition ip) {
		return new Point2D.Double(ip.getX()*spriteLength, ip.getY()*spriteHeigt);
	}

	public void addMouseListener(MouseAdapter ml) {
		if (ml!=null)
			this.listMouseAdapters.add(ml);
	}

	/**
	 * Donne la position a suivre au gestionnaire de focus
	 */
	public void setPositionToFollow(IPosition ip) {
		if (fw == null)
			this.fw = new FocusView(this, ip);
		else
			fw.setPositionToFollow(ip);
	}

	public FocusView getFocusView() {
		return fw;
	}
}
