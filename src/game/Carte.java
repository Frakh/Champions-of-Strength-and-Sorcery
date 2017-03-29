package game;

import es.exception.IllegalFriendException;
import es.sortie.composants.CarteLayer;
import es.sortie.composants.ObjetLayer;
import game.carte.Case;
import game.carte.IElement;
import game.carte.PFUtil;
import game.carte.elements.HerosMap;
import utilitaire.Vector2i;


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

	public boolean canMove(Vector2i pos){
		return sol[pos.getX()][pos.getY()].getMvtCost() != 0;
	}

	public Case getCase(int x, int y){
		return sol[x][y];
	}

	public IElement getElement(int x, int y){
		return elements[x][y];
	}

	public int getHeight() {
		return this.sol[0].length;
	}

	public int getWidth() {
		return this.sol.length;
	}

	public Vector2i getDimensions() {
		return new Vector2i(this.sol.length, this.sol[0].length);
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

	public IElement checkElement(int x, int y) {
		try {
			if (elements[x][y] != null) return elements[x][y];
			int i = x-1;
			int j = y-1;
			if (i<0) i=0;
			if (j<0) j=0;
			if (x == elements.length-1) x=x-1;
			if (y == elements[0].length-1) y=y-1;
			while (i <= x+1){
				while (j <= y+1){
					if (i != x && j != y) {
						if (elements[i][j] != null) return elements[i][j];
					}
					j++;
				}
				i++;
			}
			return null;
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Erreur lors de la lecture : x=" + x + " - y=" + y);
			throw new RuntimeException(e);
		}
	}


	public PFUtil[][] pathfinding(int coordHerosL, int coordHerosH, int nbPointsMouv){ // renvoie un tableau de bools, représentant les cases accessibles par un monstre qui marche par terre
		int k=-1;
		int l=-1;
		PFUtil trucARetourner[][] = new PFUtil[getWidth()][getHeight()];
		int truc[][] = new int[getWidth()][getHeight()]; // ai besoin de truc pour avoir faux, vrai et a rendre vrai
		for(int i=0; i<getWidth(); i++){
			for (int j=0; j<getHeight(); j++){
				trucARetourner[i][j] = new PFUtil();		// case non visitee = -1
				truc[i][j]=0;								// case accessible = + de 0
			}
		}
		trucARetourner[coordHerosL][coordHerosH].setCoord(0);
		truc[coordHerosL][coordHerosH]=0;
		int mouvement = nbPointsMouv;
		boolean Continue=true;
		while (Continue){//while for for if if while if while if. bon appetit bien sur.
			for(int i=0; i<getWidth(); i++){
				for (int j=0; j<getHeight(); j++){
					if (trucARetourner[i][j].getCoord() != -1
							&& !trucARetourner[i][j].isEvent()){
						k=i-1;
						if (k<0) {k=0;}
						while (k <= i+1 && k<getWidth()){//double boucle while parce que for me faisait sortir du tableau
							l=j-1;						  //talent
							if (l<0) {l=0;}
							while (l <= j+1 && l<getHeight()){
								if ((trucARetourner[k][l].getCoord()>trucARetourner[i][j].getCoord()+sol[k][l].getMvtCost())
										|| trucARetourner[k][l].getCoord()==-1)
									if(canMove(new Vector2i(k,l))
											&& (trucARetourner[i][j].getCoord()+sol[k][l].getMvtCost()<mouvement)){
										trucARetourner[k][l].setCoord(trucARetourner[i][j].getCoord()+sol[k][l].getMvtCost());
										if (elements[k][l]!=null)
											trucARetourner[k][l].setEvent(true);
									}
								l++;
							}
							k++;
						}
					}
				}
			}
			Continue=false;
			for(int aa=0; aa<getWidth(); aa++){
				for (int bb=0; bb<getHeight(); bb++){
					if (trucARetourner[aa][bb].getCoord()!=truc[aa][bb]){
						Continue = true;
						truc[aa][bb]=trucARetourner[aa][bb].getCoord();
					}
				}
			}
		}
		trucARetourner[coordHerosL][coordHerosH].setEvent(true);
		return trucARetourner;
	}




	public boolean deplacer(HerosMap heros, int coordHerosL, int coordHerosH, Vector2i pos) throws Exception {
		if (heros==null)
			throw new NullPointerException("heros est null");
		if (pos==null)
			throw new NullPointerException("pos est null");
		PFUtil [][] tabDep = pathfinding(coordHerosL, coordHerosH, heros.getHeros().getPtDeplacement());
		toStringPF(coordHerosL, coordHerosH, heros.getHeros().getPtDeplacement());
		PFUtil caseCible = tabDep[pos.getX()][pos.getY()];
		int coutDep = caseCible.getCoord();
		if(coutDep < 0) {
			System.out.println("Case trop loin du heros");
			return false;
		}
		else {
			heros.getHeros().setPtDeplacement(heros.getHeros().getPtDeplacement() - coutDep);
			elements[pos.getX()][pos.getY()] = heros;
			elements[coordHerosL][coordHerosH] = null;
			if(caseCible.isEvent()) {
				elements[pos.getX()][pos.getY()].interagir(heros.getHeros());
			}
			return true;
		}
	}

	public void addElement(IElement e,int x,int y){
		if(elements[x][y]==null) elements[x][y]=e;
	}

	public Vector2i getCoordHeros(HerosMap h){
		for(int i=0;i<elements.length;++i){
			for(int j=0;j<elements[0].length;++j){
				if (elements[i][j] != null && elements[i][j].equals(h)){ //on cherche le heros dans la carte
					return new Vector2i(i,j);  //et on renvoie ses coordonnées, où apparemment i=largeur et j=hauteur
				}
			}
		}
		return null; //le heros n'est pas dans la carte
	}



	public void toStringPF(int coordHerosL, int coordHerosH, int nbPointsMouv){
		String aaa = "";
		PFUtil [][] tabDep = pathfinding(coordHerosL, coordHerosH, nbPointsMouv);
		for(int j=0; j<tabDep[0].length; j++){
			for(int i=0; i<tabDep.length; i++){
				aaa+= tabDep[i][j].getCoord();
				aaa+=" ";
			}
			aaa+="\n\n";
		}
		aaa+="\n";
		for(int j=0; j<tabDep[0].length; j++){
			for(int i=0; i<tabDep.length; i++){
				if (tabDep[i][j].isEvent())
					aaa+="o ";
				else
					aaa+="x ";
			}
			aaa+="\n";
		}
		System.out.println(aaa);
	}


}