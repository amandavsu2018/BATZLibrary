package org.batzlibrary.sprint1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateUser {
	public Boolean checkUsername(String user, boolean bool) {
		bool = true;
		Database db = new Database();
		String database = new String(db.getDatabase());
		String databaseUser = new String(db.getDatabaseUser());
		String databasePass = new String(db.getDatabasePassword());
		ResultSet result = null;
		try {
			// Creates conn Object with database from first line of file,
			// databaseUser from 2nd,
			// and databasePass from 3rd
			Connection conn = DriverManager.getConnection(database, databaseUser, databasePass);

			// Here we set the MariaDB query variable which is the command we
			// are going to use
			// when we query the database in order to obtain information
			String query = "SELECT * FROM users WHERE user_username = '" + user + "'";

			// This is Java PreparedStatement, it creates the object
			// preparedStatement which has the
			// conn variable data and the database query variable. It will be
			// executed in order
			// to connect to the MariaDB database to get our information out
			PreparedStatement preparedStmt = conn.prepareStatement(query);

			// This ResultSet is an object that contains the executed query from
			// preparedStatement.
			// if result has stuff in it from the query (i.e. the username we
			// are checking to see
			// is already in the database) then result.first() will have stuff
			// in it and we can
			// set boolean to true. Else it's empty and we set boolean to false
			result = preparedStmt.executeQuery(query);

			// close database connection after query
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// check to see if ResultSet result has stuff in it. Return true if so,
		// and false if not.
		try {
			if (result.first()) {
				bool = true;
			} else {
				bool = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bool;
	}

	public String replaceUsername(String user) {
		boolean replace = false;
		String tempuser = user;
		int num = 1;
		String[] stringarray = new String[2];
		String temp = null;
		while (replace == false) {
			Database db = new Database();
			String database = new String(db.getDatabase());
			String databaseUser = new String(db.getDatabaseUser());
			String databasePass = new String(db.getDatabasePassword());
			ResultSet result = null;
			try {
				// Creates conn Object with database from first line of file,
				// databaseUser from 2nd,
				// and databasePass from 3rd
				Connection conn = DriverManager.getConnection(database, databaseUser, databasePass);

				// Here we set the MariaDB query variable which is the command
				// we are going to use
				// when we query the database in order to obtain information
				String query = "SELECT * FROM users WHERE user_username = '" + user + "'";

				// This is Java PreparedStatement, it creates the object
				// preparedStatement which has the
				// conn variable data and the database query variable. It will
				// be executed in order
				// to connect to the MariaDB database to get our information out
				PreparedStatement preparedStmt = conn.prepareStatement(query);

				// This ResultSet is an object that contains the executed query
				// from preparedStatement.
				// if result has stuff in it from the query (i.e. the username
				// we are checking to see
				// is already in the database) then result.first() will have
				// stuff in it and we can
				// set boolean to true. Else it's empty and we set boolean to
				// false
				result = preparedStmt.executeQuery(query);

				// close database connection after query
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			// check to see if ResultSet result has stuff in it. If it does,
			// then the user name still exists in the database. So, increment num,
			// concatenate to tempuser, and try again. If result.first() returns null,
			// then ResultSet result contains nothing and user name does not exist in
			// database. So, set replace to true and return current user name in user.
			try {
				if (result.first()) {
					stringarray[0] = tempuser;
					stringarray[1] = Integer.toString(num);
					temp = stringarray[0] + stringarray[1];
					num++;
					user = temp;
				} else {
					replace = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}
}