package game;

import es.netclasses.Evenement;

public class EvenementConteneur {

	private Evenement evenement;
	private Joueur deposeur;

	public EvenementConteneur(Evenement e, Joueur dep) {
		this.evenement = e;
		this.deposeur= dep;
	}

	public Joueur getDeposeur() {
		return deposeur;
	}

	public Evenement getEvenement() {
		return evenement;
	}
}
