package es.netclasses;

import es.eventlogger.LogSys;

import java.io.IOException;
import java.io.ObjectInputStream;

public class Receiver implements Runnable {

	// Boolean pour savoir si oui ou non le thread doit continuer a tourner
	public boolean should_run;

	// L'objectinputstream dans lequel sera reçu les evenements
	private ObjectInputStream ois;

	// Le thread dans lequel cet objet est en train de tourner
	private final Thread t;

	public Receiver(ObjectInputStream ois) {
		this.ois = ois;
		should_run = false;
		t = new Thread(this);
	}

	/**
	 * Methode d'obtetion du thread dans lequel tourne le receiver
	 * @return le thread dans lequel le receiver tourne
	 */
	public Thread getReceiverThread() {
		return t;
	}

	/**
	 * Demarre le receiver dans lequel le thread tourne
	 */
	public void start() {
		if (should_run)
			throw new IllegalStateException("Thread already started");
		t.start();
		should_run = true;
	}

	//Lire javadoc dans la classe java.lang.Runnable, à la methode run()
	@Override
	public void run() {
		while (should_run) {
			try {
				Evenement rObj = (Evenement) ois.readObject();
				NetQueueEvenement.addEvenement(rObj);

			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} catch (ClassNotFoundException | ClassCastException e) {
				LogSys.log(e);
			}
		}
	}
}
