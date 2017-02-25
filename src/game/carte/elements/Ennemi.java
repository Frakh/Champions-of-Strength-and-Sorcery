package game.carte.elements;

import game.Heros;
import game.Unite;
import game.carte.CaseDejaPriseException;
import game.carte.IElement;
import game.combat.Combat;

public class Ennemi implements IElement {

	private int nombre;
	private int id;

	public Ennemi(int n, int id){
		nombre=n;
		this.id=id;
	}
	
	@Override
	public String getImage() {
		return null;
	}

	@Override
	public void interagir(Heros oui) throws CaseDejaPriseException {
		Heros Mechaaaaant = new Heros();
		Mechaaaaant.addTroupe(new Unite(id, nombre), 0);
		Combat c = new Combat(oui, Mechaaaaant);
	}

	@Override
	public String decrire() {
		return null;
	}

}
