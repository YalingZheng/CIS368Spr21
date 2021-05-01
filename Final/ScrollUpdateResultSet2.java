package databaseprogramming;

import java.sql.*;

public class ScrollUpdateResultSet2 {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// Load the JDBC driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver loaded");

		// Connect to a database
		Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb", "root",
				"8zyfBU2qmt2BpEB");
		connection.setAutoCommit(true);
		System.out.println("Database connected");

		// Get a new statement for the current connection
		/*
		■■ TYPE_FORWARD_ONLY: The result set is accessed forward sequentially.
		■■ TYPE_SCROLL_INSENSITIVE: The result set is scrollable, but not sensitive to
		changes in the database.
		■■ TYPE_SCROLL_SENSITIVE: The result set is scrollable and sensitive to changes
		made by others. Use this type if you want the result set to be scrollable and updatable.
		The possible values of resultSetConcurrency are the constants defined in the
		ResultSet:
		■■ CONCUR_READ_ONLY: The result set cannot be used to update the database.
		■■ CONCUR_UPDATABLE: The result set can be used to update the database.
		*/
		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

		// Get ResultSet
		ResultSet resultSet = statement.executeQuery("select state, capital from StateCapital");

		System.out.println("\nBefore update ");
		displayResultSet(resultSet);

		// Update the second row
		resultSet.absolute(2); // Move cursor to the second row
		resultSet.updateString("state", "New S"); // Update the column
		resultSet.updateString("capital", "New C"); // Update the column
		resultSet.updateRow(); // Update the row in the data source

		// Insert after the last row
		resultSet.last();
		resultSet.moveToInsertRow(); // Move cursor to the insert row
		resultSet.updateString("state", "Florida");
		resultSet.updateString("capital", "Tallahassee");
		resultSet.insertRow(); // Insert the row
		resultSet.moveToCurrentRow(); // Move the cursor to the current inserted row

		// Delete fourth row
		resultSet.absolute(4); // Move cursor to the 5th row
		resultSet.deleteRow(); // Delete the second row

		System.out.println("\n\nAfter update ");
		resultSet = statement.executeQuery("select state, capital from StateCapital");
		displayResultSet(resultSet);

		// Close the connection
		resultSet.close();
	}

	private static void displayResultSet(ResultSet resultSet) throws SQLException {
		ResultSetMetaData rsMetaData = resultSet.getMetaData();
		resultSet.beforeFirst();
		while (resultSet.next()) {
			for (int i = 1; i <= rsMetaData.getColumnCount(); i++)
				System.out.printf("%-12s\t", resultSet.getObject(i));
			System.out.println();
		}
	}
}
