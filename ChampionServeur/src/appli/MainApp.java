package appli;

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

		System.out.println("Ready to receive player");
		while (true) {
			new NewPlayerService(new SocketFlux(waiter.accept())).start();
		}
	}

}
