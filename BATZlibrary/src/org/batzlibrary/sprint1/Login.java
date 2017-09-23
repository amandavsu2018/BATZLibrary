package org.batzlibrary.sprint1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

public class Login {
	Database db = new Database();


	public Boolean checkUsername(String user, boolean bool) {
		bool = true;
		Database db = new Database();
		String database = new String(db.getDatabase());
		String databaseUser = new String(db.getDatabaseUser());
		String databasePass = new String(db.getDatabasePassword());
		ResultSet result = null;
		try{
			//Creates conn Object with database from first line of file, databaseUser from 2nd,
			//and databasePass from 3rd
			Connection conn = DriverManager.getConnection(database, databaseUser, databasePass);
			
			//Here we set the MariaDB query variable which is the command we are going to use
			//when we query the database in order to obtain information
			String query = "SELECT * FROM users WHERE user_username = '" + user + "'";
			
			//This is Java PreparedStatement, it creates the object preparedStatement which has the 
			//conn variable data and the database query variable. It will be executed in order
			//to connect to the MariaDB database to get our information out
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			
			//This ResultSet is an object that contains the executed query from preparedStatement.
			//if result has stuff in it from the query (i.e. the username we are checking to see
			//is already in the database) then result.first() will have stuff in it and we can
			//set boolean to true. Else it's empty and we set boolean to false
			result = preparedStmt.executeQuery(query);
			
			//close database connection after query
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//check to see if ResultSet result has stuff in it. Return true if so, and false if not.
		try{
			if(result.first()){
				bool = true;
			} else {
				bool = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bool;
	}
	
	public Boolean checkUserPassword(String user, String pass, boolean bool) {
		bool = true;
		Database db = new Database();
		String database = new String(db.getDatabase());
		String databaseUser = new String(db.getDatabaseUser());
		String databasePass = new String(db.getDatabasePassword());
		ResultSet result = null;
		try{
			//Creates conn Object with database from first line of file, databaseUser from 2nd,
			//and databasePass from 3rd
			Connection conn = DriverManager.getConnection(database, databaseUser, databasePass);
			
			//Here we set the MariaDB query variable which is the command we are going to use
			//when we query the database in order to obtain information
			String query = "SELECT user_password FROM users WHERE user_username = '" + user + "'";
			
			//This is Java PreparedStatement, it creates the object preparedStatement which has the 
			//conn variable data and the database query variable. It will be executed in order
			//to connect to the MariaDB database to get our information out
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			
			//This ResultSet is an object that contains the executed query from preparedStatement.
			//Because we have determined that the user already exists in the database, a password will
			//also already exist in the database. We will take the returned information in the ResultSet
			//result and compare it to the password given at the login to see if the passwords match.
			//If they match, then return bool = true. If they do not, return bool = false.
			result = preparedStmt.executeQuery(query);
			
			//close database connection after query
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//check ResultSet result against provided password at login. Return true if passwords match, 
		//and false if they do not.
		try{
			/*note that when you pull a ResultSet, you are pulling a table. So to scan through the table
			data you use result.next() to move the cursor to the next data in the table.. this is
			simply a pointer to the cell in the table that contains the data. So once you are at
			the cell you want the data out of you need to use result.getString(), result.getInt(),
			result.getFloat() etc.  result.getString(1) will get the data out of cell 1 
			*/
			if(result.next()){
				String temp = result.getString(1);
				
				//use .equals() to compare Strings
				if(temp.equals(pass)){
					bool = true;
				} else {
					bool = false;
				}
			} else {
				bool = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bool;
	}
	
	public String getFirstname(String user, String fname){
		Database db = new Database();
		String database = new String(db.getDatabase());
		String databaseUser = new String(db.getDatabaseUser());
		String databasePass = new String(db.getDatabasePassword());
		ResultSet result = null;
		try{
			//Creates conn Object with database from first line of file, databaseUser from 2nd,
			//and databasePass from 3rd
			Connection conn = DriverManager.getConnection(database, databaseUser, databasePass);
			
			//Here we set the MariaDB query variable which is the command we are going to use
			//when we query the database in order to obtain information
			String query = "SELECT user_firstname FROM users WHERE user_username = '" + user + "'";
			
			/*
			 * This is Java PreparedStatement, it creates the object preparedStatement which has the 
			 * conn variable data and the database query variable. It will be executed in order
			 * to connect to the MariaDB database to get our information out
			 */
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			
			//execute database query to get user's first name, save to ResultSet result
			result = preparedStmt.executeQuery(query);
			
			//close connection to database after running query
			conn.close();
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		/*
		 * if ResultSet result grabbed user's first name out of database, it will be the first
		 * entry in the table. use .next() to put the pointer on the data and .getString()
		 * to get the String out of the ResultSet. Then, return String fname.
		 */
		try{
			if(result.next()){
				fname = result.getString(1);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return fname;
	}
	
	public String getStatus(String user, String status){
		Database db = new Database();
		String database = new String(db.getDatabase());
		String databaseUser = new String(db.getDatabaseUser());
		String databasePass = new String(db.getDatabasePassword());
		ResultSet result = null;
		try{
			//Creates conn Object with database from first line of file, databaseUser from 2nd,
			//and databasePass from 3rd
			Connection conn = DriverManager.getConnection(database, databaseUser, databasePass);
			
			//Here we set the MariaDB query variable which is the command we are going to use
			//when we query the database in order to obtain information
			String query = "SELECT user_status FROM users WHERE user_username = '" + user + "'";
			
			/*
			 * This is Java PreparedStatement, it creates the object preparedStatement which has the 
			 * conn variable data and the database query variable. It will be executed in order
			 * to connect to the MariaDB database to get our information out
			 */
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			
			//execute database query to get user's first name, save to ResultSet result
			result = preparedStmt.executeQuery(query);
			
			//close connection to database after running query
			conn.close();
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		/*
		 * if ResultSet result grabbed user's first name out of database, it will be the first
		 * entry in the table. use .next() to put the pointer on the data and .getString()
		 * to get the String out of the ResultSet. Then, return String fname.
		 */
		try{
			if(result.next()){
				status = result.getString(1);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return status;
	}
}