package game.carte.elements;

import es.eventlogger.LogSys;
import game.carte.IElement;
import utilitaire.ConteneurGeneric;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ElementFactory {

	private static ConteneurGeneric cont_gen = new ConteneurGeneric();

	static {
		//A remplir pour creer les elements
		/*
		Exemple :

		cont_gen.add_set_Attribut(<nom_du_monstre>, <methode_charger_de_creer_le_dit_monstre>);
		 */
	}

	public static IElement getIelement(String nomIelement) {
		if (nomIelement==null)
			return null;
		try {
			return (IElement) ((Method)cont_gen.getAttribut(nomIelement)).invoke(null);
		} catch (IllegalAccessException | InvocationTargetException e) {
			LogSys.log(e, "Erreur ElementFactory.getIelement");
			return null;
		}
	}
}
