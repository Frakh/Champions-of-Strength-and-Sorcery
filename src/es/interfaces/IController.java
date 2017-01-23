package es.interfaces;

public interface IController {

	/**
	 * Ajoute une commande de controle
	 * @param keyCode le code de la touche
	 * @param actionCode code d'action
	 */
	void setActionMap(int actionCode, int keyCode);
//mon petit, cette paix est ce pourquoi luttent tous les vrais spaghettis.
	/**
	 * Donne si oui ou non le code d'action actionné
	 * @param actionCode code d'action
	 * @return true si déclenché
	 */
	boolean isActionned(int actionCode);

	boolean isActionned(int ... actionCode);

	boolean isJustPress(int actionCode);

}
