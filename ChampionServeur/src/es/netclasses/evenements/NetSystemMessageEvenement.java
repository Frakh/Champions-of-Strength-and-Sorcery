package es.netclasses.evenements;

import es.netclasses.Evenement;

public class NetSystemMessageEvenement extends Evenement{

	public static final byte UNFOUND_CLASS = 0;

	private byte messageid;
	private String detail;

	public NetSystemMessageEvenement(byte message_id, String detail) {
		super(Evenement.NETWORK_RESERVED_ID);
		this.messageid = message_id;
		this.detail = detail;
	}

	public byte getMessageid() {
		return messageid;
	}

	public String getDetail() {
		return detail;
	}
}
