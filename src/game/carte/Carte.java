package game.carte;

import java.util.HashMap;
import java.util.Map;

import es.interfaces.IPosition;


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
}
