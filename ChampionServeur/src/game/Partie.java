package game;

import utilitaire.BaseThread;

import java.util.Vector;

public class Partie extends BaseThread {

	public static Vector<Partie> partieVector = new Vector<>();
	private static String partieJoinable;

	private Vector<Joueur> joueurs;

	public Partie(String map) {
		partieVector.add(this);
		joueurs = new Vector<>();
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
}
