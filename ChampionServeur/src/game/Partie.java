package game;

import es.netclasses.evenements.eventimpl.JeuEvenement;
import utilitaire.BaseThread;

import java.util.Vector;

public class Partie extends BaseThread {

	//Liste des parties n'ayant pas commencé
	private static Vector<Partie> partiesAttente = new Vector<>();
	//Liste des parties qui sont en cours d'execution
	private static Vector<Partie> partiesEnCours = new Vector<>();

	//Compteur d'id
	private static int id_cpt = 0;

	//La liste des joueurs
	private Vector<Joueur> joueurs;
	//Le propriétaire de la partie
	private Joueur proprietaire;
	//La chaine de charactere donné par la map
	private String map;
	//Id de la partie
	private int id;

	/**
	 * CTOR unique
	 * @param map : la carte qui sera utilisé
	 * @param proprietaire : le joueur propriétaire de la partie
	 */
	private Partie(String map, Joueur proprietaire) {
		partiesAttente.add(this);
		this.proprietaire = proprietaire;
		this.map = map;
		joueurs = new Vector<>();
		this.id = id_cpt++;
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
		return "Liste des parties joinables : \n" + partiesAttente.toString();
	}

	public static String getPartiesEnCours() {
		return "Liste des parties en cours :\n" + partiesEnCours.toString();
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
		return "ID de la partie :" + id + "Nombre de joueurs : " + joueurs.size() + '\n';
	}

	/**
	 * Permet de rejoindre une partie ( id de la partie, puis le joueur )
	 * @param i : l'id
	 * @param joueur : le joueur
	 */
	public static void rejoindre(final int i, final Joueur joueur) {
		getPartieById(i).ajouterJoueur(joueur);
	}

	/**
	 * Permet d'obtenir une partie par son id
	 * @param id : l'identifiant de la partie
	 * @return : null si non trouvé, une référence utilisable sinon
	 */
	public static Partie getPartieById(int id) {
		if (partiesAttente.size()>0) {
			for (Partie p : partiesAttente) {
				if (p.id == id)
					return p;
			}
		}
		if (partiesEnCours.size()>0) {
			for (Partie p : partiesEnCours) {
				if (p.id == id) {
					return p;
				}
			}
		}
		return null;
	}

	/**
	 * Ajout d'un joueur dans la partie
	 * @param joueur : le joueur
	 */
	private void ajouterJoueur(final Joueur joueur) {
		joueurs.add(joueur);
	}

	/**
	 * Permet de tuer une partie
	 */
	private void killPartie() {
		conti_run = false;
		for (Joueur j : this.joueurs) {
			j.sendEvenement(new JeuEvenement(JeuEvenement.PARTIE_KILLED, ""));
		}
	}

	/**
	 * Permet de tuer une partie
	 * @param id : l'id de la partie
	 */
	public static void killPartie(int id) {
		getPartieById(id).killPartie();
	}

	/**
	 * Permet de tuer toutes les parties
	 */
	public static void killAll() {
		for (Partie p : partiesAttente) {
			p.killPartie();
		}
		for (Partie p : partiesEnCours) {
			p.killPartie();
		}
	}
}
