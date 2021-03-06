package game;

import com.sun.glass.events.KeyEvent;
import es.entree.ControlleurJoueur;
import es.interfaces.IControllable;
import es.interfaces.IController;
import game.carte.elements.Batiment;
import game.carte.elements.HerosMap;
import utilitaire.IPosition;
import utilitaire.Vector2i;

import java.util.ArrayList;

public class Joueur implements IControllable{
	private ArrayList<Batiment> batiments;
	private ArrayList<HerosMap> herosMap; //herosMap est la représentation graphique et contient Heros
	//private ArrayList<Chateau> chateaux;
	private ControlleurJoueur controleur;
	Vector2i curseur = new Vector2i(0,0);
	
	private HerosMap herosSelectionne; //le heros actuellement sélectionné
	
	
	public HerosMap getHerosSelectionne() {
		return herosSelectionne;
	}

	public void setHerosSelectionne(HerosMap herosSelectionne) {
		this.herosSelectionne = herosSelectionne;
	}

	public Joueur(){
		batiments = new ArrayList<>();
		herosMap = new ArrayList<>();
		//chateaux = new ArrayList<Chateau>();
		controleur = new ControlleurJoueur();
		controleur.setActionMap(2, KeyEvent.VK_DOWN);
		controleur.setActionMap(8, KeyEvent.VK_UP);
		controleur.setActionMap(4, KeyEvent.VK_LEFT);
		controleur.setActionMap(6, KeyEvent.VK_RIGHT);
		controleur.setActionMap(5, KeyEvent.VK_ENTER);
	}
		
	@Override
	public IController getController() {
		return controleur;
	}

	@Override
	public IPosition getPosition() { //pourquoi c'est là ça putain ?
		// TODO Auto-generated method stub
		return null;
	}
	
	public void addHeros(Heros h){
		herosMap.add(new HerosMap(h));
	}
	
	public Heros getHeros(int i){
		return herosMap.get(i).getHeros();
	}

	public HerosMap getHerosMap(int i) {
		return herosMap.get(i);
	}
	
	public boolean herosContains(HerosMap h){
		return herosMap.contains(h);
	}
	
	/* c'est peut-être pas ici qu'on le fait en fait
	public void deplacerCurseur(int action){
		switch (action){
		case 2: ;//faire des collages en bas
		case 8: ;//faire des collages en haut
		case 4: ;//faire des collages à gauche
		case 6: ;//faire des collages à droite
		}
	}
	
	public void validerCurseur(){
		//valider les collages
	}
	*/
	
	public void curseurUp(){
		curseur.y--;
	}
	
	public void curseurDown(){
		curseur.y++;
	}
	
	public void curseurLeft(){
		curseur.x--;
	}
	
	public void curseurRight(){
		curseur.x++;
	}
	
	public Vector2i getCurseur(){
		return curseur;
	}
	
}