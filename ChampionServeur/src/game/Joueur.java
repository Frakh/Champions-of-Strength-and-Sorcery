package game;

import es.netclasses.evenements.Evenement;
import es.netclasses.evenements.eventimpl.JeuEvenement;
import utilitaire.BaseThread;
import utilitaire.SocketFlux;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Joueur extends BaseThread {

	private SocketFlux socketFlux;
	private static int id_cpt = 0;
	private int id;

	private Queue<Evenement> queue;

	public Joueur(SocketFlux s) {
		this.socketFlux = s;
		this.id = id_cpt++;
		this.queue = new ConcurrentLinkedQueue<>();
	}

	@Override
	public void run() {
		while (super.conti_run) {
			queue.add(socketFlux.readEvenement());
		}
	}

	public Evenement[] getEvenementArray() {
		Evenement[] earr = new Evenement[queue.size()];
		queue.toArray(earr);
		return earr;
	}

	public SocketFlux getSocketFlux() {
		return socketFlux;
	}

	public void sendEvenement(final JeuEvenement jeuEvenement) {
		socketFlux.writeEvenement(jeuEvenement);
	}
}
