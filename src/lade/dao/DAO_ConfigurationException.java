package lade.dao;

public class DAO_ConfigurationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DAO_ConfigurationException(String message) {
		
		super(message);
	}

	public DAO_ConfigurationException(String message, Throwable cause) {
		
		super(message, cause);
	}

	public DAO_ConfigurationException(Throwable cause) {
		
		super(cause);
	}
}
