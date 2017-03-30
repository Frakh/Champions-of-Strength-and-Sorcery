package test;

import es.entree.ControlleurJoueur;
import es.entree.Souris;
import es.interfaces.IController;
import es.interfaces.IFileLoader;
import es.sortie.FrameManager;
import es.sortie.ImageConteneur;
import es.sortie.composants.AbstractBufferComposant;
import es.sortie.composants.CarteLayer;
import es.sortie.composants.CurseurLayer;
import es.sortie.composants.DebugLayer;
import es.sortie.composants.InterfaceUtilisateurLayer;
import es.sortie.composants.ObjetLayer;
import game.Carte;
import game.Heros;
import game.Joueur;
import game.carte.elements.HerosMap;
import org.junit.Test;
import utilitaire.IPosition;
import utilitaire.IntRect;
import utilitaire.Position;
import utilitaire.Vector2i;

import java.io.IOException;

import static java.awt.event.KeyEvent.*;

public class MapTest {

/*
	@Test
	public void testDispAndMove() throws IOException, InterruptedException {
		Carte c = IFileLoader.loadCarte("./ressources/map/map.txt");

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
		ic.setActionMap(4, VK_A);

		boolean b = true;
		while (true) {

			if (ic.isJustPress(0) && cursorPos.x < c.getWidth()-1)
				cursorPos.x++;
			if (ic.isJustPress(2) && cursorPos.x>0)
				cursorPos.x--;
			if (ic.isJustPress(3) && cursorPos.y>0)
				cursorPos.y--;
			if (ic.isJustPress(1) && cursorPos.y < c.getHeight()-1)
				cursorPos.y++;
			if (ic.isJustPress(4)) {
				b = !b;
				fm.setLayerVisibility("es.sortie.composants.CarteLayer", b);
			}
			fm.setPositionToFollow(cursorPos.toPosition());

			Thread.sleep(16);
		}
	}
*/	
	
	@Test
	public void testBase() throws InterruptedException, IOException {

		FrameManager fm = new FrameManager();
		IPosition ip = new Position(20,11);

		fm.setSpriteDim(32, 32);
		fm.setPositionToFollow(ip);
		fm.setDimensions(1280,720);

		Souris souris = Souris.getInstance(fm);
		Vector2i cPos = new Vector2i(0,0);
		
		Carte c = IFileLoader.loadCarte("./ressources/map/map.txt");
		
		AbstractBufferComposant curseurLayer = new CurseurLayer(fm, "./assets/img/SPRITES/PEUNEUGEU/Curseur.png", cPos);
		AbstractBufferComposant debugL = new DebugLayer("Go", souris);
		AbstractBufferComposant elemLayer = new ObjetLayer(fm, c);
		
		CarteLayer cl = new CarteLayer(fm, c);
		InterfaceUtilisateurLayer uil= new InterfaceUtilisateurLayer();
		
		Joueur noxus = new Joueur();
		Vector2i curseur=noxus.getCurseur();
		IController ic=noxus.getController();
		noxus.addHeros(new Heros("dar kwadeaure"));
		HerosMap darius=noxus.getHerosMap(0);
		c.addElement(darius, 2, 2); //on fait demarrer le heros en 2,2
		
		fm.init(cl, elemLayer, debugL, curseurLayer);
		fm.setFrameRateLimit(30);
		
		uil.ajouterImageUI(new ImageConteneur("assets/img/ui/fin.jpg", new IntRect(640,10,60,60),42));
		
		int truc=-1;
		int lo=-1;
		int la=-1;
		Vector2i coord = new Vector2i(-1,-1);;
		while (true) {
			fm.repaint();
			Vector2i autrePos = souris.getInGamePosition();
			cPos.set(autrePos.x/fm.getSpriteWidth(), autrePos.y/fm.getSpriteHeigt());
			truc=souris.getUniqueUsedButton();
			lo = souris.getInGamePosition().getX()*c.getWidth()/1280;
			la = souris.getInGamePosition().getY()*c.getHeight()/720;
			if (truc==1 && lo<22 && lo > 19 && la < 2){
				darius.setPtMouvement(1500);
			}
			else if (truc==1){

				coord=c.getCoordHeros(darius);
				try {
					c.deplacer(darius, coord.x, coord.y, new Vector2i(lo,la));
				} catch (Exception e) {}
				truc=-1;
				lo=-1;
				la=-1;
			}
			Thread.sleep(100);

		}
	}
	
	/*
		while (true) {
			fm.repaint();

			if (ic.isJustPress(2) && curseur.y < c.getHeight()-1)
				noxus.curseurDown();;
			if (ic.isJustPress(4) && curseur.x>0)
				noxus.curseurLeft();;
			if (ic.isJustPress(6) && curseur.x<c.getWidth()-1)
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
					if (noxus.getHerosSelectionne()!=null){ //dans le vrai jeu t'auras toujours un heros selectionne, mais bon
						Vector2i coord=c.getCoordHeros(noxus.getHerosSelectionne());
						try {
							c.deplacer(noxus.getHerosSelectionne(), coord.x, coord.y, noxus.getCurseur());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
			
			fm.setPositionToFollow(new Position(noxus.getCurseur().x, noxus.getCurseur().y));

			Thread.sleep(16);
		}
	}
	*/
}
