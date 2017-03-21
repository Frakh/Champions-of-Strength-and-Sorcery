package es.entree;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class EcouteurClavier implements KeyEventDispatcher{

	private static EcouteurClavier _instance;

	private static boolean[] tabKeyOld = new boolean[256];
	private static boolean[] tabKeys = new boolean[256];

	private EcouteurClavier() {
		Arrays.fill(tabKeys, false);
		Arrays.fill(tabKeyOld, false);
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this);
	}

	public static void init() {
		if (_instance==null) {
			_instance = new EcouteurClavier();
		}
	}

	public static boolean justPressed(int keycode) {
		boolean t = tabKeyOld[keycode]!=tabKeys[keycode];
		if (t)
			System.arraycopy(tabKeys, 0, tabKeyOld, 0, 256);
		return t;
	}

	public static boolean isPressed(int keyCode) {
		return tabKeys[keyCode];
	}

	@Override
	public boolean dispatchKeyEvent(final KeyEvent e) {
		synchronized (EcouteurClavier.class) {
			System.arraycopy(tabKeys, 0, tabKeyOld, 0, 256);
			switch (e.getID()) {
				case KeyEvent.KEY_PRESSED:
					tabKeys[e.getKeyCode()] = true;
					break;
				case KeyEvent.KEY_RELEASED:
					tabKeys[e.getKeyCode()] = false;
					break;
			}
		}
		return false;
	}

}
