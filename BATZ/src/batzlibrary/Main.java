package batzlibrary;

import java.sql.*;
import java.util.*;
import java.io.*;

public class Main {
	
	//JDBC driver name and database URL
	static String jdbc_driver = "";
	static String database = "";
	static String USER = "";
	static String PASS = "";
	
	public static void main(String[] args) {
		//Set the JDBC Driver for connecting to MariaDB Database
		JDBCdriver jd = new JDBCdriver();
        jdbc_driver = jd.jdbcDriver();
        
        //Instantiate Database Object and update database address, user, pass
        Database db = new Database();
        database = db.dataBase();
		
        //Variable Names
        String username, password, /*locked,*/ status = "", fname = ""; //lname, street, city, state, zip, phone, email;
        boolean sessionOpen = false;
        //String createuser = "", createpass = "", createpin = "", createlocked = "", createstatus = null;
       // String createfname = "", createlname = "", createstreet = "", createcity = "", createstate = "";
       // String createzip = "", createphone = "", createemail = "";
        boolean checkBool = false;
        
        
        //Start Login 
        System.out.println("Login.\n");
        Scanner scan = new Scanner(System.in);
        System.out.print("Username: ");
        username = scan.nextLine();
  
        
        //Check to see if user exists
        Login lg = new Login();
        boolean userlogin = lg.checkUsername(username, checkBool);
        
        if(userlogin == true){
        	System.out.println("User exists\n");
        	System.out.println("Password: ");
        	password = scan.nextLine();
        	checkBool = false;
        	userlogin = lg.checkUserPassword(username, password, checkBool);
        	if(userlogin == true){
        		System.out.println("Username and password match, LOGIN!");
        		sessionOpen = true;
        	} else {
        		System.out.println("Username and password DO NOT match, Goodbye!");
        	}
        } else {
        	System.out.println("User does not exist!");
        }
        
        //set variable values from getters after login successful.
        fname = lg.getFirstname(username, fname);
        status = lg.getStatus(username, status);
        
        System.out.println(fname + "'s status is a " + status + ".");
        
        //use while loop with sessionOpen and timestamp to determine if Login hasn't timed-out
        while(sessionOpen == true){
        	System.out.println("Welcome " + fname + ". What would you like to do?");
        	
        	//stuff to do in here
        	// ***** IF STATUS EQUALS MEMBER
        	if(status.equals("member")){
        		Member me = new Member();
        		me.actionsMe();
        		// ***** IF STATUS EQUALS ASSOCIATE *****
        	} else if(status.equals("associate")){
        		Associate a = new Associate();
        		a.actionsA();
        		// ***** IF STATUS EQUALS MANAGER *****
        	} else if(status.equals("manager")){
        		Manager ma = new Manager();
        		ma.actionsMa();
        		// ***** STATUS PENDING *****
        	} else {
        		System.out.println("Your membership status is still pending. Please contact an associate.");
        	}
        	
        	//close the session
        	sessionOpen = false;
        }
        System.out.println("Session closing. Goodbye!");
// */
        
/*        
        //TEMPORARY - create users
        System.out.println("");
        System.out.println("Creating a new user in the database.\n");
        System.out.println("Enter username.");
        scan = new Scanner(System.in);
        createuser = scan.nextLine();
        
        if(createuser.equals("")){
        	System.out.println("Createuser is empty. Exiting.");
        } else {
        	System.out.println("Enter password.");
        	createpass = scan.nextLine();
        	System.out.println("Enter pin.");
        	createpin = scan.nextLine();
        	System.out.println("Enter locked status(true or false).");
        	createlocked = scan.nextLine();
        	System.out.println("Enter status");
        	createstatus = scan.nextLine();
        	System.out.println("Enter first name");
        	createfname = scan.nextLine();
        	System.out.println("Enter last name");
        	createlname = scan.nextLine();
        	System.out.println("Enter street");
        	createstreet = scan.nextLine();
        	System.out.println("Enter city");
        	createcity = scan.nextLine();
        	System.out.println("Enter state");
        	createstate = scan.nextLine();
        	System.out.println("Enter zip code");
        	createzip = scan.nextLine();
        	System.out.println("Enter phone number");
        	createphone = scan.nextLine();
        	System.out.println("Enter email address");
        	createemail = scan.nextLine();
        	
        	try{
            	Connection conn = DriverManager.getConnection("jdbc:mariadb://batzlibrary.org/BATZlibrary", "batzlibrary", "XvKk1hl36JLqYLKC");
            	System.out.println("Connection Object Created.\n");
            	
            	String query = "INSERT INTO users (user_username, user_password, user_pin, user_locked, user_status, user_firstname, user_lastname, user_street, user_city, user_state, user_zip, user_phone, user_email)" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            	PreparedStatement preparedStmt = conn.prepareStatement(query);
            	
            	preparedStmt.setString (1, createuser);
                preparedStmt.setString (2, createpass);
                preparedStmt.setString (3, createpin);
                preparedStmt.setString (4, createlocked);
                preparedStmt.setString (5, createstatus);
                preparedStmt.setString (6, createfname);
                preparedStmt.setString (7, createlname);
                preparedStmt.setString (8, createstreet);
                preparedStmt.setString (9, createcity);
                preparedStmt.setString (10, createstate);
                preparedStmt.setString (11, createzip);
                preparedStmt.setString (12, createphone);
                preparedStmt.setString (13, createemail);
                
                System.out.println("Attempting database connection..\n");
                preparedStmt.execute();
                conn.close();
                System.out.println("Updated database successfully.");
            } catch (SQLException e) {
            	e.printStackTrace();
            }
        }
*/
    }
}