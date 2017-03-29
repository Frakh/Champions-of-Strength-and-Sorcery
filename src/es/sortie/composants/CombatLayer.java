package es.sortie.composants;

import es.dataManager.ImageManager;
import es.sortie.FrameManager;
import game.combat.Combat;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Classe permettant de dessiner la scène de combat
 */
public class CombatLayer extends AbstractBufferComposant {

	//L'objet représentant le combat
	private Combat combat;

	//L'image du fond du combat
	public static String fondCombat = "./assets/img/bg_plaine.jpg";

	/**
	 * Constructeur du CombatLayer
	 * @param fm : le frame manager
	 * @param combat : l'objet de type combat
	 */
	public CombatLayer(FrameManager fm, Combat combat) {
		super(fm, null);
		this.combat = combat;
	}

	/**
	 * Methode permettant de dessiner la scène de combat dans l'objet de type combat
	 * @param g : l'objet graphique
	 */
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		//Fond d'écran
		g2.drawImage(
				ImageManager.getImage(fondCombat),
				0,0,gdb.winWidth, gdb.winHeight, this	);

		//Affichage des aires
		for (int b = 0; b < 13; ++b) {
			for (int a = 0; a < 20; a++) {
				Rectangle2D.Double rdouble = new Rectangle2D.Double(
						a*gdb.spriteWidth+(b%2==0?gdb.spriteWidth/2:0), b*gdb.spriteHeight,
						gdb.spriteWidth, gdb.spriteHeight
				);
				g2.draw(rdouble);
			}
		}

		//Affichage des unités
		for (int i = 0; i <= 13; ++i) {
			int x = combat.getCoordTroupes(i).x;
			int y = combat.getCoordTroupes(i).y;
			if (x>=0 && y>=0) {
				String urlimage = combat.terrainCombat[x][y].getUnit().getImage();
				boolean[][] path = combat.pathfinding(x,y);
				g2.drawImage(
						ImageManager.getImage(urlimage),
						x * gdb.spriteWidth+(y%2==0?gdb.spriteWidth/2:0),
						y * gdb.spriteHeight,
						gdb.spriteWidth,
						gdb.spriteHeight,
						this
				);
			}
		}

	}

	//Voir javadoc de la super classe abstraite
	@Override
	void dessiner(final Graphics g) {
		paintComponent(g);
	}
}