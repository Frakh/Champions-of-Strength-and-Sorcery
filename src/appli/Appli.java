package appli;

import es.entree.Souris;
import es.sortie.FrameManager;
import es.sortie.ImageConteneur;
import es.sortie.composants.DebugLayer;
import es.sortie.composants.InterfaceUtilisateurLayer;
import utilitaire.IPosition;
import utilitaire.IntRect;
import utilitaire.Position;
import utilitaire.Vector2i;

import java.awt.event.MouseEvent;

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
		
		Vector2i tailleBouton=new Vector2i(255,100);
		Vector2i positionBoutonCreer = new Vector2i(840,150);
		Vector2i positionBoutonRejoindre=new Vector2i(840,350);
		
		ImageConteneur boutonCreer=new ImageConteneur("./assets/img/ui/create.jpg",new IntRect(positionBoutonCreer.x,positionBoutonCreer.y,tailleBouton.x,tailleBouton.y),9);
		ImageConteneur boutonRejoindre=new ImageConteneur("./assets/img/ui/join.jpg",new IntRect(positionBoutonRejoindre.x,positionBoutonRejoindre.y,tailleBouton.x,tailleBouton.y),9);
		
		menu.ajouterImageUI(new ImageConteneur("./assets/img/ui/menu_fond.jpg",new IntRect(0,0,1280,720),-69));
		menu.ajouterImageUI(boutonCreer);
		menu.ajouterImageUI(boutonRejoindre);
		
		Souris mickey = Souris.getInstance(fm);

		DebugLayer dl = new DebugLayer(mickey);
		
		fm.init(menu, dl);
		fm.setFrameRateLimit(60);
		
		/*
		 là il faut:
		 -afficher le menu principal
		 -gérer les clics souris pour sélectionner des options(créer/rejoindre partie, etc)
		 je sais faire aucun des deux donc bon 
		*/

		while(true){
			//if (mickey.mousePressed())
			System.out.print("");
			int usedButtonUnique = mickey.getUniqueUsedButton();
			if (usedButtonUnique==MouseEvent.BUTTON1 && boutonCreer.getImageDrawingArea().contains(mickey.getInGamePosition())){
				gererCreerPartie(menu, mickey);
				//lorsque taper sur zone de texte, ajouterDonnesUI(Vector2i v, new capturekeyboard)
			}
			if (usedButtonUnique==MouseEvent.BUTTON1 && boutonRejoindre.getImageDrawingArea().contains(mickey.getInGamePosition())){
				//rejoindre une partie
				System.out.println("ah");
				menu.ajouterImageUI(new ImageConteneur("./assets/img/ui/fenetreRejoindre.jpg",new IntRect(100,50,700,600),10));
			}
		}
		
	}

	public static void gererCreerPartie(InterfaceUtilisateurLayer menu, Souris mickey) {
		boolean conti = true;

		ImageConteneur boutonFermer = new ImageConteneur("./assets/img/ui/nope.jpg", new IntRect(770, 50, 30, 30), 11);
		ImageConteneur boutonValider = new ImageConteneur("./assets/img/ui/valider.jpg", new IntRect(550, 450, 200, 90), 10);
		ImageConteneur fenetreCreer = new ImageConteneur("./assets/img/ui/fenetreCreer.jpg", new IntRect(100, 50, 700, 600), 10);

		menu.ajouterImageUI(fenetreCreer);
		menu.ajouterImageUI(boutonFermer);
		menu.ajouterImageUI(boutonValider);

		while (conti) {
			//créer une partie
			int ub = mickey.getUniqueUsedButton();
			if (ub == MouseEvent.BUTTON1 && boutonFermer.getImageDrawingArea().contains(mickey.getInGamePosition())) {
				menu.retirerImageUI(fenetreCreer);
				menu.retirerImageUI(boutonValider);
				menu.retirerImageUI(boutonFermer);
				conti = false;
				System.out.println("oui");

			}
		}
	}
}
