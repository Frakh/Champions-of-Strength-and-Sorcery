package es.exception;

public class MediaNonTrouveException extends RuntimeException{

	public MediaNonTrouveException() {
		super();
	}

	public MediaNonTrouveException(String s) {
		super(s);
	}

	public MediaNonTrouveException(Throwable t) {
		super(t);
	}

}
