package es.exception;

public class ImageNonTrouveException  extends RuntimeException {

	public ImageNonTrouveException() {
	}

	public ImageNonTrouveException(final String s) {
		super(s);
	}

	public ImageNonTrouveException(Throwable t) {
		super(t);
	}
}
