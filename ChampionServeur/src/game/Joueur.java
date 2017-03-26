package game;

import utilitaire.BaseThread;
import utilitaire.SocketFlux;

public class Joueur extends BaseThread {

	private SocketFlux socketFlux;

	public Joueur(SocketFlux s) {
		this.socketFlux = s;
	}

	@Override
	public void run() {

	}
}
