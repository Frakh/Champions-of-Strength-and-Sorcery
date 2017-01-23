package es.sortie.composants;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class DebugComposant extends JComponent implements IGoodComp {

	private java.util.List<Object> debDis = new ArrayList<>();
	public int startPosX = 1000, startPosY = 20;

	public DebugComposant(Object...os) {
		Collections.addAll(debDis, os);
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		int x = startPosX, y = startPosY;
		for (Object o : debDis) {
			g2.drawString(o.toString(), x, y);
			y+=15;
		}
	}

	@Override
	public void dessiner(final Graphics g) {
		paintComponent(g);
	}
}
