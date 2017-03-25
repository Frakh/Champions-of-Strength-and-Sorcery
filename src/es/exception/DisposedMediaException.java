package es.exception;

public class DisposedMediaException extends RuntimeException {

	public DisposedMediaException() {
		super();
	}

	public DisposedMediaException(String str) {
		super(str);
	}

	public DisposedMediaException(Throwable t) {
		super(t);
	}

}
