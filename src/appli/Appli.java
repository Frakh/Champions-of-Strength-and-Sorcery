package appli;

import es.sortie.FrameManager;
import es.sortie.composants.AbstractBufferComposant;
import es.sortie.composants.CarteLayer;
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
		menu.ajouterImageUI(new Vector2i(0,0),"assets/img/ui/menu_fond.jpg");

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
