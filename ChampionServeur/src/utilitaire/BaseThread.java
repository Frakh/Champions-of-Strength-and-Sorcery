package utilitaire;

public abstract class BaseThread implements Runnable {

	protected Thread thread;
	protected boolean conti_run;

	public Thread start() {
		if (thread==null) {
			conti_run = true;
			thread = new Thread(this);
			thread.start();
		}
		return thread;
	}

	public Thread getThread() {
		return thread;
	}
}
