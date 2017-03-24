package es.sortie.composants;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Layer utilisé pour le debug ( affiche a l'écran les objets passés en paramètres
 */
public class DebugLayer extends AbstractBufferComposant {

	//La liste d'objets a afficher
	private java.util.List<Object> debDis = new ArrayList<>();
	//La position en pixel de départ d'affichage à l'écran
	public int startPosX = 20, startPosY = 20;

	/**
	 * Le constructeur de debug layer
	 * @param os : la liste d'objet
	 */
	public DebugLayer(Object...os) {
		super(null, null);
		Collections.addAll(debDis, os);
	}

	/**
	 * Methode permettant de dessiner dans l'objet graphique g les chaines des toString() des objets
	 * @param g : l'objet graphique de type g
	 */
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

	// Voir javadoc super classe abstraite
	@Override
	public void dessiner(final Graphics g) {
		paintComponent(g);
	}
}
