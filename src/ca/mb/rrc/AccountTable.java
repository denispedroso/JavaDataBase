package ca.mb.rrc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * AccountTable Class
 * Provide methods to insert, update and delete row in accounts table
 * 
 * @author Denis Buhrer Pedroso
 *
 */
public class AccountTable {

    private static String USER_NAME;
    private static String PASS_WORD;
    private static String CONNECTION_STR;
    private final ArrayList<Integer> acctTypes;
    
    /**
     *  Constructor
     *  
     *  Create instance of AccountTypeTable
     *  get all Account Types from AccountType Table
     *  
     * @param user
     * @param pass
     * @param conn
     */
    AccountTable(String user, String pass, String conn) {
    	
    	USER_NAME = user;
    	PASS_WORD = pass;
    	CONNECTION_STR = conn;
    	
    	AccountTypeTable accTypeTable = new AccountTypeTable(user, pass, conn);
    	
    	acctTypes = accTypeTable.acctTypes();
    	
    }
    
    /**
     * Validate Account Type is validated before inserting into Account  table
     * @param AcctTypeId
     * @throws AccountTypeException
     */
    public void isAcctTypeValid(Integer AcctTypeId) throws AccountTypeException {
    	
		if (acctTypes.contains(AcctTypeId) == false) {
			throw new AccountTypeException(100, "Provided Account Type is invalid.");
		} 
		
    }
    
    /**
     *  Insert values into Account Table
     * @param AcctId
     * @param AcctTypeId
     * @param Balance
     */
    public void insert(Integer AcctId, Integer AcctTypeId, Integer Balance) {
    	
    	String sql = "INSERT INTO accounts VALUES (?,?,?)";
    	
        try (Connection conn = DriverManager.getConnection(CONNECTION_STR, USER_NAME, PASS_WORD);
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

        	// Validate Account Type is valid
        	this.isAcctTypeValid(AcctTypeId);
        	
            // Account id
            stmt.setInt(1, AcctId);
            
            // Account Type
            stmt.setInt(2, AcctTypeId);
            
            // Account Amount
            stmt.setInt(3, Balance);

            int result = stmt.executeUpdate();

            // Insert successful if result==1
            if (result == 1)
                System.out.println("One row inserted with success in table accounts");

        } catch (SQLException e) {
            System.err.println(e);
        } catch (AccountTypeException e) {
        	System.err.println(e.toString());
		} // End try
    } // End of insert
    
    /**
     * Update values into account table
     * @param AcctId
     * @param AcctTypeId
     * @param Balance
     */
    public void update(Integer AcctId, Integer AcctTypeId, Integer Balance) {
    	
    	String sql = "UPDATE accounts SET AcctTypeId = ?, Balance = ? WHERE AccountId = ?";
    	
		// NOTE: try-with-resources used here
		try (Connection conn = DriverManager.getConnection(CONNECTION_STR, USER_NAME, PASS_WORD);
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);)
		{
			
        	// Validate Account Type is valid
        	this.isAcctTypeValid(AcctTypeId);
        	
			// Account Type to update
			stmt.setInt(1, AcctTypeId);
			
			// Account Balance to update
			stmt.setInt(2, Balance);
			
			// Account Id to update
			stmt.setInt(3, AcctId);

			int result = stmt.executeUpdate();

			// Update successful if result==1
			if (result == 1)
				System.out.println("One row updated with success in table accounts");
			else
				System.out.println("No row updated in table accounts");

		} catch (SQLException e) {
			System.err.println(e);
		} catch (AccountTypeException e) {
        	System.err.println(e.toString());
		} // End try
    	
    } // End of update
    
    /**
     * Delete values from account table
     * @param AccountId
     */
    public void delete(Integer AccountId) {
    	
    	String sql = "DELETE FROM accounts WHERE AccountId = ?";
    	
		try (Connection conn = DriverManager.getConnection(CONNECTION_STR, USER_NAME, PASS_WORD);
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);)
		{

			// Transaction Type id to delete
			stmt.setInt(1, AccountId);

			int result = stmt.executeUpdate();

			// Delete successful if result==1
			if (result == 1)
				System.out.println("One row deleted with success in table accounts");
			else
				System.out.println("No row deleted in table accounts");

		} catch (SQLException e)
		{
			System.err.println(e);
		}
    	
    } //End of delete
    
    /**
     * Checks if provided account Id exists and return true or false
     * @param AccountId
     * @return 
     */
    public static boolean acctExists(Integer AccountId) {
    	
    	String sql = "SELECT * FROM accounts WHERE AccountId = ?";
    	
		try (Connection conn = DriverManager.getConnection(CONNECTION_STR, USER_NAME, PASS_WORD);
				PreparedStatement stmt = conn.prepareStatement(sql);)
		{

			// Transaction Type id to delete
			stmt.setInt(1, AccountId);

			ResultSet result = stmt.executeQuery();
			
			if (result.isBeforeFirst()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e)
		{
			System.err.println(e);
		}
		return false;
    	
    } //End of delete
} // End of Class
