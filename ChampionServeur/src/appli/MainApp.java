package appli;

import java.io.IOException;
import java.net.ServerSocket;

public class MainApp {

	public static final int CONNECT_PORT = 9001;

	public static void main(String[] args) throws IOException {

		System.out.println("Server : Champion of strengh and sorcery");
		System.out.println("Setting up");

		ServerSocket waiter = new ServerSocket(CONNECT_PORT);

		System.out.println("Ready to receive player");
	}

}
