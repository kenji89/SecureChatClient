package pl.marczykm.SecureChatClient.exception;

public class TechnicalException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6869208636074118646L;

	public TechnicalException() {
		super();
	}
	
	public TechnicalException(String message){
		super(message);
	}
}
