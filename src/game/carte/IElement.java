package game.carte;

import es.interfaces.ISpriteDrawable;
import game.Heros;

public interface IElement extends ISpriteDrawable {
	void interagir(Heros oui) throws CaseDejaPriseException;
	String decrire();
}
