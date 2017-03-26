package appli;


import es.netclasses.evenements.eventimpl.JeuEvenement;
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

	public void processGameEvenement(JeuEvenement e) {

	}

	@Override
	public void run() {

		System.out.println("New Player " + "Etablished Connection");

		JeuEvenement jev = (JeuEvenement) joueur.getSocketFlux().readEvenement();

		switch (jev.getMessageId()) {
			case JeuEvenement.GAME_LIST:
				joueur.getSocketFlux().writeEvenement(new JeuEvenement(JeuEvenement.GAME_LIST, Partie.getPartiesJoinable()));
		}
	}
}
