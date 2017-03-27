package appli;

import es.sortie.FrameManager;
import es.sortie.ImageConteneur;
import es.sortie.composants.InterfaceUtilisateurLayer;
import utilitaire.IPosition;
import utilitaire.Position;
import utilitaire.Vector2i;

public class Appli {
	public static void main (String[] args){
		//charger des trucs
		//lancer cinématique d'intro stylée
		//lancer menu principal
		
		FrameManager fm = new FrameManager();
		IPosition ip = new Position(0,0);

		fm.setSpriteDim(32, 32);
		fm.setPositionToFollow(ip);
		fm.setDimensions(1280,720);

		InterfaceUtilisateurLayer menu = new InterfaceUtilisateurLayer();
		
		menu.ajouterImageUI(new ImageConteneur("./assets/img/ui/menu_fond.jpg",new Vector2i(1280,720),new Vector2i(0,0),-69));
		menu.ajouterImageUI(new ImageConteneur("./assets/img/ui/create.jpg",new Vector2i(205,100),new Vector2i(740,200),9));
		menu.ajouterImageUI(new ImageConteneur("./assets/img/ui/join.jpg",new Vector2i(205,100),new Vector2i(740,400),9));
		
		fm.init(menu); 
		fm.setFrameRateLimit(60);
		
		/*
		 là il faut:
		 -afficher le menu principal
		 -gérer les clics souris pour sélectionner des options(créer/rejoindre partie, etc)
		 je sais faire aucun des deux donc bon 
		*/

		for (int i = 0; i < 10; ++i) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
