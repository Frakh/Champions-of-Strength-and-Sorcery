package es.sortie.composants;

import es.dataManager.ImageManager;
import es.sortie.FrameManager;
import game.combat.Combat;

import java.awt.*;

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

		g2.drawImage(
				ImageManager.getImage(fondCombat),
				0,0,gdb.winWidth, gdb.winHeight, this	);

		for (int i = 0; i <= 13; ++i) {
			try {
				int x = combat.getCoordTroupes(i).x;
				int y = combat.getCoordTroupes(i).y;
				String urlimage = combat.terrainCombat[x][y].getUnit().getImage();
				g2.drawImage(
						ImageManager.getImage(urlimage),
						x * gdb.spriteWidth,
						y * gdb.spriteHeight,
						gdb.spriteWidth,
						gdb.spriteHeight,
						this
				);
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		}

	}

	//Voir javadoc de la super classe abstraite
	@Override
	void dessiner(final Graphics g) {
		paintComponent(g);
	}
}
