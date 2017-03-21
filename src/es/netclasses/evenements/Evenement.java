package es.netclasses.evenements;

import java.io.Serializable;

/**
 * Classe d'évenements.
 * Classe qui sera envoyé au serveur qui traitera les evenements
 * Les sous classes permetteront de modifier l'état du jeu
 *
 * Les sous classes utiliseront leurs propres données qui sera exploité par les classes correspondantes
 *
 * Le package ES est commun avec le server
 */
public class Evenement implements Serializable {

	/**
	 * Unique field de la classe Evenement
	 * Une valeur négative signifie que l'évenement ne doit pas être routé
	 */
	public final byte eventId;

	// Default CTOR
	public Evenement(byte eventId) {
		this.eventId = eventId;
	}

	// Retourne l'id en valeur absolu
	public int getId() {
		return eventId*(eventId<0?-1:1);
	}

	// Methode principalement utilisé par le serveur pour savoir si il dispatch cet evenement
	public boolean isDispachtable() {
		return eventId<0;
	}




	public static byte NETWORK_RESERVED_ID = 0x00;
}
