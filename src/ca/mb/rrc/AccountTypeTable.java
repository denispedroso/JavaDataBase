package ca.mb.rrc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * AccountTypeTable
 * Provide methods to insert, update and delete row in accounttype table
 * 
 * @author Denis Buhrer Pedroso
 *
 */
public class AccountTypeTable {
	
    private static String USER_NAME;
    private static String PASS_WORD;
    private static String CONNECTION_STR;
    
    /**
     * Constructor
     * 
     * @param user
     * @param pass
     * @param conn
     */
    AccountTypeTable(String user, String pass, String conn){
    	
    	USER_NAME = user;
    	PASS_WORD = pass;
    	CONNECTION_STR = conn;
    	
    }
    
    /**
     * Insert  values into accounttype table
     * @param AccTypeId
     * @param AccTypeDesc
     */
    public void insert(Integer AccTypeId, String AccTypeDesc) {
    	
    	String sql = "INSERT INTO accounttype VALUES (?,?)";
    	
        try (Connection conn = DriverManager.getConnection(CONNECTION_STR, USER_NAME, PASS_WORD);
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

            // Account Type id
            stmt.setInt(1, AccTypeId);
            // Account Type Description
            stmt.setString(2, AccTypeDesc);

            int result = stmt.executeUpdate();

            // Insert successful if result==1
            if (result == 1)
                System.out.println("One row inserted with success in table accounttype");

        } catch (SQLException e) {
            System.err.println(e);
        } // End try
    } // End of insert
    
    /**
     * Update values in accounttype table
     * @param AccTypeId
     * @param AccTypeDesc
     */
    public void update(Integer AccTypeId, String AccTypeDesc) {
    	
    	String sql = "UPDATE accounttype SET AccountTypeDesc = ? WHERE AcctTypeId = ?";
    	
		try (Connection conn = DriverManager.getConnection(CONNECTION_STR, USER_NAME, PASS_WORD);
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);)
		{
			// Account Type Description to update
			stmt.setString(1, AccTypeDesc);
			
			// Account Type id to update
			stmt.setInt(2, AccTypeId);

			int result = stmt.executeUpdate();

			// Update successful if result==1
			if (result == 1)
				System.out.println("One row updated with success in table accounttype");
			else
				System.out.println("No row updated in table accounttype");

		} catch (SQLException e)
		{
			System.err.println(e);
		}
    	
    } // End of update
    
    /**
     * Delete row from accounttype table
     * @param AccTypeId
     */
    public void delete(Integer AccTypeId) {
    	
    	String sql = "DELETE FROM accounttype WHERE AcctTypeId = ?";
    	
		try (Connection conn = DriverManager.getConnection(CONNECTION_STR, USER_NAME, PASS_WORD);
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);)
		{

			// Transaction Type id to delete
			stmt.setInt(1, AccTypeId);

			int result = stmt.executeUpdate();

			// Delete successful if result==1
			if (result == 1)
				System.out.println("One row deleted with success in table accounttype");
			else
				System.out.println("No row deleted in table accounttype");

		} catch (SQLException e)
		{
			System.err.println(e);
		}
    	
    } //End of delete
    
    /**
     * 
     * @return ArrayList<Integer>
     */
    public ArrayList<Integer> acctTypes () {
    	
    	String sql = "SELECT * FROM accounttype";
    	
    	ArrayList<Integer> acctTypesList  = new ArrayList<Integer>();
    	
		try (Connection conn = DriverManager.getConnection(CONNECTION_STR, USER_NAME, PASS_WORD);
				PreparedStatement stmt = conn.prepareStatement(sql);)
		{

			ResultSet result = stmt.executeQuery();

            while (result.next()) {

                Integer id = result.getInt("AcctTypeId");
                
                acctTypesList.add(id);
            }

		} catch (SQLException e)
		{
			System.err.println(e);
		}

		return acctTypesList;
    } // End of acctTypes

}// End of class
