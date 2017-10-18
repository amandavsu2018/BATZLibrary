package batzlibrary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class SQL {
	public ResultSet SQLConnMain(String query) {
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
			
			//String query is passed in from other classes
			
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
		return result;
	}

/*
	public ResultSet SQLConnCreateUser(String query) {
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
			
			//String query is passed in from other classes
			
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
		return result;
	}
*/
}
