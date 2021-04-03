package ca.mb.rrc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {
	
	private static final String USER_NAME = "root";
	private static final String PASS_WORD = "admin";
	private static final String CONNECTION_STR = "jdbc:mysql://localhost/db1";

	public static void test () throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(CONNECTION_STR, USER_NAME, PASS_WORD);
			
			System.out.println("Successfuly connected to DB: " +conn.getCatalog());

		} catch (SQLException e) 	{
			e.printStackTrace();
		} finally {
			conn.close();
		} // End Try
	} //End method test
}// End of class

