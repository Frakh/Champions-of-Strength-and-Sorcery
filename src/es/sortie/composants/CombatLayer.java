package es.sortie.composants;

import es.dataManager.ImageManager;
import es.entree.Souris;
import es.sortie.FrameManager;
import game.combat.Combat;
import utilitaire.IntRect;
import utilitaire.Vector2i;

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
	//
	public static String CURSEUR_SOURIS_IMAGE_PATH = "./assets/img/Curseur.png";

	public final int DEPLACEMENT_HORIZONTAL_PIXEL;
	public final int DEPLACEMENT_VERTICAL_PIXEL;
	public final float REDUCTION_VERTICALE = 0.75f;
	public final float REDUCTION_HORIZONTALE = 0.85f;

	public final Vector2i[][] rectgls;

	private Souris souris;

	public CombatLayer(FrameManager fm, Combat combat, Souris souris) {
		this(fm, combat);
		this.souris = souris;
	}

	/**
	 * Constructeur du CombatLayer
	 * @param fm : le frame manager
	 * @param combat : l'objet de type combat
	 */
	public CombatLayer(FrameManager fm, Combat combat) {
		super(fm, null);
		this.combat = combat;
		DEPLACEMENT_HORIZONTAL_PIXEL = (int)(((double)fm.getWidth())*0.1);
		DEPLACEMENT_VERTICAL_PIXEL = 25;
		rectgls = new Vector2i[20][13];

		this.setDataBlock();
		for (int b = 0; b < 13; ++b) {
			for (int a = 0; a < 20; a++) {
				int x_rect_pos = (int) ((a*gdb.spriteWidth+(b%2==0?gdb.spriteWidth/2:0))*this.REDUCTION_HORIZONTALE + this.DEPLACEMENT_HORIZONTAL_PIXEL);
				int y_rect_pos = (int) ((b*gdb.spriteHeight)*this.REDUCTION_VERTICALE + this.DEPLACEMENT_VERTICAL_PIXEL);

				rectgls[a][b] = new Vector2i(x_rect_pos, y_rect_pos);
			}
		}
	}

	/**
	 * Methode permettant de dessiner la scène de combat dans l'objet de type combat
	 * @param g : l'objet graphique
	 */
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		IntRect leRectangle = new IntRect(0,0,
				(int)(gdb.spriteWidth*this.REDUCTION_HORIZONTALE),
				(int)(gdb.spriteHeight*this.REDUCTION_VERTICALE)
				);

		//Fond d'écran
		g2.drawImage(
				ImageManager.getImage(fondCombat),
				0,0,gdb.winWidth, gdb.winHeight, this	);

		Vector2i mousePos = souris.getInGamePosition();
		//Affichage des aires
		for (int b = 0; b < 13; ++b) {
			for (int a = 0; a < 20; a++) {

				int x_rect_pos = (int) ((a*gdb.spriteWidth+(b%2==0?gdb.spriteWidth/2:0))*this.REDUCTION_HORIZONTALE + this.DEPLACEMENT_HORIZONTAL_PIXEL);
				int y_rect_pos = (int) ((b*gdb.spriteHeight)*this.REDUCTION_VERTICALE + this.DEPLACEMENT_VERTICAL_PIXEL);

				leRectangle.x = x_rect_pos;
				leRectangle.y = y_rect_pos;

				Rectangle2D.Double rdouble = new Rectangle2D.Double(
						x_rect_pos, y_rect_pos,
						gdb.spriteWidth*this.REDUCTION_HORIZONTALE,
						gdb.spriteHeight*this.REDUCTION_VERTICALE
				);
				if (leRectangle.contains(souris.getInGamePosition())) {
					Color c = g2.getColor();
					g2.setColor(Color.GRAY);
					g2.fill(rdouble);
					g2.setColor(c);
				} else {
					g2.draw(rdouble);
				}
			}
		}

		//Affichage des unités
		for (int i = 0; i <= 13; ++i) {
			int x = combat.getCoordTroupes(i).x;
			int y = combat.getCoordTroupes(i).y;
			if (x>=0 && y>=0) {
				String urlimage = combat.terrainCombat[x][y].getUnit().getImage();
				boolean[][] path = combat.pathfinding(x,y);
				int dr_x_pos = (int) ((x * gdb.spriteWidth+(y%2==0?gdb.spriteWidth/2:0))*this.REDUCTION_HORIZONTALE + this.DEPLACEMENT_HORIZONTAL_PIXEL);
				int dr_y_pos = (int) (y * gdb.spriteHeight * this.REDUCTION_VERTICALE) + this.DEPLACEMENT_VERTICAL_PIXEL;
				g2.drawImage(
						ImageManager.getImage(urlimage),
						dr_x_pos,
						dr_y_pos,
						(int) (gdb.spriteWidth*this.REDUCTION_HORIZONTALE),
						(int) (gdb.spriteHeight*this.REDUCTION_VERTICALE),
						this
				);
			}
		}

		g2.drawImage(
				ImageManager.getImage(CURSEUR_SOURIS_IMAGE_PATH),
				souris.getInGamePosition().x-16,
				souris.getInGamePosition().y-16,
				this
		);

	}

	//Voir javadoc de la super classe abstraite
	@Override
	void dessiner(final Graphics g) {
		paintComponent(g);
	}
}