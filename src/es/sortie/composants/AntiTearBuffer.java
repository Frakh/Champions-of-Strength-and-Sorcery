package es.sortie.composants;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Classe permettant aux autres classes de dessiner dans une image, qui sera elle même déssinée sur l'écran
 */
public class AntiTearBuffer extends JComponent {

	private java.util.List<AbstractBufferComposant> components;
	private BufferedImage screenBuffer;
	private GraphicDataBlock gdb = new GraphicDataBlock();

	public AntiTearBuffer(BufferedImage sb) {
		components = new ArrayList<>();
		screenBuffer = sb;
	}

	public void addComponents(AbstractBufferComposant j) {
		components.add(j);
	}

	public static int RENDERED_IMAGES = 0;

	@Override
	public void paintComponent(Graphics g) {

		RENDERED_IMAGES = 0;





		Graphics2D imgGraph = screenBuffer.createGraphics();

		imgGraph.setBackground(new Color(0,0,0,0));
		imgGraph.clearRect(0,0,screenBuffer.getWidth(), screenBuffer.getHeight());
		for (AbstractBufferComposant jc: components) {
			jc.dessiner(screenBuffer.getGraphics());
		}
		g.drawImage(screenBuffer, 0,0,this);
		g.drawString("Numb of rendered component : " + RENDERED_IMAGES,10,20);

	}

}
