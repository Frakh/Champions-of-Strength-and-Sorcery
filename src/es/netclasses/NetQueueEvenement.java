package es.netclasses;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Classe de répartition des évenements
 *
 * Cette classe va permettre de répartire les évenements entre les différentes classes
 */
public class NetQueueEvenement {

	// Nombre max de queues
	public static final int MAX_QUEUE_ARRAY_SIZE = 256;

	// Tableau de queues
	private static Queue[] arrayQueue = new ConcurrentLinkedQueue[MAX_QUEUE_ARRAY_SIZE];

	/**
	 * Methode permettant d'ajouter un évenement
	 * @param e l'evenement
	 */
	public synchronized static void addEvenement(Evenement e) {
		if (arrayQueue[e.getId()] == null) {
			arrayQueue[e.getId()] = new ConcurrentLinkedQueue();
		}

		arrayQueue[e.getId()].add(e);
		System.out.println("Evenement reçu : " + e.getId());
	}

	/**
	 * Fonction permettant d'obtenir l'evenement stocké qui attends son traitement depuis le plus longtemps
	 * @param id : l'identifiant d'evenement
	 * @return : l'evenement si il y en a un, null sinon
	 */
	public synchronized static Evenement getEvenement(int id) {
		if (arrayQueue[id]==null) {
			return null;
		}
		return (Evenement) arrayQueue[id].poll();
	}

	/**
	 * Methode permettant d'obtenir un tableau des évenements selon l'id passé
	 * @param id : l'identifiant de l'évenement
	 * @return Le tableau de la queue
	 * @throws NullPointerException : en cas de queue inexistante
	 *
	 */
	public synchronized static Evenement[] getEvents(int id) throws NullPointerException {
		if (arrayQueue[id]==null)
			return null;
		synchronized (arrayQueue[id]) {
			Evenement[] evenements = new Evenement[arrayQueue[id].size()];
			arrayQueue[id].toArray(evenements);
			arrayQueue[id].clear();
			return evenements;
		}
	}
}
