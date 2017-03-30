package appli;

import es.entree.CaptureKeyboard;
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
		
		menu.ajouterImageUI(new ImageConteneur("./assets/img/ui/menuFond.PNG",new IntRect(0,0,1280,720),-69));
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
			}
			if (usedButtonUnique==MouseEvent.BUTTON1 && boutonRejoindre.getImageDrawingArea().contains(mickey.getInGamePosition())){
				gererRejoindrePartie(menu, mickey);
			}
		}
		
	}

	public static void gererCreerPartie(InterfaceUtilisateurLayer menu, Souris mickey) {
		boolean conti = true;
		//String nom=null;
		
		ImageConteneur fenetreCreer = new ImageConteneur("./assets/img/ui/fenetreCreer.jpg", new IntRect(100, 50, 700, 600), 10);
		ImageConteneur boutonFermer = new ImageConteneur("./assets/img/ui/nope.jpg", new IntRect(770, 50, 30, 30), 11);
		ImageConteneur boutonValider = new ImageConteneur("./assets/img/ui/valider.jpg", new IntRect(550, 450, 200, 90), 11);	
		//ImageConteneur zoneTexteNom=new ImageConteneur("./assets/img/ui/zonetexte.jpg", new IntRect(380, 1230, 300, 30), 11);
		
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
			/*if (ub == MouseEvent.BUTTON1 && zoneTexteNom.getImageDrawingArea().contains(mickey.getInGamePosition())){
				CaptureKeyboard ck =new CaptureKeyboard();
				menu.ajouterDonnesUI(zoneTexteNom.getImageDrawingArea().getXY(), ck);
				try {
					ck.launchInNewThread().join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				nom=ck.toString();
			}
			
			if (ub == MouseEvent.BUTTON1 && boutonValider.getImageDrawingArea().contains(mickey.getInGamePosition())) {
				if(nom!=null){
					//lancer socket, utiliser nom, etc
					System.out.println("oui");
				}
			}
			*/
		}
	}
	public static void gererRejoindrePartie(InterfaceUtilisateurLayer menu, Souris mickey) {
		boolean conti = true;
		String nom=null;
		
		ImageConteneur fenetreCreer = new ImageConteneur("./assets/img/ui/fenetreCreer.jpg", new IntRect(100, 50, 700, 600), 10);
		ImageConteneur boutonFermer = new ImageConteneur("./assets/img/ui/nope.jpg", new IntRect(770, 50, 30, 30), 11);
		ImageConteneur boutonValider = new ImageConteneur("./assets/img/ui/valider.jpg", new IntRect(550, 450, 200, 90), 11);	
		ImageConteneur zoneTexteNom=new ImageConteneur("./assets/img/ui/zonetexte.jpg", new IntRect(380, 1230, 300, 30), 11);
		
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
			if (ub == MouseEvent.BUTTON1 && zoneTexteNom.getImageDrawingArea().contains(mickey.getInGamePosition())){
				CaptureKeyboard ck =new CaptureKeyboard();
				menu.ajouterDonnesUI(zoneTexteNom.getImageDrawingArea().getXY(), ck);
				try {
					ck.launchInNewThread().join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				nom=ck.toString();
			}
			
			if (ub == MouseEvent.BUTTON1 && boutonValider.getImageDrawingArea().contains(mickey.getInGamePosition())) {
				if(nom!=null){
					//lancer socket, utiliser nom, etc
					System.out.println("oui");
				}
			}
			
		}
	}
}
