package game.carte.elements;

import game.Heros;
import game.Joueur;
import game.carte.IElement;

public class ChateauMap implements IElement{
	private Joueur propri�taire;
	//private ChateauInside chateau; //les fonctions de cr�ation de bat�ment, recrutement etc
	
	@Override
	public String getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void interagir(Heros oui) {
		/*if (!(propri�taire.equals(oui.getJoueur())))
		 	baston;
		   else ouvrir menu chateau*/
	}
	
	@Override
	public String d�crire() {
		return null;
	}
}