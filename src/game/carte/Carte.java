package game.carte;

import es.interfaces.IPosition;
import es.sortie.composants.IGoodComp;

import java.util.HashMap;
import java.util.Map;

import static sun.reflect.Reflection.getCallerClass;


public class Carte {
	private Case[][] sol;
	private Map<IPosition,IElement> elements;
	
	//le constructeur du debug wesh
	public Carte(int hauteur, int largeur, HashMap<IPosition,IElement> elements){
		sol = new Case[hauteur][largeur];
		this.elements=elements;
	}
	
	//le constructeur du fichier texte
	//public Carte(String path) mais j'ai la flemme pour l'instant
	
	public Case getCase(int x, int y){
		return sol[y][x];
	}
	
	public IElement getElement(int x, int y){
		return elements.get(new Position(x,y));
	}

	public Case[][] getSol() {
		//Verification pour savoir dans quelle classe se trouve la methode appelant cette methode
		if (!accessCheck(getCallerClass()))
			throw new RuntimeException("L'appelant n'est pas friendly");
		return sol;
	}

	public Map<IPosition, IElement> getElements() {
		if (!accessCheck(getCallerClass()))
			throw new RuntimeException("L'appelant n'est pas friendly");
		return elements;
	}

	/**
	 * Methode verifiant si la classe donné est une classe implémentant l'interface IGoodComponent
	 *
	 * !!! JE NE SAIS PAS SI CA MARCHE !!!
	 *
	 * @param c : la classe en question
	 * @return true si la classe passé en paramètre implément IGoodComp, false sinon
	 */
	private boolean accessCheck(Class c) {
		Class[] interfaces = c.getInterfaces();
		for (Class i : interfaces) {
			if (i.equals(IGoodComp.class))
				return true;
		}
		return false;
	}
}
