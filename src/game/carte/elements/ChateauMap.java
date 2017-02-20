package game.carte.elements;

import game.Heros;
import game.Joueur;
import game.carte.IElement;

public class ChateauMap implements IElement{
	private Joueur propriétaire;
	//private ChateauInside chateau; //les fonctions de création de batîment, recrutement etc
	
	@Override
	public String getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void interagir(Heros oui) {
		/*if (!(propriétaire.equals(oui.getJoueur())))
		 	baston;
		   else ouvrir menu chateau*/
	}
	
	@Override
	public String décrire() {
		return null;
	}
}