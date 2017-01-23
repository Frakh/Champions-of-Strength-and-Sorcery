package es.sortie.composants;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GeneralPainter extends JComponent {

	private java.util.List<IGoodComp> components;

	public GeneralPainter() {
		components = new ArrayList<>();
	}

	public void addComponents(IGoodComp j) {
		components.add(j);
	}

	@Override
	public void paintComponent(Graphics g) {

		for (IGoodComp jc: components) {
			jc.dessiner(g);
		}
	}

}
