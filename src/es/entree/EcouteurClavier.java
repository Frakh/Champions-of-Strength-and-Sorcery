package es.entree;

import es.exception.UnititializedEcouteurClavierException;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class EcouteurClavier implements KeyEventDispatcher{

	private static EcouteurClavier _instance;

	public static final int TAB_SIZE = 1024;

	private static boolean[] tabKeyOld = new boolean[TAB_SIZE];
	private static boolean[] tabKeys = new boolean[TAB_SIZE];

	private static boolean canResend = true;
	private static int lastKey;

	private EcouteurClavier() {
		Arrays.fill(tabKeys, false);
		Arrays.fill(tabKeyOld, false);
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this);
	}

	/**
	 * Fonction d'initialisation de l'écouteur clavier
	 */
	public static void init() {
		if (_instance==null) {
			_instance = new EcouteurClavier();
		}
	}

	/**
	 * Methode permettant de savoir si oui ou non une touché vient d'être relaché
	 * @param keycode : le code de la touche
	 * @return : true si
	 * @throws UnititializedEcouteurClavierException si l'écouteur clavier n'est pas initialisé ( voir la fonction init() )
	 */
	public static boolean justPressed(int keycode) throws UnititializedEcouteurClavierException{
		if (_instance==null)
			throw new UnititializedEcouteurClavierException();
		boolean t = tabKeyOld[keycode]!=tabKeys[keycode];
		t = t && tabKeyOld[keycode] && !tabKeys[keycode];
		if (t)
			System.arraycopy(tabKeys, 0, tabKeyOld, 0, 256);
		return t;
	}

	/**
	 * Methode permettant de savoir si actuellement, une touche est enfoncé
	 * @param keyCode : le code de la dite touche
	 * @return : true si la touche est enfoncé, false sinon
	 * @throws UnititializedEcouteurClavierException si l'écouteur clavier n'est pas initialisé ( voir la fonction init() )
	 */
	public static boolean isPressed(int keyCode) throws UnititializedEcouteurClavierException {
		if (_instance==null)
			throw new UnititializedEcouteurClavierException("Erreur : uninitialized keyboard");
		return tabKeys[keyCode];
	}

	public static int getLastKey() {
		if (canResend) {
			canResend = false;
			return lastKey;
		}
		return 0;
	}

	/**
	 * Methode qui n'est pas censé être utilisé par l'exterieur
	 * @param e : le code de la touche
	 * @return : semble retourner false constamment
	 */
	@Override
	public boolean dispatchKeyEvent(final KeyEvent e) {
		synchronized (EcouteurClavier.class) {
			System.arraycopy(tabKeys, 0, tabKeyOld, 0, 256);
			switch (e.getID()) {
				case KeyEvent.KEY_PRESSED:
					tabKeys[e.getKeyCode()] = true;
					canResend = true;
					lastKey = e.getKeyCode();
					break;
				case KeyEvent.KEY_RELEASED:
					tabKeys[e.getKeyCode()] = false;
					canResend = true;
					lastKey = 0;
					break;
			}
		}
		return false;
	}

}
