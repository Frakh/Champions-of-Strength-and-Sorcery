package game.carte.elements;

import game.Heros;
import game.Joueur;
import game.carte.IElement;

public class Batiment implements IElement{
	public Joueur proprietaire;
	private int id; //parce que un jour, je suis presque sûr qu'on saura faire l'association entre id et batiment
	
	@Override
	public String getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void interagir(Heros oui) {
		/*if (!(propriétaire.equals(oui.getJoueur())))
		 	propriétaire=oui.getJoueur();
		   else faire des trucs dépendants du batîment et je sais pas comment on va faire mais programmer quarante classes de batiments semble chiant
		   à terme il faudra peut-être rendre batiment abstraite et faire des implémentations*/
	}

	@Override
	public String decrire() {

		return null;
	}
}
