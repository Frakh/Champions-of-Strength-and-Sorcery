package es.interfaces;

import utilitaire.IPosition;

public interface IControllable {

	/**
	 * Obtention du controller de l'objet controllable
	 * @return le controller
	 */
	IController getController();

	/**
	 * Obtention de la position du controllable
	 * @return la position
	 */
	IPosition getPosition();

}
