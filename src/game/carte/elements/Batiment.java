package game.carte.elements;

import game.Heros;
import game.Joueur;
import game.carte.IElement;

public class Batiment implements IElement{
	public Joueur propri�taire;
	private int id; //parce que un jour, je suis presque s�r qu'on saura faire l'association entre id et batiment
	
	@Override
	public String getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void interagir(Heros oui) {
		/*if (!(propri�taire.equals(oui.getJoueur())))
		 	propri�taire=oui.getJoueur();
		   else faire des trucs d�pendants du bat�ment et je sais pas comment on va faire mais programmer quarante classes de batiments semble chiant
		   � terme il faudra peut-�tre rendre batiment abstraite et faire des impl�mentations*/
	}
	
	@Override
	public String d�crire() {
		// je sais absolument pas comment on va associer les id aux batiments associ�s mais c'est pas grave
		return null;
	}
}
