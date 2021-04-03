package ca.mb.rrc;

import java.sql.SQLException;

/**
 *  TransactionsTest Class
 *  Tests if connection with Database works
 *  Creates instance of TransactionTypeTable, AccountTypeTable, AccountTable, TransactionTable
 *  Inserts, updates and deletes in each one of the above tables
 * @author Denis Buhrer Pedroso
 *
 */
public class TransactionsTest {
	
    private static final String USER_NAME = "root";
    private static final String PASS_WORD = "admin";
    private static final String CONNECTION_STR = "jdbc:mysql://localhost/db1";

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Test if connection works
		try {
			ConnectionTest.test();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		new Thread(() -> {
			// Create new instance of TransactionTypeTable
			TransactionTypeTable transactionTypeTable = new TransactionTypeTable(USER_NAME, PASS_WORD, CONNECTION_STR);
			
			// Insert new row into Transaction Type Table
			transactionTypeTable.insert("D", "Test");
			
			// Update row in Transaction Type Table
			transactionTypeTable.update("D", "Test Updated");
			
			// Delete row from Transaction Type Table
			transactionTypeTable.delete("D");
		}).start();

		new Thread(() -> {
			// Create new instance of AccountTypeTable
			AccountTypeTable accTypeTable = new AccountTypeTable(USER_NAME, PASS_WORD, CONNECTION_STR);
			
			// Insert new row into Account Type Table
			accTypeTable.insert(40, "Account Desc Test");
			
			// Update row in Account Type Table
			accTypeTable.update(40, "Acct Desc Updated");
			
			// Delete row from Account Type Table
			accTypeTable.delete(40);
		}).start();

		new Thread(() -> {
			// Create new instance of AccountTable
			AccountTable acctTable = new AccountTable(USER_NAME, PASS_WORD, CONNECTION_STR);
			
			// Insert new row into Account Table
			acctTable.insert(500, 10, 4000);
		
			// Update row in Account Table
			acctTable.update(500, 20, 4500);
			
			// Delete row from Account Table
			acctTable.delete(500);
		}).start();

		
		new Thread(() -> {
			// Create new instance of TransactionTable
			TransactionTable tranTable = new TransactionTable(USER_NAME, PASS_WORD, CONNECTION_STR);
			
			// Insert new row into Transaction Table
			tranTable.insert(4, "C", 100, 200, 25);
			
			// Update row in Transaction Table
			tranTable.update(4, "P", 100, 200, 50);
			
			// Delete row from Transaction Table
			tranTable.delete(4);
		}).start();

	} // End of main()

}// End of class
