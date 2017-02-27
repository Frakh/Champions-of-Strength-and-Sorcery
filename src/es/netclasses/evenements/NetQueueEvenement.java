package es.netclasses.evenements;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Classe de répartition des évenements
 *
 * Cette classe va permettre de répartire les évenements entre les différentes classes
 */
public class NetQueueEvenement {

	// Nombre max de queues
	public static final int MAX_QUEUE_ARRAY_SIZE = Byte.MAX_VALUE;

	// Tableau de queues
	private static Queue[] arrayQueue = new ConcurrentLinkedQueue[MAX_QUEUE_ARRAY_SIZE];

	/**
	 * Methode permettant d'ajouter un évenement
	 * @param e
	 */
	public static void addEvenement(Evenement e) {
		if (arrayQueue[e.getId()] == null) {
			arrayQueue[e.getId()] = new ConcurrentLinkedQueue();
		}
		// PUTAIN DE GENERIQUES !!!
		arrayQueue[e.getId()].add(e);
	}

	/**
	 * Methode permettant d'obtenir un tableau des évenements selon l'id passé
	 * @param id : l'identifiant de l'évenement
	 * @return Le tableau de la queue
	 * @throws NullPointerException : en cas de queue inexistante
	 *
	 */
	public static Evenement[] getEvents(int id) throws NullPointerException {
		Evenement[] evenements = new Evenement[arrayQueue[id].size()];
		arrayQueue[id].toArray(evenements);
		arrayQueue[id].clear();
		return evenements;
	}
}
