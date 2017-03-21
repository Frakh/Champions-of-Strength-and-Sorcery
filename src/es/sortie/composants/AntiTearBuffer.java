package es.sortie.composants;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class AntiTearBuffer extends JComponent {

	private java.util.List<AbstractBufferComposant> components;
	private BufferedImage screenBuffer;

	public AntiTearBuffer(BufferedImage sb) {
		components = new ArrayList<>();
		screenBuffer = sb;
	}

	public void addComponents(AbstractBufferComposant j) {
		components.add(j);
	}

	@Override
	public void paintComponent(Graphics g) {

		Graphics2D imgGraph = screenBuffer.createGraphics();
		imgGraph.setBackground(new Color(0,0,0,0));
		imgGraph.clearRect(0,0,screenBuffer.getWidth(), screenBuffer.getHeight());
		for (AbstractBufferComposant jc: components) {
			jc.dessiner(screenBuffer.getGraphics());
		}
		g.drawImage(screenBuffer, 0,0,this);

	}

}
