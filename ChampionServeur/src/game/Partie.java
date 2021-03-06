package game;

import es.netclasses.Evenement;
import es.netclasses.evenements.JeuEvenement;
import utilitaire.BaseThread;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;

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
	//La queue qui va servir de redispacther d'évenement
	private Queue<EvenementConteneur> queueDevent;
	//La queue qui va gérer les évenements destinés au serveur
	private Queue<EvenementConteneur> servEvenements;

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
		joueurs.add(proprietaire);
		this.id = id_cpt++;
		this.queueDevent = new ConcurrentLinkedQueue<>();
		this.servEvenements = new ConcurrentLinkedQueue<>();
	}

	/**
	 * Methode static qui permet de créer une partie et de la lancer dans un nouveau thread
	 * @param j : le joueur
	 * @param map : la carte ( l'url )
	 */
	public static void creerPartie(Joueur j, String map) {
		new Partie(map, j).start();
		j.start();
		System.out.println("Creation de partie");
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
		conti_run = true;
		while (conti_run) {
			for (Joueur j : joueurs) {
				Evenement eve = null;
				do {
					eve = j.readEvenement();
					if (eve!=null) {
						this.queueDevent.add(new EvenementConteneur(eve, j));
						System.out.println("Evenement reçu et stacked");
					}
				} while (eve!=null);
			}

			//Redispachage des evenements aux autres joueurs
			EvenementConteneur redispat = queueDevent.poll();
			while (redispat!=null) {
				for (Joueur j : joueurs) {
					if (!j.equals(redispat.getDeposeur())) {
						j.sendEvenement(redispat.getEvenement());
						System.out.println("Envoie evenement : " + j.getClass().getName());
					}
				}
				redispat = queueDevent.poll();
			}

			List<Joueur> toDel = new ArrayList<>();
			for (Joueur j : joueurs) {
				if (!j.isValid)
					toDel.add(j);
			}
			for (Joueur j : toDel) {
				joueurs.remove(j);
			}

			try {
				Thread.sleep(4);
			} catch (InterruptedException e) {
			}
		}
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
		joueur.start();
		System.out.println("Partie rejointe");
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
