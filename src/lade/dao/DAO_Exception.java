package lade.dao;

public class DAO_Exception extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DAO_Exception(String message) {
		
		super(message);
	}

	public DAO_Exception(String message, Throwable cause) {
		
		super(message, cause);
	}

	public DAO_Exception(Throwable cause) {
		
		super(cause);
	}
}
