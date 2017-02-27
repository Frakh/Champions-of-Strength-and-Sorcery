package es.netclasses.evenements;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class NetQueueEvenement {

	public static final int MAP_EVENT = 0;
	public static final int PLAYER_EVENT = 1;
	public static final int GAME_EVENT = 2;

	public static final int MAX_QUEUE_ARRAY_SIZE = 256;

	private static Queue[] arrayQueue = new ConcurrentLinkedQueue[MAX_QUEUE_ARRAY_SIZE];

	public static void addEvenement(Evenement e) {
		if (arrayQueue[e.getId()] == null) {
			arrayQueue[e.getId()] = new ConcurrentLinkedQueue();
		}
		// PUTAIN DE GENERIQUES !!!
		arrayQueue[e.getId()].add(e);
	}

	public static Object[] getEvents(int id) {
		Evenement[] evenements = new Evenement[arrayQueue[id].size()];
		arrayQueue[id].toArray(evenements);
		return evenements;
	}
}
