package game;

import utilitaire.BaseThread;
import utilitaire.SocketFlux;

public class Joueur extends BaseThread {

	private SocketFlux socketFlux;
	private static int id_cpt = 0;
	private int id;

	public Joueur(SocketFlux s) {
		this.socketFlux = s;
		this.id = id_cpt++;
	}

	@Override
	public void run() {

	}

	public SocketFlux getSocketFlux() {
		return socketFlux;
	}
}
