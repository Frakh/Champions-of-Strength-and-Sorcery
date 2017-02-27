package es.netclasses.evenements;

/**
 * Classe d'évenements.
 * Classe qui sera envoyé au serveur qui traitera les evenements
 * Les sous classes permetteront de modifier l'état du jeu
 *
 * Les sous classes utiliseront leurs propres données qui sera exploité par les classes correspondantes
 */
public class Evenement {

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
}
