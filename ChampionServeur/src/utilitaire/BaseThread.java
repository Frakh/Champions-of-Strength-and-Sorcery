package utilitaire;

public abstract class BaseThread implements Runnable {

	protected Thread thread;

	public Thread start() {
		if (thread==null) {
			thread = new Thread(this);
			thread.start();
		}
		return thread;
	}

	public Thread getThread() {
		return thread;
	}
}
