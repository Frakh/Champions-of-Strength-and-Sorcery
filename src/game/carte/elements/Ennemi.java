package game.carte.elements;

import game.Heros;
import game.carte.IElement;

public class Ennemi implements IElement {

	private int nombre;
	private int id;

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
		return null;
	}

}
