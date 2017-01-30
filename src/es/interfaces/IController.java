package es.interfaces;

public interface IController {

	int NUMBER_MAX_OF_KEY_BINDING = 256;

	/**
	 * Ajoute une commande de controle
	 * @param keyCode le code
	 * @param actionCode
	 */
	void setActionMap(int actionCode, int keyCode);
//mon petit, cette paix est ce pourquoi luttent tous les vrais spaghettis.
	/**
	 * Donne si oui ou non le code d'action actionné
	 * @param actionCode
	 * @return
	 */
	boolean isActionned(int actionCode);

	boolean isActionned(int ... actionCode);

	boolean isJustPress(int actionCode);

}
