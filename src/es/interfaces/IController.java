package es.interfaces;

/**
 * Principe de l'interface :
 *
 * Interface visant à controler des objets, via un set de commandes paramétrés
 * Doit être implémenté par des controlleurs d'objets
 */
public interface IController {

	/**
	 * Ajoute une commande de controle
	 * @param keyCode le code de la touche
	 * @param actionCode code d'action
	 */
	void setActionMap(int actionCode, int keyCode);
	/**
	 * Donne si oui ou non le code d'action actionCode est donné
	 * @param actionCode code d'action
	 * @return true si déclenché
	 */
	boolean isActionned(int actionCode);

	boolean isActionned(int ... actionCode);

	boolean isJustPress(int actionCode);

}
