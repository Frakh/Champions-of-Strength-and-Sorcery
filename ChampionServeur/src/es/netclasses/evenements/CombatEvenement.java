package es.netclasses.evenements;

import es.netclasses.Evenement;

public class CombatEvenement extends Evenement{

	public final byte id_action;

	public CombatEvenement(byte id, String Mappp) {
		super(Evenement.COMBAT_EVENT);
		carteMaj=Mappp;
		id_action = id;
	}



	public static final byte DEBUT_COMBAT = 0;
	public static final byte MAJ_COMBAT = 42;

	String carteMaj="";

	public String getMaj() {
		return carteMaj;
	}
}



