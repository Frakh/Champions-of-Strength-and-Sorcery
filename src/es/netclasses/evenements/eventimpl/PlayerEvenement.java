package es.netclasses.evenements.eventimpl;

import es.netclasses.evenements.Evenement;

import java.io.Serializable;

public class PlayerEvenement extends Evenement implements Serializable {

	public PlayerEvenement(final int eventId) {
		super(eventId);
	}

	public int keyPressedTab[];


}
