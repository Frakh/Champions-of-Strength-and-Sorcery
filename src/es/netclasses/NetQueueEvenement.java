package es.netclasses;

import es.eventlogger.LogSys;
import es.netclasses.evenements.eventimpl.ServerStopEvenement;

import java.io.IOException;
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
	public static void addEvenement(Evenement e) {
		if (arrayQueue[e.getId()] == null) {
			arrayQueue[e.getId()] = new ConcurrentLinkedQueue();
		}
		/*
		 * // PUTAIN DE GENERIQUES !!!
		 * Ce truc génère un warning compilateur, mais la, faut pas se foutre de la gueule du monde
		 * Java est même pas foutu d'implementer les générics correctement, tellement que personne ne
		 * peut instancier un tableau de types génériques, quand bien même le type erasure est censé
		 * donner le même putain de résultat a la compilation
		 *
		 * ET LE COMPILO ME DIT DE FAIRE GAFFE ICI , PARCE QUE "ta utilizé dé ro taipss" ???
		 */
		
		//Ce message a ete sponsorise par La Baleine.
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
		if (arrayQueue[id]!=null)
			return null;
		synchronized (arrayQueue[id]) {
			Evenement[] evenements = new Evenement[arrayQueue[id].size()];
			arrayQueue[id].toArray(evenements);
			arrayQueue[id].clear();
			return evenements;
		}
	}

	/**
	 * Methode permettant de vider le contenu de la queue désigné
	 * @param id : identifiant de la queue d'évenement
	 * @return -1 en cas de queue nulle, le nombre d'évenements supprimés sinon
	 */
	public static int clearQueue(int id) {
		Queue clq = arrayQueue[id];
		if (clq==null)
			return -1;
		int s = clq.size();
		clq.clear();
		return s;
	}

	/**
	 * Methode permettant de switcher le blocage d'evenement d'un certain id entre le client et le serveur
	 * @param id_event_to_block : id de l'evenement a bloquer
	 * @return true si l'envoie de la donné a réussie, false sinon
	 */
	public static boolean switchEventBlock(int id_event_to_block) {
		Evenement e = new ServerStopEvenement(id_event_to_block);
		try {
			NetworkInterface.send(e);
			return true;
		} catch (IOException e1) {
			LogSys.log(e1);
			return false;
		}
	}
}
