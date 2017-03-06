package test;

import es.interfaces.IFileLoader;
import es.sortie.FrameManager;
import es.sortie.composants.CarteLayer;
import es.sortie.composants.IGoodComp;
import game.carte.Carte;
import org.junit.Test;
import utilitaire.IPosition;
import utilitaire.Position;

import java.io.IOException;

public class MapTest {

	@Test
	public void testMapDisp() throws IOException, InterruptedException {

		Carte c = new Carte(IFileLoader.loadCarteFile("./ressources/map/test.gameres"));

		FrameManager fm = new FrameManager();
		IPosition ip = new Position(0,0);

		fm.setSpriteDim(32, 32);
		fm.setPositionToFollow(ip);
		fm.setDimensions(1280,720);

		IGoodComp carteLayer = new CarteLayer(fm, c);

		fm.init(carteLayer);

		for (int i = 0; i < 10; ++i) {
			fm.repaint();
			Thread.sleep(5000);
		}


	}

}
