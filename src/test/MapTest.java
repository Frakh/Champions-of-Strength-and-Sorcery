package test;

import es.entree.ControlleurJoueur;
import es.interfaces.IController;
import es.interfaces.IFileLoader;
import es.sortie.FrameManager;
import es.sortie.composants.AbstractBufferComposant;
import es.sortie.composants.CarteLayer;
import es.sortie.composants.CurseurLayer;
import es.sortie.composants.ObjetLayer;
import game.Heros;
import game.Joueur;
import game.carte.Carte;
import game.carte.elements.HerosMap;

import org.junit.Test;
import utilitaire.IPosition;
import utilitaire.Position;
import utilitaire.Vector2i;

import java.io.IOException;

import static java.awt.event.KeyEvent.*;

public class MapTest {

	@Test
	public void testMapDisp() throws IOException, InterruptedException {

		Carte c = IFileLoader.loadCarte("./ressources/map/nexttest.gameres");

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
		Carte c = IFileLoader.loadCarte("./ressources/map/nexttest.gameres");

		FrameManager fm = new FrameManager();
		IPosition ip = new Position(0,0);

		fm.setSpriteDim(32, 32);
		fm.setPositionToFollow(ip);
		fm.setDimensions(1280,720);

		Vector2i cursorPos = new Vector2i(0,0);
		AbstractBufferComposant carteLayer = new CarteLayer(fm, c);
		AbstractBufferComposant curseurLayer = new CurseurLayer(fm, "./assets/img/SPRITES/PEUNEUGEU/Curseur.png", cursorPos);
		fm.init(carteLayer, curseurLayer);

		fm.setFrameRateLimit(60);

		IController ic = new ControlleurJoueur();
		ic.setActionMap(0, VK_D);
		ic.setActionMap(1, VK_S);
		ic.setActionMap(2, VK_Q);
		ic.setActionMap(3, VK_Z);

		while (true) {

			if (ic.isJustPress(0) && cursorPos.x < c.getWidth()-1)
				cursorPos.x++;
			if (ic.isJustPress(2) && cursorPos.x>0)
				cursorPos.x--;
			if (ic.isJustPress(3) && cursorPos.y>0)
				cursorPos.y--;
			if (ic.isJustPress(1) && cursorPos.y < c.getHeight()-1)
				cursorPos.y++;
			fm.setPositionToFollow(new Position(cursorPos.x, cursorPos.y));

			Thread.sleep(16);
		}
	}
	
	@Test
	public void testDeplacerHeros() throws IOException, InterruptedException {
		Carte c = IFileLoader.loadCarte("./ressources/map/nexttest.gameres");
		
		Joueur noxus = new Joueur();
		Vector2i curseur=noxus.getCurseur();
		IController ic=noxus.getController();
		noxus.addHeros(new Heros()); //on créé un héros sans nom parce que balek
		HerosMap darius=noxus.getHerosMap(0); //idéalement faudrait pouvoir get le heros avec son nom aussi, parce que là on triche un peu
		darius.setPtMouvement(50);

		if (darius==null)
			throw new RuntimeException("Darius est null");
		
		c.addElement(darius, 0, 0); //on fait démarrer le héros en 0,0
		System.out.println("oui");
												  

		FrameManager fm = new FrameManager();
		IPosition ip = new Position(0,0);

		fm.setSpriteDim(32, 32);
		fm.setPositionToFollow(ip);
		fm.setDimensions(1280,720);

		AbstractBufferComposant carteLayer = new CarteLayer(fm, c);
		AbstractBufferComposant curseurLayer = new CurseurLayer(fm, "./assets/img/SPRITES/PEUNEUGEU/Curseur.png", curseur);
		AbstractBufferComposant elemLayer = new ObjetLayer(fm, c);
		fm.init(carteLayer, elemLayer, curseurLayer);

		

		while (true) {
			fm.repaint();

			if (ic.isJustPress(2) && curseur.y < c.getHeight()-1)
				noxus.curseurDown();;
			if (ic.isJustPress(4) && curseur.x>0)
				noxus.curseurLeft();;
			if (ic.isJustPress(6) && curseur.x<c.getWidth())
				noxus.curseurRight();
			if (ic.isJustPress(8) && curseur.y >0)
				noxus.curseurUp();
			if (ic.isJustPress(5)){
				if (c.getElement(curseur.x,curseur.y)!=null){ //si la case n'est pas vide
					if(c.getElement(curseur.x,curseur.y).getClass().getName().equals("game.carte.elements.HerosMap")){ //est-ce un héros ?
						HerosMap sousLeCurseur = (HerosMap) c.getElement(curseur.x,curseur.y);
						if(noxus.herosContains(sousLeCurseur)){ //est-ce que ce héros est à moi ?
							noxus.setHerosSelectionne(sousLeCurseur);
						}
					}
				}
				//si la case est vide
				else{
					Vector2i coord=c.getCoordHeros(noxus.getHerosSelectionne());
					try {
						c.deplacer(noxus.getHerosSelectionne(), coord.x, coord.y, noxus.getCurseur());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			fm.setPositionToFollow(new Position(noxus.getCurseur().x, noxus.getCurseur().y));

			Thread.sleep(16);
		}
	}

}
