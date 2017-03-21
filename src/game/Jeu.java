package game;

import game.carte.Carte;

public class Jeu {
	private Carte surface, souterrain;
	private Joueur[] joueurs;
	
	public Jeu (Carte surface, Carte souterrain, Joueur[] joueurs) {
		this.surface = surface;
		this.souterrain = souterrain;
		this.joueurs = joueurs;
	}

	public static boolean estGagne() {
		return false;
	}
}