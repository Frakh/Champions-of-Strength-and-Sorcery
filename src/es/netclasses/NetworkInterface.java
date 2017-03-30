package es.netclasses;

import es.eventlogger.LogSys;

import java.io.*;
import java.net.Socket;

/**
 * Classe d'interface réseau
 *
 * Reçois et mets l'evenement reçu automatiquement sur la pile de gestion d'events
 */
public class NetworkInterface {

	private static Socket streamSocket = null;
	private static Receiver threceiver;
	private static ObjectOutputStream oos;
	private static ObjectInputStream ois;
	private static boolean should_run = true;

	public static void stop() {
		should_run = false;
	}

	public static void flush() throws IOException {
		oos.flush();
	}

	/**
	 * Permet de bind la socket au port et d'ouvrir les stream
	 * @param addr : l'adresse
	 * @param port : le port du serveur
	 * @return un boolean, si ça c'est bien passé, true, sinon false
	 */
	public static boolean bind(String addr, int port) {
		try {
			streamSocket = new Socket(addr, port);

			// Maybe Buggy
			oos = new ObjectOutputStream(streamSocket.getOutputStream());
			ois = new ObjectInputStream(streamSocket.getInputStream());

			threceiver = new Receiver(ois);
			threceiver.start();

		} catch (IOException e) {
			LogSys.log(e);
			return false;
		}
		return true;
	}

	/**
	 * Methode envoyant un objet sur le réseau, au serveur
	 * @param e : l'evenement
	 * @throws IOException : en cas de problemes
	 */
	public static void send(Evenement e) throws IOException {
		oos.writeObject(e);
		oos.flush();
	}

}