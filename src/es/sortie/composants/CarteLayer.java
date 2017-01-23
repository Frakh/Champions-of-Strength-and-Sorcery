package es.sortie.composants;

import es.dataManager.ImageManager;
import es.interfaces.ISpriteDrawable;
import es.sortie.FrameManager;

import javax.swing.*;
import java.awt.*;

public class CarteLayer extends JComponent implements IGoodComp {

	private ISpriteDrawable[][] tableau;
	private FrameManager fm;

	public CarteLayer(FrameManager fm, ISpriteDrawable[][] tableau) {
		this.fm = fm;
		this.tableau = tableau;
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		for (int i = 0; i < tableau.length; ++i) {
			for (int j = 0; j < tableau[0].length; ++j) {
				g2.drawImage(ImageManager.getImage(tableau[i][j].getImage()), fm.getSpriteLength()*i, fm.getSpriteHeigt()*j, this);
			}
		}
	}

	@Override
	public void dessiner(final Graphics g) {
		paintComponent(g);
	}
}
