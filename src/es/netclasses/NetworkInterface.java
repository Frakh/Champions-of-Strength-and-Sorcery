package es.netclasses;

import es.eventlogger.LogSys;
import es.netclasses.evenements.Evenement;
import es.netclasses.evenements.NetQueueEvenement;

import java.io.*;
import java.net.Socket;

public class NetworkInterface {

	private static Socket streamSocket = null;
	private static Thread threceiver;
	private static ObjectOutputStream oos;
	private static ObjectInputStream ois;

	public static boolean bind(String addr, int port) {
		try {
			streamSocket = new Socket(addr, port);

			// Maybe Buggy
			oos = new ObjectOutputStream(new BufferedOutputStream(streamSocket.getOutputStream()));
			ois = new ObjectInputStream(new BufferedInputStream(streamSocket.getInputStream()));

			threceiver = new Thread(() -> {

				while (true) {
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

	public static void send(Evenement e) throws IOException {
		oos.writeObject(e);
	}

}