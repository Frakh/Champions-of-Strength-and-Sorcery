package es.sortie;

import es.exception.FrameManagerInitializedException;
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

	//La jframe qui servira a dessiner dans
	private JFrame jFrame;
	//Les dimensions de la fenêtre
	private int width, height;
	//Les dimensions des sprites
	private int spriteHeigt, spriteWidth;
	//Le focus
	private FocusView fw;
	//La liste des mouse adapter
	private List<MouseAdapter> listMouseAdapters;
	//Un boolean pour savoir si la frame manager est initialized
	private boolean is_initialized;
	//Le thread qui permet de rafrashir automatiquement la fenêtre
	private FrameRefresherThread frt;
	//Le buffer d'affichage
	private AntiTearBuffer atb;

	public static final int DEF_LEN = 1280, DEF_HEI = 720, DEF_SPR_HEI = 32, DEF_SPR_LEN = 32;

	public FrameManager() {
		jFrame = new JFrame("Champions of strength and sorcery");
		width = DEF_LEN;
		height = DEF_HEI;
		spriteHeigt = DEF_SPR_HEI;
		spriteWidth = DEF_SPR_LEN;
		listMouseAdapters = new ArrayList<>();
		is_initialized = false;
		frt = new FrameRefresherThread();
		//Permet de mettre en fullscreen
		//GraphicsEnvironment.getLocalGraphicsEnvironment().
		//		getDefaultScreenDevice().setFullScreenWindow(jFrame);
	}

	/**
	 * Permet d'obtenir la longueur de la fenêtre ( x )
	 * Prends en compte les bordures windows
	 * @return la longueur de la fenêtre
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Permet d'obtenir la hauteur de la fenêtre
	 * Prends en compte les bordures windows
	 * @return la hauteur de la fenêtre
	 */
	public int getHeight() {
		return height;
	}

	public int getCurrentWidth() {
		return jFrame.getWidth();
	}

	public int getCurrentHeight() {
		return jFrame.getHeight();
	}

	/**
	 * Permet de paramétrer la taille de la fenêtre. PRENDS EN COMPTE LES BORDURES WINDOWS
	 * @param l : width ( width )
	 * @param h : height
	 */
	public void setDimensions(int l, int h) {
		width = l;
		height = h;
	}

	/**
	 * Permet d'obtenir les souris
	 * @return la liste des souris
	 */
	public List<MouseAdapter> getMouseAdapters() {
		return this.listMouseAdapters;
	}

	/**
	 * Permet de paramétrer la dimension des sprites.
	 * Vas automatiquement redimensionner la taille des sprites au drawtime
	 */
	public void setSpriteDim(int height, int len) {
		this.spriteWidth = len;
		this.spriteHeigt = height;
	}

	/**
	 * Permet d'obtenir la hauteur ( y ) des sprites a dessiner
	 * @return la hauteur du sprite
	 */
	public int getSpriteHeigt() {
		return spriteHeigt;
	}

	/**
	 * Permet d'obtenir la longueur ( x ) des sprites a dessiner
	 * @return la largeur du sprite
	 */
	public int getSpriteWidth() {
		return spriteWidth;
	}

	/**
	 * Methode d'initalisation du gestionnaire d'image
	 * @param jComponents liste des composants
	 */
	public void init(AbstractBufferComposant... jComponents) {
		if (is_initialized)
			throw new FrameManagerInitializedException("");
		jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jFrame.setSize(width, height);
		BufferedImage screenBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		AntiTearBuffer gp = new AntiTearBuffer(this, screenBuffer);
		for (AbstractBufferComposant j : jComponents) {
			gp.addComponents(j);
		}
		for (MouseAdapter ma : listMouseAdapters) {
			gp.addMouseListener(ma);
			gp.addMouseMotionListener(ma);
			gp.addMouseWheelListener(ma);
		}
		this.atb = gp;
		jFrame.add(gp);
		jFrame.setVisible(true);
		is_initialized = true;
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
		return new Point2D.Double(ip.getX()* spriteWidth, ip.getY()*spriteHeigt);
	}

	/**
	 * Ajout d'un mouse adaptater à la JFrame
	 * @param ml : le mouse adapter
	 */
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

	/**
	 * Methode d'obtention du focus
	 * @return le focus
	 */
	public FocusView getFocusView() {
		return fw;
	}

	/**
	 * Methode permettant de donner le frame rate limite
	 * @param frameRateLimit : la limite
	 */
	public void setFrameRateLimit(int frameRateLimit) {
		if (frameRateLimit>0) {
			frt.frameRate = frameRateLimit;
			if (frt.thread==null)
				frt.start();
		}
		else
			frt.can_continue = false;
	}

	/**
	 * Permet de mettre une certaine quantité de layers visibles ou non visibles
	 * @param clname : le nom de la classe du layer
	 * @param isVisible : si oui ou non ils sont visibles
	 */
	public void setLayerVisibility(String clname, boolean isVisible) {
		this.atb.setDisplayable(clname, isVisible);
	}


	/**
	 * Inner class permettant de faire rafraichir l'image de manière automatique
	 */
	class FrameRefresherThread implements Runnable {

		//Le framerate a faire rafraishir
		int frameRate = 60;

		//Le thread dans lequel le rafraichisseur s'execute
		Thread thread;

		//Un boolean pour savoir si il peut continuer
		boolean can_continue = true;

		/**
		 * Methode permettant de démarrer le rafraichisseur dans un autre thread
		 */
		void start() {
			if (thread==null && FrameManager.this.is_initialized) {
				thread = new Thread(this);
				thread.start();
			}
		}

		/**
		 * La methode run que tous le monde connait ( voir javadoc de l'interface java.lang.Runnable )
		 */
		@Override
		public void run() {
			double frametime;
			while (can_continue) {
				try {
					frametime = 1.0/((double)frameRate);
					FrameManager.this.repaint();
					Thread.sleep((int)(1000*frametime));
				} catch (Exception e) {
					can_continue = false;
				}
			}
			thread = null;
		}
	}
}
