package game.carte.elements;

import game.Heros;
import game.Joueur;
import game.carte.IElement;

public class ChateauMap extends Batiment{
	private Joueur proprietaire;
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
	public String decrire() {

		return null;
	}

	@Override
	public void activerJour() {
		// faire des trucs selon les batiments du ChateauInside
		
	}

	@Override
	public void activerSemaine() {
		// faire des trucs selon les batiments du ChateauInside
		
	}
}