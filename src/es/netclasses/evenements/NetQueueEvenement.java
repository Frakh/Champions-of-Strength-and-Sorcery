package es.netclasses.evenements;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class NetQueueEvenement implements Runnable {

	public static final int MAP_EVENT = 0;
	public static final int PLAYER_EVENT = 1;
	public static final int GAME_EVENT = 2;

	public static final int MAX_QUEUE_ARRAY_SIZE = 256;

	private Queue<Evenement> evenements;

	public NetQueueEvenement() {
		evenements = new ConcurrentLinkedQueue<>();
	}

	@Override
	public void run() {
		// Systeme de répartition des evenements
	}
}
