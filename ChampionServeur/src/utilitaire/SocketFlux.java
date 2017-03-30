package utilitaire;

import es.netclasses.Evenement;
import es.netclasses.evenements.NetSystemMessageEvenement;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketFlux {

	private Socket socket;
	private ObjectInputStream oistream;
	private ObjectOutputStream oostream;

	public SocketFlux(Socket socket) throws IOException {
		this.socket = socket;
		this.oostream = new ObjectOutputStream(socket.getOutputStream());
		this.oistream = new ObjectInputStream(socket.getInputStream());
	}

	public void writeEvenement(Evenement evenement) {
		try {
			oostream.writeObject(evenement);
			oostream.flush();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public Evenement readEvenement() {
		Evenement ev = null;
		try {
			ev = (Evenement) oistream.readObject();
		} catch (IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			System.out.println("Unfound class");
			writeEvenement(new NetSystemMessageEvenement(NetSystemMessageEvenement.UNFOUND_CLASS, e.getMessage()));
		}
		return ev;
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		try {
			oistream.close();
			oostream.close();
			socket.close();
		} catch (IOException e) {
			System.out.println("IOException catched in socket flux : " + e.getMessage());
		}
	}
}
