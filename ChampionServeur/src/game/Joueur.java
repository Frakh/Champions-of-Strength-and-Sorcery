package game;

import es.netclasses.Evenement;
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

	public SocketFlux getSocketFlux() {
		return socketFlux;
	}

	public void sendEvenement(final Evenement evenement) {
		socketFlux.writeEvenement(evenement);
	}

	public Evenement readEvenement() {
		return queue.poll();
	}

	@Override
	public void run() {
		while (true) {
			queue.add(socketFlux.readEvenement());
		}
	}

	@Override
	public boolean equals(Object o) {
		return this.id==((Joueur)o).id;
	}
}
