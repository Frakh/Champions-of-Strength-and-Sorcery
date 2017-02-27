package game.carte;

import es.exception.IllegalFriendException;
import es.sortie.composants.CarteLayer;
import es.sortie.composants.ObjetLayer;
import utilitaire.IPosition;


public class Carte {
	private Case[][] sol;
	private IElement[][] elements;

	public Carte(Case[][] sol) {
		this.sol = sol;
	}

	public Carte(Case[][] sol, IElement[][] elements) {
		// Copie de pointers
		this.sol = sol;
		this.elements = elements;
	}
	
	//le constructeur du debug wesh
	public Carte(int largeur, int hauteur, Case[][] sol, IElement[][] elements){
		sol = new Case[largeur][hauteur];
		if (sol == null || elements==null)
			throw new IllegalArgumentException("Argument null : " + (sol==null?"sol":"elements"));

		this.sol=sol;
		this.elements = elements;
	}
	
	//le constructeur du fichier texte
	//public Carte(String path) mais j'ai la flemme pour l'instant
	
	public boolean canMove(IPosition pos){
		if (sol[(int) pos.getX()][(int) pos.getY()].getMvtCost()==0) return false;
		return true;
	}
	
	public Case getCase(int x, int y){
		return sol[y][x];
	}
	
	public IElement getElement(int x, int y){
		return elements[x][y];
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
	public IElement[][] getElements(ObjetLayer.Friend friend) {
		if (friend==null)
			throw new IllegalFriendException();
		return elements;
	}
	
	public IElement checkElement(int x, int y){
		if (elements[x][y]!=null) return elements[x][y];
		for (int i=x-1;i<=x+1;++i){
			for (int j=y-1;j<=y+1;++j){
				if(i!=x&&j!=y){
					if (elements[i][j]!=null) return elements[i][j];
				}
			}
		}
		return null;
	}
}
