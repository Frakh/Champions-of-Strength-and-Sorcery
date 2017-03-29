package es.entree;

import java.util.HashMap;
import java.util.Map;

import static java.awt.event.KeyEvent.*;

public class CaptureKeyboard implements Runnable{

	public CaptureKeyboard() {
		EcouteurClavier.init();
	}

	public String currentString = "";

	private static Map<Integer, Character> keyMapping;

	public Thread launchInNewThread() {
		Thread t = new Thread(this);
		t.start();
		return t;
	}

	static {
		keyMapping = new HashMap<>(50, 1.F);
		keyMapping.put(VK_A, 'A');
		keyMapping.put(VK_B, 'B');
		keyMapping.put(VK_C, 'C');
		keyMapping.put(VK_D, 'D');
		keyMapping.put(VK_E, 'E');
		keyMapping.put(VK_F, 'F');
		keyMapping.put(VK_G, 'G');
		keyMapping.put(VK_H, 'H');
		keyMapping.put(VK_I, 'I');
		keyMapping.put(VK_J, 'J');
		keyMapping.put(VK_K, 'K');
		keyMapping.put(VK_L, 'L');
		keyMapping.put(VK_M, 'M');
		keyMapping.put(VK_N, 'N');
		keyMapping.put(VK_O, 'O');
		keyMapping.put(VK_P, 'P');
		keyMapping.put(VK_Q, 'Q');
		keyMapping.put(VK_R, 'R');
		keyMapping.put(VK_S, 'S');
		keyMapping.put(VK_T, 'T');
		keyMapping.put(VK_U, 'U');
		keyMapping.put(VK_V, 'V');
		keyMapping.put(VK_W, 'W');
		keyMapping.put(VK_X, 'X');
		keyMapping.put(VK_Y, 'Y');
		keyMapping.put(VK_Z, 'Z');
		keyMapping.put(VK_0, '0');
		keyMapping.put(VK_1, '1');
		keyMapping.put(VK_2, '2');
		keyMapping.put(VK_3, '3');
		keyMapping.put(VK_4, '4');
		keyMapping.put(VK_5, '5');
		keyMapping.put(VK_6, '6');
		keyMapping.put(VK_7, '7');
		keyMapping.put(VK_8, '8');
		keyMapping.put(VK_9, '9');
		keyMapping.put(VK_SEMICOLON, '.');
		keyMapping.put(VK_ENTER, '\n');
		keyMapping.put(VK_SPACE, ' ');
		keyMapping.put(VK_BACK_SPACE, '\b');
	}


	@Override
	public void run() {
		char nextChar = ' ';
		StringBuilder stbd = new StringBuilder();

		while (nextChar!='\n') {
			try {
				char currentChar = keyMapping.get(EcouteurClavier.getLastKey());
				if (nextChar != currentChar) {
					nextChar = currentChar;
					if (nextChar == '\b') {
						stbd.deleteCharAt(stbd.length() - 1);
					} else {
						stbd.append(nextChar);
					}
					this.currentString = stbd.toString();
				}
			}catch (Exception e) {}
		}
	}

	public String toString() {
		return this.currentString;
	}
}
