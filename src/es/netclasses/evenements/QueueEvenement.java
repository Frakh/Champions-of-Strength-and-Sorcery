package es.netclasses.evenements;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueEvenement implements Runnable {

	public static final int MAP_EVENT = 0;
	public static final int PLAYER_EVENT = 1;
	public static final int GAME_EVENT = 2;

	public static final int MAX_QUEUE_ARRAY_SIZE = 256;

	private Queue<Evenement> evenements;

	public QueueEvenement() {
		evenements = new ConcurrentLinkedQueue<>();
	}


	/**
	 * When an object implementing interface <code>Runnable</code> is used
	 * to create a thread, starting the thread causes the object's
	 * <code>run</code> method to be called in that separately executing
	 * thread.
	 * <p>
	 * The general contract of the method <code>run</code> is that it may
	 * take any action whatsoever.
	 *
	 * @see Thread#run()
	 */
	@Override
	public void run() {
		// Systeme de répartition des evenements
	}
}
