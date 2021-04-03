package ca.mb.rrc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * TransactionTypeTable Class
 * Provide methods to insert, update and delete row in transactiontype table
 * 
 * @author Denis Pedroso
 *
 */
public class TransactionTypeTable {
	
    private static String USER_NAME;
    private static String PASS_WORD;
    private static String CONNECTION_STR;
    
    /**
     * Constructor 
     * Gets user name, pass word and connection_str
     * @param user
     * @param pass
     * @param conn
     */
    TransactionTypeTable(String user, String pass, String conn) {
    	USER_NAME = user;
    	PASS_WORD = pass;
    	CONNECTION_STR = conn;
    }
    
    /**
     * Insert values into transactiontype table
     * @param TranTypeId
     * @param TransactionDesc
     */
    public void insert(String TranTypeId, String TransactionDesc) {
    	
    	String sql = "INSERT INTO transactiontype VALUES (?,?)";
    	
        try (Connection conn = DriverManager.getConnection(CONNECTION_STR, USER_NAME, PASS_WORD);
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

            // Transaction Type id
            stmt.setString(1, TranTypeId);
            // Transaction Type Description
            stmt.setString(2, TransactionDesc);

            int result = stmt.executeUpdate();

            // Insert successful if result==1
            if (result == 1)
                System.out.println("One row inserted with success in Table transactiontype");

        } catch (SQLException e) {
            System.err.println(e);
        } // End try
    } // End of insert
    
    /**
     * Update values in transactiontype table
     * @param TranTypeId
     * @param TransactionDesc
     */
    public void update(String TranTypeId, String TransactionDesc) {
    	
    	String sql = "UPDATE transactiontype SET TransactionDesc = ? WHERE TranTypeId = ?";
    	
		// NOTE: try-with-resources used here
		try (Connection conn = DriverManager.getConnection(CONNECTION_STR, USER_NAME, PASS_WORD);
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);)
		{
			//Type Description to update
			stmt.setString(1, TransactionDesc);
			
			// Transaction Type id to update
			stmt.setString(2, TranTypeId);

			int result = stmt.executeUpdate();

			// Update successful if result==1
			if (result == 1)
				System.out.println("One row updated with success in Table transactiontype");
			else
				System.out.println("No row updated in Table transactiontype");

		} catch (SQLException e)
		{
			System.err.println(e);
		}
    	
    } // End of update
    
    /**
     * Delete row from transactiontype table
     * @param TranTypeId
     */
    public void delete(String TranTypeId) {
    	
    	String sql = "DELETE FROM transactiontype WHERE TranTypeId = ?";
    	
		try (Connection conn = DriverManager.getConnection(CONNECTION_STR, USER_NAME, PASS_WORD);
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);)
		{

			// Transaction Type id to delete
			stmt.setString(1, TranTypeId);

			int result = stmt.executeUpdate();

			// Delete successful if result==1
			if (result == 1)
				System.out.println("One row deleted with success in Table transactiontype");
			else
				System.out.println("No row deleted in Table transactiontype");

		} catch (SQLException e)
		{
			System.err.println(e);
		}
    	
    } //End of delete
    
    /**
     * 
     * @return ArrayList<String>
     */
    public ArrayList<String> tranTypes () {
    	
    	String sql = "SELECT * FROM transactiontype";
    	
    	ArrayList<String> tranTypesList  = new ArrayList<String>();
    	
		try (Connection conn = DriverManager.getConnection(CONNECTION_STR, USER_NAME, PASS_WORD);
				PreparedStatement stmt = conn.prepareStatement(sql);)
		{

			ResultSet result = stmt.executeQuery();

            while (result.next()) {

                String id = result.getString("TranTypeId");
                
                tranTypesList.add(id);
            }

		} catch (SQLException e)
		{
			System.err.println(e);
		}

		return tranTypesList;
    }// End of tranTypes
} // End of class
