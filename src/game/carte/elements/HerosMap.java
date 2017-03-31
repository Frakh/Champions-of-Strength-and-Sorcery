package game.carte.elements;

import es.interfaces.ISpriteDrawable;
import game.Heros;
import game.carte.IElement;

public class HerosMap implements IElement, ISpriteDrawable {

	private Heros heros; //le héros auquel l'élément de map correspond
	
	public HerosMap(Heros h){
		heros=h;
	}

	public Heros getHeros() {
		return heros;
	}

	@Override
	public String getImage() {
		return "./assets/img/hero2.png";
	}

	@Override
	public void interagir(Heros oui) {
		// baston(this, oui);
	}

	public void setPtMouvement(int ptMouvement) {
		heros.setPtDeplacement(ptMouvement);
	}

	@Override
	public String decrire() {
		return heros.getNom();
	}

}
