package es.sortie.composants;

import es.dataManager.ImageManager;
import es.sortie.FrameManager;
import game.combat.Combat;

import java.awt.*;

public class CombatLayer extends AbstractBufferComposant {

	private Combat combat;

	public static String fondCombat = "./assets/img/bg_plaine.jpg";

	public CombatLayer(FrameManager fm, Combat combat) {
		super(fm, null);
		this.combat = combat;
	}

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

	@Override
	void dessiner(final Graphics g) {
		paintComponent(g);
	}
}
