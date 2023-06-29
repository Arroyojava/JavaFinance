/* Author: Jordan Arroyo
 * Date: 2/13/22
 * Filename: DBConnection.java 
 */

package ExpenseGui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	private static Connection con = null;

	private DbConnection() {

	}

	static {
		String mySqlUrl = "jdbc:mysql://localhost:3306/expense_schema";
		String user = "root";
		String pass = "";

		try {
			con = DriverManager.getConnection(mySqlUrl, user, pass);
		} catch (SQLException e) {
			System.out.println("************************");
			System.out.println("Connection failed using credentials of " + user + "/" + pass);
			System.out.println("************************");
			e.printStackTrace();
		}
	}

	/*
	 * Call this method to get a connection to the database. It is static so you
	 * need to use the class name: DbConnection.getConnection();
	 */
	public static Connection getConnection() {
		return con;
	}
}
