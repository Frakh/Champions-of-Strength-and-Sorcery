package es.sortie.composants;

import es.dataManager.ImageManager;
import es.sortie.FrameManager;
import game.carte.Carte;
import game.carte.IElement;

import java.awt.*;

public class ObjetLayer extends AbstractBufferComposant {

	public static class Friend { private Friend(){}}
	private static Friend friend = new Friend();
	private Carte carte;

	private FrameManager fm;

	public ObjetLayer(FrameManager fm, Carte c) {
		this.fm = fm;
		this.carte = c;
	}

	@Override
	public void dessiner(final Graphics g) {
		paintComponent(g);
	}

	@Override
	public void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		IElement[][] elementMap = carte.getElements(friend);
		//Set<IPosition> positions = elementMap.keySet();

		for (int i = gdb.iStartPos; i < gdb.iEndPos; ++i) {
			for (int j = gdb.jStartPos; j < gdb.jEndPos; ++j) {

				if (elementMap[i][j]==null)
					continue;

				int xDrawCoord = i*gdb.spriteWidth + gdb.xDecalage,
						yDrawCoord = j*gdb.spriteHeight + gdb.yDecalage;

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

		/*for (IPosition ip : positions) {

			int xCoordImgDraw = (int) (fm.getSpriteLength() * ip.getX() + xDecalage),
					yCoordImgDraw = (int) (fm.getSpriteHeigt() * ip.getY() + yDecalage);

			BufferedImage bi = ImageManager.getImage(elementMap.get(ip).getImage());
			g2.drawImage(bi, xCoordImgDraw, yCoordImgDraw, fm.getSpriteLength(), fm.getSpriteHeigt(), this);
		}*/

	}

}
