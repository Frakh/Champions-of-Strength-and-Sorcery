package game.carte.elements;

import game.Heros;
import game.carte.IElement;

public class Ressource implements IElement{
	//ressource = les tas de ressources et les coffres, d'o� n�cessit� d'abstracter interagir
	private int nombre;
	private int id; //permet de retrouver le type de ressource (ou le fait que c'est un coffre)
	
	public void interagir(Heros oui){
		//oui.ramasserRessource(id, nombre);
	}

	@Override
	public String getImage() {
		//getSprite(id);
		return null;
	}

	@Override
	public String d�crire() {
		// je sais absolument pas comment on va associer les id aux ressource associ�es mais c'est pas grave
		return null;
	}
}
