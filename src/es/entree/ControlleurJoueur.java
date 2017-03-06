package es.entree;

import es.interfaces.IController;

public class ControlleurJoueur implements IController{

	public static final int MAX_NUMBERS_OF_BINDING = 256;

	private int[] keyMap = new int[256];

	/**
	 * Ajoute une commande de controle
	 *
	 * @param actionCode le code d'action
	 * @param keyCode    le code
	 */
	@Override
	public void setActionMap(final int actionCode, final int keyCode) {
		keyMap[actionCode] = keyCode;
	}

	/**
	 * Donne si oui ou non le code d'action actionné
	 *
	 * @param actionCode le code d'action
	 * @return true si actionné, false sinon
	 */
	@Override
	public boolean isActionned(final int actionCode) {
		return EcouteurClavier.isPressed(keyMap[actionCode]);
	}

	@Override
	public boolean isActionned(final int... actionCode) {
		for (int a: actionCode)
			if (isActionned(a))
				return true;
		return false;
	}

	@Override
	public boolean isJustPress(final int actionCode) {
		return EcouteurClavier.justPressed(keyMap[actionCode]);
	}
}
