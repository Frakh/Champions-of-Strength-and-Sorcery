package appli;

import es.entree.CaptureKeyboard;
import es.entree.Souris;
import es.netclasses.NetworkInterface;
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
		Vector2i positionBoutonJouer = new Vector2i(840,150);
		Vector2i positionBoutonCredits=new Vector2i(840,350);
		
		ImageConteneur boutonJouer=new ImageConteneur("./assets/img/ui/play.jpg",new IntRect(positionBoutonJouer.x,positionBoutonJouer.y,tailleBouton.x,tailleBouton.y),9);
		ImageConteneur boutonCredits=new ImageConteneur("./assets/img/ui/credits.jpg",new IntRect(positionBoutonCredits.x,positionBoutonCredits.y,tailleBouton.x,tailleBouton.y),9);
		
		menu.ajouterImageUI(new ImageConteneur("./assets/img/ui/menuFond.PNG",new IntRect(0,0,1280,720),-69));
		menu.ajouterImageUI(boutonJouer);
		menu.ajouterImageUI(boutonCredits);
		
		Souris mickey = Souris.getInstance(fm);

		DebugLayer dl = new DebugLayer(mickey, menu);
		
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
			System.out.print(""); //pour des raisons étranges, ça ne marche pas sans
			int usedButtonUnique = mickey.getUniqueUsedButton();
			if (usedButtonUnique==MouseEvent.BUTTON1 && boutonJouer.getImageDrawingArea().contains(mickey.getInGamePosition())){
				gererLancementJeu(menu, mickey);
			}
			if (usedButtonUnique==MouseEvent.BUTTON1 && boutonCredits.getImageDrawingArea().contains(mickey.getInGamePosition())){
				gererCredits(menu, mickey);
			}
		}
		
	}

	public static void gererLancementJeu(InterfaceUtilisateurLayer menu, Souris mickey) {
		NetworkInterface.bind("172.19.47.220", 9001);
	}
	
	public static void gererCredits(InterfaceUtilisateurLayer menu, Souris mickey) {
		System.out.println("rejoindre partie");
		boolean conti = true;

		ImageConteneur fenetreCredits = new ImageConteneur("./assets/img/ui/fenetreCredits.jpg", new IntRect(100, 50, 700, 600), 10);
		ImageConteneur boutonFermer = new ImageConteneur("./assets/img/ui/nope.jpg", new IntRect(770, 50, 30, 30), 11);

		menu.ajouterImageUI(fenetreCredits);
		menu.ajouterImageUI(boutonFermer);

		while (conti) {
			System.out.print(""); //java est un monde étrange

			int ub = mickey.getUniqueUsedButton();
			if (ub == MouseEvent.BUTTON1 && boutonFermer.getImageDrawingArea().contains(mickey.getInGamePosition())) {
				System.out.println("retirer image");
				menu.retirerImageUI(fenetreCredits);
				menu.retirerImageUI(boutonFermer);
				conti = false;
				System.out.println("oui");
			}
		}
	}
}

/*System.out.println("lancer partie");
boolean conti = true;
String nom=null;

Code utilisé à l'époque où on avait créer/rejoindre partie
ImageConteneur fenetreCreer = new ImageConteneur("./assets/img/ui/fenetreCreer.jpg", new IntRect(100, 50, 700, 600), 10);
 
ImageConteneur boutonFermer = new ImageConteneur("./assets/img/ui/nope.jpg", new IntRect(770, 50, 30, 30), 11);
ImageConteneur boutonValider = new ImageConteneur("./assets/img/ui/valider.jpg", new IntRect(550, 450, 200, 90), 11);	
ImageConteneur zoneTexteNom=new ImageConteneur("./assets/img/ui/zonetexte.jpg", new IntRect(380, 120, 300, 30), 11);

menu.ajouterImageUI(fenetreCreer);
menu.ajouterImageUI(boutonFermer);
menu.ajouterImageUI(boutonValider);
menu.ajouterImageUI(zoneTexteNom);

while (conti) {
	//créer une partie
	System.out.print(""); //java est un monde étrange

	int ub = mickey.getUniqueUsedButton();
	if (ub == MouseEvent.BUTTON1 && boutonFermer.getImageDrawingArea().contains(mickey.getInGamePosition())) {
		System.out.println("retirer image");
		menu.retirerImageUI(fenetreCreer);
		menu.retirerImageUI(boutonValider);
		menu.retirerImageUI(boutonFermer);
		menu.retirerImageUI(zoneTexteNom);
		conti = false;
		System.out.println("oui");
	}
	if (ub == MouseEvent.BUTTON1 && zoneTexteNom.getImageDrawingArea().contains(mickey.getInGamePosition())){
		CaptureKeyboard ck =new CaptureKeyboard();
		Vector2i coordtext=zoneTexteNom.getImageDrawingArea().getXY();
		coordtext.set(coordtext.getX()+2,coordtext.getY()+((zoneTexteNom.getImageDrawingArea().height/3))*2);
		menu.ajouterDonnesUI(coordtext, ck);
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
		System.out.println("non");
	}

}
*/
