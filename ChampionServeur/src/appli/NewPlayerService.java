package appli;


import es.netclasses.evenements.JeuEvenement;
import game.Joueur;
import game.Partie;
import utilitaire.SocketFlux;

public class NewPlayerService implements Runnable {

	private Thread thread;
	private Joueur joueur;

	public NewPlayerService(SocketFlux choset) {
		joueur = new Joueur(choset);
	}

	public Thread start() {
		thread = new Thread(this);
		thread.start();
		return thread;
	}


	@Override
	public void run() {

		System.out.println("New Player " + "Etablished Connection");

		JeuEvenement jevent = (JeuEvenement) joueur.getSocketFlux().readEvenement();

		switch (jevent.getMessageId()) {
			case JeuEvenement.GAME_LIST:
				joueur.getSocketFlux().writeEvenement(new JeuEvenement(JeuEvenement.GAME_LIST, Partie.getPartiesJoinable()));
				break;
			case JeuEvenement.CREATE_GAME:
				Partie.creerPartie(joueur, jevent.getDetail());
				return;
			case JeuEvenement.JOIN_GAME:
				try {
					Partie.rejoindre(Integer.parseInt(jevent.getDetail()), joueur);
					return;
				} catch (Exception e) {
					joueur.getSocketFlux().writeEvenement(new JeuEvenement(JeuEvenement.NOT_FOUND_GAME, ""));
				}
				break;
			default:
				break;
		}
	}
}
