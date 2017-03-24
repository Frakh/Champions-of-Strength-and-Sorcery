package es.interfaces;

public interface IController {

	int NUMBER_MAX_OF_KEY_BINDING = 256;

	/**
	 * Permet de mapper un code d'action avec un code de touche
	 * @param actionCode : le code d'action
	 * @param keyCode : le code de touche
	 */
	void setActionMap(int actionCode, int keyCode);

	/**
	 * Permet de savoir si un code d'action est effectivement déclenché
	 * @param actionCode : le code d'action
	 * @return : true si appuyé, false sinon
	 */
	boolean isActionned(int actionCode);

	/**
	 * Permet de savoir si tous les code d'actions en paramètres son déclenchés
	 * @param actionCode : les codes d'actions
	 * @return true si tous les codes d'actions sont appuyés
	 */
	boolean isActionned(int ... actionCode);

	/**
	 * permet de savoir si un code d'action est en transition "déclenché->non déclenché"
	 * @param actionCode : le code déclenché
	 * @return : true si le code d'action est en transition, false sinon
	 */
	boolean isJustPress(int actionCode);

}
