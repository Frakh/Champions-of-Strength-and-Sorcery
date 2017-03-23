package game.carte;

import es.exception.IllegalFriendException;
import es.sortie.composants.CarteLayer;
import es.sortie.composants.ObjetLayer;
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
		// Code simplifié par l'ide
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
	
	public PFUtil[][] pathfinding(int coordHerosL, int coordHerosH, int nbPointsMouv){ // renvoie un tableau de bools, représentant les cases accessibles par un monstre qui marche par terre
		// case non visitée = -1
		// case accessible = + de 0
		int k;
		int l;
		PFUtil trucARetourner[][] = new PFUtil[getHeight()][getWidth()];
		int truc[][]=new int[getHeight()][getWidth()]; // ai besoin de truc pour avoir faux, vrai et à rendre vrai
		for(int i=0; i<getHeight(); i++){
			for (int j=0; j<getWidth(); j++){
				trucARetourner[i][j].setCoord(-1);
				truc[i][j]=0;
			}
		}
		trucARetourner[coordHerosL][coordHerosH].setCoord(0);
		int mouvement = 1000;// = case[coordHerosH][coordHerosL].getHeros.getMouvement;
		boolean Continue=true;
		while (Continue){//while for for if if while if while if. bon appétit bien sûr.
			for(int i=0; i<getHeight(); i++){
				for (int j=0; j<getWidth(); j++){
					if (trucARetourner[coordHerosL][coordHerosH].getCoord()!=-1 && !trucARetourner[coordHerosL][coordHerosH].isEvent()){
						k=i-1;
						if (k<0) {k=0;}
						while (k <= i+1 && k<getHeight()){//double boucle while parce que for me faisait sortir du tableau
							l=j-1;						  //talent
							if (l<0) {l=0;}
							while (l <= j+1 && l<getWidth()){	
								if (canMove(new Vector2i(k,l)) && (trucARetourner[k][l].getCoord()<trucARetourner[i][j].getCoord()+sol[k][l].getMvtCost())&&(trucARetourner[i][j].getCoord()+sol[k][l].getMvtCost()<mouvement)){
									trucARetourner[k][l].setCoord(trucARetourner[i][j].getCoord()+sol[k][l].getMvtCost());
									if (checkElement(k,l)!=null)
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
			for(int i=0; i<getHeight(); i++){
				for (int j=0; j<getWidth(); j++){
					if (trucARetourner[i][j].getCoord()!=truc[i][j]) Continue = true;
				}
			}
		}
		return trucARetourner;
	}
	
	public void deplacer(HerosMap heros, int coordHerosL, int coordHerosH, Vector2i pos) throws Exception {

		if (heros==null)
			throw new NullPointerException("heros est null");
		if (pos==null)
			throw new NullPointerException("pos est null");

		PFUtil [][] tabDep = pathfinding(coordHerosH, coordHerosL, heros.getHeros().getPtDeplacement());
		PFUtil caseCible = tabDep[pos.getX()][pos.getY()];
		int coutDep = caseCible.getCoord();
		if(coutDep < 0) {
			//throw new Exception("impossible de se deplacer la-bas");
		}
		else {
			heros.getHeros().setPtDeplacement(heros.getHeros().getPtDeplacement() - coutDep);
			elements[pos.getX()][pos.getY()] = heros;
			elements[coordHerosH][coordHerosL] = null;
			if(caseCible.isEvent()) {
				elements[pos.getX()][pos.getY()].interagir(heros.getHeros());
			}
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
}