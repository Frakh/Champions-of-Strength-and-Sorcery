package test;

import es.entree.Souris;
import es.sortie.FrameManager;
import es.sortie.composants.AbstractBufferComposant;
import es.sortie.composants.CurseurLayer;
import es.sortie.composants.DebugLayer;
import org.junit.Test;
import utilitaire.IPosition;
import utilitaire.Position;
import utilitaire.Vector2i;

public class MouseTest {

	@Test
	public void testBase() throws InterruptedException {

		FrameManager fm = new FrameManager();
		IPosition ip = new Position(20,11);

		fm.setSpriteDim(32, 32);
		fm.setPositionToFollow(ip);
		fm.setDimensions(1280,720);

		Souris souris = new Souris();

		Vector2i cPos = new Vector2i(0,0);
		AbstractBufferComposant curseurLayer = new CurseurLayer(fm, "./assets/img/SPRITES/PEUNEUGEU/Curseur.png", cPos);
		AbstractBufferComposant debugL = new DebugLayer("Go", souris);
		fm.addMouseListener(souris);
		fm.init(curseurLayer, debugL);

		while (true) {

			fm.repaint();

			Vector2i autrePos = souris.getFramePosition();
			cPos.set(autrePos.x/fm.getSpriteLength(), autrePos.y/fm.getSpriteHeigt());
			Thread.sleep(16);

		}
	}

}
