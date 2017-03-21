package game;

import java.util.ArrayList;

import com.sun.glass.events.KeyEvent;

import es.entree.ControlleurJoueur;
import es.interfaces.IControllable;
import es.interfaces.IController;
import game.carte.elements.Batiment;
import javafx.scene.input.KeyCode;
import utilitaire.IPosition;

public class Joueur implements IControllable{
	private ArrayList<Batiment> batiments;
	private ArrayList<Heros> heros;
	//private ArrayList<Chateau> chateaux;
	private ControlleurJoueur controleur;
	
	public Joueur(){
		batiments = new ArrayList<Batiment>();
		heros = new ArrayList<Heros>();
		//chateaux = new ArrayList<Chateau>();
		controleur = new ControlleurJoueur();
		controleur.setActionMap(2, KeyEvent.VK_2);
		controleur.setActionMap(8, KeyEvent.VK_8);
		controleur.setActionMap(4, KeyEvent.VK_4);
		controleur.setActionMap(6, KeyEvent.VK_6);
		controleur.setActionMap(5, KeyEvent.VK_5);
	}
		
	@Override
	public IController getController() {
		return controleur;
	}

	@Override
	public IPosition getPosition() {
		// TODO Auto-generated method stub
		return null;
	}
	
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
	
}