package es.exception;

/**
 * Created on 24/03/2017.
 */
public class UnititializedEcouteurClavierException extends RuntimeException {

	public UnititializedEcouteurClavierException() {
		super();
	}

	public UnititializedEcouteurClavierException(String s) {
		super(s);
	}

	public UnititializedEcouteurClavierException(Throwable t) {
		super(t);
	}

}
