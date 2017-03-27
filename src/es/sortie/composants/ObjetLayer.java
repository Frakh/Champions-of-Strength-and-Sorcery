package es.sortie.composants;

import es.dataManager.ImageManager;
import es.sortie.FrameManager;
import game.Carte;
import game.carte.IElement;

import java.awt.*;

/**
 * Layer des objets sur la carte ( monstres, ressources, héros... )
 */
public class ObjetLayer extends AbstractBufferComposant {

	//Friend, parce que les genius qui ont conçu java ne savent pas ce que c'est
	public static class Friend { private Friend(){}}
	private static Friend friend = new Friend();

	/**
	 * Constructeur du layer
	 * @param fm : le frame manager
	 * @param c : ma carte
	 */
	public ObjetLayer(FrameManager fm, Carte c) {
		super(fm, c);
	}

	//Voir la javadoc
	@Override
	public void dessiner(final Graphics g) {
		paintComponent(g);
	}

	/**
	 * Methode permettant de dessiner les objets dans l'objet graphique
	 * @param g = graphique
	 */
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		IElement[][] elementMap = carte.getElements(friend);

		for (int i = gdb.iStartPos; i < gdb.iEndPos; ++i) {
			for (int j = gdb.jStartPos; j < gdb.jEndPos; ++j) {

				if (elementMap[i][j]!=null) {

					int xDrawCoord = i * gdb.spriteWidth + gdb.xDecalage,
							yDrawCoord = j * gdb.spriteHeight + gdb.yDecalage;

					g2.drawImage(
							ImageManager.getImage(elementMap[i][j].getImage()),
							xDrawCoord,
							yDrawCoord,
							gdb.spriteWidth,
							gdb.spriteHeight,
							this
					);
					++AntiTearBuffer.RENDERED_IMAGES;
				}
			}
		}
	}
}
