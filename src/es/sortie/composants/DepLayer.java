package es.sortie.composants;

import es.dataManager.ImageManager;
import es.interfaces.IPosition;
import es.interfaces.ISpriteDrawable;
import es.sortie.FrameManager;
import game.deplacable.IDeplacable;
import game.physics.IHitBox;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class DepLayer extends JComponent implements IGoodComp {

	private java.util.List<IDeplacable> deplacables;
	private FrameManager fm;

	public DepLayer(FrameManager f, java.util.List<IDeplacable> id) {
		fm = f;
		this.deplacables = id;
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		for (IDeplacable ids : deplacables) {
			ISpriteDrawable idd = (ISpriteDrawable) ids;
			g2.drawImage(ImageManager.getImage(idd.getImage()),
					(int)(ids.getPosition().getX()*fm.getSpriteLength()),
					(int)(ids.getPosition().getY()*fm.getSpriteHeigt()),
					this);

			IHitBox hitBox = ids.getHitBox();
			IPosition position = ids.getPosition();
			g2.setColor(Color.RED);
			g2.draw(
					new Rectangle2D.Double(
							(hitBox.getxStart() + position.getX())*fm.getSpriteLength(),
							(hitBox.getyStart() + position.getY())*fm.getSpriteHeigt(),
							hitBox.getxLen()*fm.getSpriteLength(),
							hitBox.getyLen()*fm.getSpriteHeigt()
					)
			);
		}
	}

	@Override
	public void dessiner(final Graphics g) {
		paintComponent(g);
	}
}
