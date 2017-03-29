package test;

import es.entree.CaptureKeyboard;
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

		Souris souris = Souris.getInstance(fm);
		Vector2i cPos = new Vector2i(0,0);
		AbstractBufferComposant curseurLayer = new CurseurLayer(fm, "./assets/img/SPRITES/PEUNEUGEU/Curseur.png", cPos);
		AbstractBufferComposant debugL = new DebugLayer("Go", souris);
		fm.init(curseurLayer, debugL);

		while (true) {

			fm.repaint();

			Vector2i autrePos = souris.getInGamePosition();
			cPos.set(autrePos.x/fm.getSpriteWidth(), autrePos.y/fm.getSpriteHeigt());
			Thread.sleep(16);

		}
	}

	@Test
	public void testEntreeChaine() throws InterruptedException {

		FrameManager fm = new FrameManager();
		fm.setDimensions(640,360);

		fm.setPositionToFollow(new Position(0,0));

		CaptureKeyboard ck = new CaptureKeyboard();
		DebugLayer dl = new DebugLayer(ck);
		fm.init(dl);
		fm.setFrameRateLimit(60);

		ck.launchInNewThread().join();
	}

}
