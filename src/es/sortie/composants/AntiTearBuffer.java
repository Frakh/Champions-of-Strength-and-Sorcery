package es.sortie.composants;

import es.sortie.FrameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Classe permettant aux autres classes de dessiner dans une image, qui sera elle même déssinée sur l'écran
 */
public class AntiTearBuffer extends JComponent {

	//Liste des composants qui seront déssiné
	private java.util.List<AbstractBufferComposant> components;
	//Le buffer qui sera écrite dans l'image
	private BufferedImage screenBuffer;
	//Le block de donnée graphique
	private GraphicDataBlock gdb = new GraphicDataBlock();
	//Le frame manager
	private FrameManager fm;

	/**
	 * Le constructeur
	 * @param sb : l'image qui servira de tampon
	 */
	public AntiTearBuffer(FrameManager fm, BufferedImage sb) {
		components = new ArrayList<>();
		screenBuffer = sb;
		this.fm = fm;
	}

	/**
	 * Ajoute un layer dans la liste des composants qui dessineront
	 * @param j : le layer
	 */
	public void addComponents(AbstractBufferComposant j) {
		components.add(j);
	}

	public static int RENDERED_IMAGES = 0;

	/**
	 * Methode permettant de dessiner sur un objet graphique
	 * @param g : l'objet graphique a dessiner
	 */
	@Override
	public void paintComponent(Graphics g) {

		RENDERED_IMAGES = 0;
		//screenBuffer = new BufferedImage(fm.getWidth(), fm.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D imgGraph = screenBuffer.createGraphics();

		imgGraph.setBackground(new Color(0,0,0,0));
		imgGraph.clearRect(0,0,screenBuffer.getWidth(), screenBuffer.getHeight());
		for (AbstractBufferComposant jc: components) {
			jc.setDataBlock();
			jc.dessiner(screenBuffer.getGraphics());
		}
		g.drawImage(screenBuffer, 0,0, fm.getCurrentWidth(), fm.getCurrentHeight(),this);
		g.drawString("Numb of rendered component : " + RENDERED_IMAGES,10,20);

	}

}
