package game;

import utilitaire.BaseThread;

import java.util.Vector;

public class Partie extends BaseThread {

	public static Vector<Partie> partieVector = new Vector<>();
	private static String partieJoinable;

	private Vector<Joueur> joueurs;
	private Joueur proprietaire;
	private String map;

	public Partie(String map, Joueur proprietaire) {
		partieVector.add(this);
		this.proprietaire = proprietaire;
		this.map = map;
		joueurs = new Vector<>();
	}

	public static void creerPartie(Joueur j, String map) {
		new Partie(map, j).start();
	}

	public static String getPartiesJoinable() {
		return "Liste des parties joinables : \n" + partieVector.toString();
	}

	@Override
	public void run() {

	}

	@Override
	public String toString() {
		return "Nombre de joueurs : " + joueurs.size() + '\n';
	}

	public static void rejoindre(final int i, final Joueur joueur) {
		partieVector.get(i).ajouterJoueur(joueur);
	}

	private void ajouterJoueur(final Joueur joueur) {
		joueurs.add(joueur);
	}
}
