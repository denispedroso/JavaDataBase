package ca.mb.rrc;

/**
 * AccountException class
 * 
 * Throws error related to Account
 * 
 * @author Denis Pedroso
 *
 */
public class AccountException extends Throwable {
	private static final long serialVersionUID = 4188758768752001536L;
	private Integer _errorCode;
	private String _message;
	
	/**
	 * AccountException Constructor
	 * @param errorCode
	 * @param message
	 */
	AccountException(Integer errorCode, String message){
		super();
		_errorCode = errorCode;
		_message = message;
	} // End of AccountException
	
	/**
	 * toString method
	 * @return message
	 */
    public String toString() {
    	
    	String message = _message + " Error Code: " + _errorCode;
    	
    	return message;
    } // End of toString()
    
    /**
     * getErrorCode method
     * @return _errorCode
     */
    public Integer getErrorCode() {
    	return _errorCode;
    }
    
    /** 
     * getMessage method
     * @return _message
     */
    public String getMessage() {
    	return _message;
    }
}
