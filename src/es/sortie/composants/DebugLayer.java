package es.sortie.composants;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class DebugLayer extends AbstractBufferComposant {

	private java.util.List<Object> debDis = new ArrayList<>();
	public int startPosX = 20, startPosY = 20;

	public DebugLayer(Object...os) {
		Collections.addAll(debDis, os);
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		int x = startPosX, y = startPosY;
		Color c = g2.getColor();
		g2.setColor(Color.red);
		for (Object o : debDis) {
			g2.drawString(o.toString(), x, y);
			y+=15;
		}
		g2.setColor(c);
	}

	@Override
	public void dessiner(final Graphics g) {
		paintComponent(g);
	}
}
