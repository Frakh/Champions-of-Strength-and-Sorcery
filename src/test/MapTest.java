package test;

import es.entree.ControlleurJoueur;
import es.interfaces.IController;
import es.interfaces.IFileLoader;
import es.sortie.FrameManager;
import es.sortie.composants.CarteLayer;
import es.sortie.composants.CurseurLayer;
import es.sortie.composants.AbstractBufferComposant;
import game.carte.Carte;
import org.junit.Test;
import utilitaire.IPosition;
import utilitaire.Position;
import utilitaire.Vector2i;

import java.io.IOException;

import static java.awt.event.KeyEvent.*;

public class MapTest {

	@Test
	public void testMapDisp() throws IOException, InterruptedException {

		Carte c = new Carte(IFileLoader.loadCarteFile("./ressources/map/test.gameres"));

		FrameManager fm = new FrameManager();
		IPosition ip = new Position(0,0);

		fm.setSpriteDim(32, 32);
		fm.setPositionToFollow(ip);
		fm.setDimensions(1280,720);

		AbstractBufferComposant carteLayer = new CarteLayer(fm, c);

		fm.init(carteLayer);

		for (int i = 0; i < 10; ++i) {
			fm.repaint();
			Thread.sleep(5000);
		}
	}

	@Test
	public void testDispAndMove() throws IOException, InterruptedException {
		Carte c = new Carte(IFileLoader.loadCarteFile("./ressources/map/test.gameres"));

		FrameManager fm = new FrameManager();
		IPosition ip = new Position(0,0);

		fm.setSpriteDim(32, 32);
		fm.setPositionToFollow(ip);
		fm.setDimensions(1280,720);

		Vector2i cursorPos = new Vector2i(0,0);
		AbstractBufferComposant carteLayer = new CarteLayer(fm, c);
		AbstractBufferComposant curseurLayer = new CurseurLayer(fm, "./assets/img/SPRITES/PEUNEUGEU/Curseur.png", cursorPos);
		fm.init(carteLayer, curseurLayer);

		IController ic = new ControlleurJoueur();
		ic.setActionMap(0, VK_D);
		ic.setActionMap(1, VK_S);
		ic.setActionMap(2, VK_Q);
		ic.setActionMap(3, VK_Z);

		while (true) {
			fm.repaint();

			if (ic.isJustPress(0) && cursorPos.x < c.getWidth()-1)
				cursorPos.x++;
			if (ic.isJustPress(2) && cursorPos.x>0)
				cursorPos.x--;
			if (ic.isJustPress(3) && cursorPos.y>0)
				cursorPos.y--;
			if (ic.isJustPress(1) && cursorPos.y < c.getHeight()-1)
				cursorPos.y++;
			fm.setPositionToFollow(new Position(cursorPos.x, cursorPos.y));

			System.out.println("X : " + cursorPos.x + " - Y : " + cursorPos.y + " - Hauteur gonna haunt : " + c.getWidth());
			Thread.sleep(16);
		}
	}

}
