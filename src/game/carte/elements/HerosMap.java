package game.carte.elements;

import game.Heros;
import game.carte.IElement;

public class HerosMap implements IElement {

	private Heros heros; //le h�ros auquel l'�l�ment de map correspond

	@Override
	public String getImage() {
		return null;
	}

	@Override
	public void interagir(Heros oui) {
		// baston(this, oui);
	}

	@Override
	public String decrire() {
		return heros.getNom();
	}

}
