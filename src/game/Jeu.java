package game;

public class Jeu implements Runnable{
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

	@Override
	public void run() {
		//charger des trucs
		//lancer partie
		while (estGagne()){
			//tour de jeu
		}
	}
}