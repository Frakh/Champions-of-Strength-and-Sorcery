package es.exception;

public class IllegalFriendException extends RuntimeException {

	public IllegalFriendException() {
		super();
	}

	public IllegalFriendException(String msg) {
		super(msg);
	}

	public IllegalFriendException(Throwable t) {
		super(t);
	}
}
