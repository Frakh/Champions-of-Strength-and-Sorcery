package es.netclasses.evenements.eventimpl;

import es.netclasses.evenements.Evenement;

public class JeuEvenement extends Evenement{

	//Detail a fournir : l'id de la partie / la map a charger
	public static final byte JOIN_GAME = 0;
	//Detail a fournir : le nom de la carte
	public static final byte CREATE_GAME = 1;
	//Detail a fournir = aucun / Liste des jeux
	public static final byte GAME_LIST = 2;
	//Detail a fournir : ne doit pas être lancé par le client / rien
	public static final byte NOT_FOUND_GAME = 3;
	//Detail a fournir : rien / doit être donné par le server a tous le monde
	public static final byte RUN_GAME = 4;
	//Detail a fournir : rien ?
	public static final byte PARTIE_KILLED = 5;

	private byte messageId;
	private String detail;

	public JeuEvenement(byte messageId, String detail) {
		super(Evenement.GAME_ID);
		this.messageId = messageId;
		this.detail = detail;
	}

	public byte getMessageId() {
		return messageId;
	}

	public String getDetail() {
		return detail;
	}
}
