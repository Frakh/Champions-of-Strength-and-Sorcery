package game.carte;

import utilitaire.IPosition;
import es.exception.IllegalFriendException;
import es.sortie.composants.CarteLayer;
import es.sortie.composants.ObjetLayer;
import utilitaire.Position;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class Carte {
	private Case[][] sol;
	private Map<IPosition,IElement> elements;
	
	//le constructeur du debug wesh
	public Carte(int largeur, int hauteur, Case[][] sol, HashMap<IPosition,IElement> elements){
		sol = new Case[largeur][hauteur];
		this.sol=sol;
		Set<IPosition> ip = elements.keySet();

		// Verification que toutes les positions soient dans la map
		for (IPosition p : ip) {
			// La hauteur, c'est Y, la largeur, c'est X
			// Si Y augmente, ça descend, si X augmente, ça vas vers la droite
			if (p.getX() < 0 || p.getY() < 0 || p.getX()>largeur || p.getY() > hauteur)
				throw new IllegalArgumentException("Une position ne rentre pas dans les dimensions : " + p.toString());
		}
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

	public int getHauteur() {
		return this.sol.length;
	}

	public int getLargeur() {
		return this.sol[0].length;
	}

	// friend class CarteLayer;
	public Case[][] getSol(CarteLayer.Friend friend) {	//allons nous faire des amis
		if (friend==null)								//je suis ton meilleur ami
			throw new IllegalFriendException("La classe appelante n'est pas friendly");
		return sol;
	}

	// friend class ObjetLayer;
	public Map<IPosition, IElement> getElements(ObjetLayer.Friend friend) {
		if (friend==null)
			throw new IllegalFriendException();
		return elements;
	}
}
