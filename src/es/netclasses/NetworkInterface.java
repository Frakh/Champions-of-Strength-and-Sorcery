package es.netclasses;

import es.eventlogger.LogSys;
import es.netclasses.evenements.Evenement;
import es.netclasses.evenements.NetQueueEvenement;

import java.io.*;
import java.net.Socket;

/**
 * Classe d'interface réseau
 *
 * Reçois et mets l'evenement reçu automatiquement sur la pile de gestion d'evenets
 */
public class NetworkInterface {

	private static Socket streamSocket = null;
	private static Thread threceiver;
	private static ObjectOutputStream oos;
	private static ObjectInputStream ois;
	private static boolean should_run = true;

	public static void stop() {
		should_run = false;
	}

	public static void flush() throws IOException {
		oos.flush();
	}

	public static boolean bind(String addr, int port) {
		try {
			streamSocket = new Socket(addr, port);

			// Maybe Buggy
			oos = new ObjectOutputStream(new BufferedOutputStream(streamSocket.getOutputStream()));
			ois = new ObjectInputStream(new BufferedInputStream(streamSocket.getInputStream()));

			threceiver = new Thread(() -> {
				// Thread reçevant les objets
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
			});

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
	}

}