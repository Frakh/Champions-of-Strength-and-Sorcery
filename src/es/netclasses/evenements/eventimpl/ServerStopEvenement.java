package es.netclasses.evenements.eventimpl;

import es.netclasses.evenements.Evenement;

/**
 * Created on 28/02/2017.
 */
public class ServerStopEvenement extends Evenement {

	public static final byte EVENEMENT_TYPE_ID = 0x00;

	private byte eventid_sw;

	public ServerStopEvenement(int event_id_to_banswitch) {
		super(EVENEMENT_TYPE_ID);
		eventid_sw = (byte) event_id_to_banswitch;
	}

	public byte getEventid_sw() {
		return eventid_sw;
	}
}
