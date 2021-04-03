package ca.mb.rrc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * TransactionTable class
 * 
 * Provide methods to insert, update and delete row in transaction table
 * 
 * @author Denis Buhrer Pedroso
 *
 */
public class TransactionTable {
	
    private static String USER_NAME;
    private static String PASS_WORD;
    private static String CONNECTION_STR;
    
    private final ArrayList<String> tranTypes;
    
    /**
     * Constructor
     * 
     * Create instance of TransactionTypeTable and get all types of transactions
     * 
     * @param user
     * @param pass
     * @param conn
     */
    TransactionTable(String user, String pass, String conn) {
    	
    	USER_NAME = user;
    	PASS_WORD = pass;
    	CONNECTION_STR = conn;
    	
    	TransactionTypeTable tranTypeTable = new TransactionTypeTable(user, pass, conn);
    	
    	tranTypes = tranTypeTable.tranTypes();
    	
    }
    	
    /**
     *  Validate TransactionType is validated before inserting into Transaction table
     *  
     * @param TranTypeId
     * @throws TransactionTypeException
     */
    public void isTranTypeValid(String TranTypeId) throws TransactionTypeException {
    	
		if (tranTypes.contains(TranTypeId) == false) {
			throw new TransactionTypeException(100, "Provided Transaction Type is invalid.");
		} 
		
    }
    
    /**
     * Insert values into transaction table
     * 
     * @param TransId
     * @param TranTypeId
     * @param AcctIdFrom
     * @param AcctIdTo
     * @param Amount
     */
    public void insert(Integer TransId, String TranTypeId,  Integer AcctIdFrom, Integer AcctIdTo, Integer Amount) {
    	
    	String sql = "INSERT INTO transaction VALUES (?,?,?,?,?)";
    	
        try (Connection conn = DriverManager.getConnection(CONNECTION_STR, USER_NAME, PASS_WORD);
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

        	// Validate TransactionType  provided is valid
        	this.isTranTypeValid(TranTypeId);
        	
        	// Validate if Account From exists
        	if (AccountTable.acctExists(AcctIdFrom) == false) {
        		throw new AccountException(200, "Provided Account From does not exists.");
        	}
        	
        	// Validate if Account To exists
        	if (AccountTable.acctExists(AcctIdTo) == false) {
        		throw new AccountException(200, "Provided Account To does not exists.");
        	}
        	
            // Transaction id
            stmt.setInt(1, TransId);
            
            // Transaction Type
            stmt.setString(2, TranTypeId);
            
            // Account Id From
            stmt.setInt(3, AcctIdFrom);
            
            // Account Id To
            stmt.setInt(4, AcctIdTo);
            
            // Amount
            stmt.setInt(5, Amount);

            int result = stmt.executeUpdate();

            // Insert successful if result==1
            if (result == 1)
                System.out.println("One row inserted with success in table transaction");

        } catch (SQLException e) {
            System.err.println(e);
        } catch (TransactionTypeException e) {
        	System.err.println(e.toString());
		}  catch (AccountException e) {
        	System.err.println(e.toString());
		} // End try
    } // End of insert
    
    /**
     * Update values in transaction table
     * @param TransId
     * @param TranTypeId
     * @param AcctIdFrom
     * @param AcctIdTo
     * @param Amount
     */
    public void update(Integer TransId, String TranTypeId,  Integer AcctIdFrom, Integer AcctIdTo, Integer Amount) {
    	
    	String sql = "UPDATE transaction SET TranTypeId = ?, AcctIdFrom = ?, AcctIdTo = ?, Amount = ? WHERE TransId = ?";
    	
		// NOTE: try-with-resources used here
		try (Connection conn = DriverManager.getConnection(CONNECTION_STR, USER_NAME, PASS_WORD);
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);)
		{
			
        	// Validate TransactionType  provided is valid
        	this.isTranTypeValid(TranTypeId);
        	
        	// Validate if Account From exists
        	if (AccountTable.acctExists(AcctIdFrom) == false) {
        		throw new AccountException(200, "Provided Account From does not exists.");
        	}
        	
        	// Validate if Account To exists
        	if (AccountTable.acctExists(AcctIdTo) == false) {
        		throw new AccountException(200, "Provided Account To does not exists.");
        	}
        	            
            // Transaction Type to update
            stmt.setString(1, TranTypeId);
            
            // Account Id From to update
            stmt.setInt(2, AcctIdFrom);
            
            // Account Id To to update
            stmt.setInt(3, AcctIdTo);
            
            // Amount to update
            stmt.setInt(4, Amount);
            
            // Transaction id to Update
            stmt.setInt(5, TransId);

			int result = stmt.executeUpdate();

			// Update successful if result==1
			if (result == 1)
				System.out.println("One row updated with success in table transaction");
			else
				System.out.println("No row updated in table transaction");

        } catch (SQLException e) {
            System.err.println(e);
        } catch (TransactionTypeException e) {
        	System.err.println(e.toString());
		}  catch (AccountException e) {
        	System.err.println(e.toString());
		} // End try
    	
    } // End of update
    
    /**
     * Delete row from transaction table
     * 
     * @param TransId
     */
    public void delete(Integer TransId) {
    	
    	String sql = "DELETE FROM transaction WHERE TransId = ?";
    	
		try (Connection conn = DriverManager.getConnection(CONNECTION_STR, USER_NAME, PASS_WORD);
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);)
		{

			// Transaction id to delete
			stmt.setInt(1, TransId);

			int result = stmt.executeUpdate();

			// Delete successful if result==1
			if (result == 1)
				System.out.println("One row deleted with success in table transaction");
			else
				System.out.println("No row deleted in table transaction");

		} catch (SQLException e)
		{
			System.err.println(e);
		}
    	
    } //End of delete
}// End of class
