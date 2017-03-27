package game;

import utilitaire.BaseThread;

import java.util.Vector;

public class Partie extends BaseThread {

	//Liste des parties n'ayant pas commencé
	public static Vector<Partie> partieVector = new Vector<>();

	//La liste des joueurs
	private Vector<Joueur> joueurs;
	//Le propriétaire de la partie
	private Joueur proprietaire;
	//La chaine de charactere donné par la map
	private String map;

	/**
	 * CTOR unique
	 * @param map : la carte qui sera utilisé
	 * @param proprietaire : le joueur propriétaire de la partie
	 */
	private Partie(String map, Joueur proprietaire) {
		partieVector.add(this);
		this.proprietaire = proprietaire;
		this.map = map;
		joueurs = new Vector<>();
	}

	/**
	 * Methode static qui permet de créer une partie et de la lancer dans un nouveau thread
	 * @param j : le joueur
	 * @param map : la carte ( l'url )
	 */
	public static void creerPartie(Joueur j, String map) {
		new Partie(map, j).start();
	}

	/**
	 * Obtient la liste des parties joinables
	 * @return : la liste des parties joinables
	 */
	public static String getPartiesJoinable() {
		return "Liste des parties joinables : \n" + partieVector.toString();
	}

	/**
	 * Le run
	 */
	@Override
	public void run() {

	}

	/**
	 * Le toString
	 * @return la string représentant la string
	 */
	@Override
	public String toString() {
		return "Nombre de joueurs : " + joueurs.size() + '\n';
	}

	/**
	 * Permet de rejoindre une partie ( id de la partie, puis le joueur )
	 * @param i : l'id
	 * @param joueur : le joueur
	 */
	public static void rejoindre(final int i, final Joueur joueur) {
		partieVector.get(i).ajouterJoueur(joueur);
	}

	/**
	 * Ajout d'un joueur dans la partie
	 * @param joueur : le joueur
	 */
	private void ajouterJoueur(final Joueur joueur) {
		joueurs.add(joueur);
	}
}
