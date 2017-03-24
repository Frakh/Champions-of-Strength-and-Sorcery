package es.exception;

/**
 * Created on 24/03/2017.
 */
public class FrameManagerInitializedException extends RuntimeException {

	public FrameManagerInitializedException() {
		super();
	}

	public FrameManagerInitializedException(final String s) {
		super(s);
	}

	public FrameManagerInitializedException(Throwable t) {
		super(t);
	}
}
