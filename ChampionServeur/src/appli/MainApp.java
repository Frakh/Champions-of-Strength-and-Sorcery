package appli;

import game.Joueur;
import game.Partie;
import utilitaire.CommandReader;
import utilitaire.SocketFlux;

import java.io.IOException;
import java.net.ServerSocket;

public class MainApp {

	public static final int CONNECT_PORT = 9001;

	public static void main(String[] args) throws IOException {

		System.out.println("Server : Champion of strengh and sorcery");

		new CommandReader().start();
		ServerSocket waiter = new ServerSocket(CONNECT_PORT);

		boolean b = true;

		System.out.println("Ready to receive player");
		while (true) {
			try {
				Joueur j = new Joueur(new SocketFlux(waiter.accept()));
				if (b) {
					Partie.creerPartie(j, "");
					b = false;
				} else {
					Partie.rejoindre(0, j);
					b = false;
				}
			} catch (Exception e) {
			}
		}
	}

}
