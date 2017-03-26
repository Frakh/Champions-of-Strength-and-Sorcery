package es.netclasses.evenements.eventimpl;

import es.netclasses.evenements.Evenement;

public class JeuEvenement extends Evenement{

	//Detail a fournir : l'id de la partie
	public static final byte JOIN_GAME = 0;
	//Detail a fournir : le nom de la carte
	public static final byte CREATE_GAME = 1;
	//Detail a fournir = aucun / Liste des jeux
	public static final byte GAME_LIST = 2;

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
