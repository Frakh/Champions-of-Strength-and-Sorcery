package es.netclasses.evenements;

public class Evenement {

	private short eventId;

	public Evenement(short eventId) {
		this.eventId = eventId;
	}

	public int getFullEventId() {
		return eventId;
	}

	public int getId() {
		return eventId*(eventId<0?-1:1);
	}

	public boolean isDispachtable() {
		return eventId<0;
	}
}
