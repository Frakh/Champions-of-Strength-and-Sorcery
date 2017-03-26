package appli;


import utilitaire.SocketFlux;

public class NewPlayerService implements Runnable {

	private SocketFlux sflux;
	private Thread thread;

	public NewPlayerService(SocketFlux choset) {

	}

	public Thread start() {
		thread = new Thread(this);
		thread.start();
		return thread;
	}

	@Override
	public void run() {

	}
}
