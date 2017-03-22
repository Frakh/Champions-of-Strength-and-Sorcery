package game.carte.elements;

import game.carte.IElement;
import utilitaire.ConteneurGeneric;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ElementFactory {

	private static ConteneurGeneric cont_gen = new ConteneurGeneric();

	static {
		//A remplir pour creer les elements
	}

	public IElement getIelement(String nomIelement) throws InvocationTargetException, IllegalAccessException {
		return (IElement) ((Method)cont_gen.getAttribut(nomIelement)).invoke(null);
	}

}
