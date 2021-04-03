package ca.mb.rrc;

/**
 * AccountTypeException class
 * 
 * Throws error related to AccountType
 * 
 * @author Denis Pedroso
 *
 */
public class AccountTypeException extends Throwable {
	private static final long serialVersionUID = -3886999145594115321L;
	private Integer _errorCode;
	private String _message;
	
	/**
	 * AccountTypeException Constructor
	 * @param errorCode
	 * @param message
	 */
	AccountTypeException(Integer errorCode, String message){
		super();
		_errorCode = errorCode;
		_message = message;
	} // End of AccountTypeException
	
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

} // End of class
