package game;

import utilitaire.BaseThread;

import java.util.Vector;

public class Partie extends BaseThread {

	public static Vector<Partie> partieVector = new Vector<>();

	public Partie(String map) {
		partieVector.add(this);
	}

	@Override
	public void run() {

	}
}
