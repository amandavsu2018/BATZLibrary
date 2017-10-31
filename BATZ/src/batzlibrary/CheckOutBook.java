package batzlibrary;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class CheckOutBook {

	String MemberUserName = "";
	String FirstName = "";
	String LastName = "";
	String FullName = "";
	String Pin = "";
	public String GetMemPin() {
		SQL s = new SQL();
		
		Scanner scan = new Scanner(System.in);
		String pinRetrieve = "";
		String pin = "";
		String MemberUserName = "";
		String MemberName = "";
		System.out.println("What is the members username?");
		MemberUserName = scan.nextLine();
		this.MemberUserName = MemberUserName;
		
		// this gets the user_pin from the user table
		pinRetrieve = "SELECT user_pin FROM users WHERE user_name = '" + MemberUserName + "'";
		ResultSet resultPin = s.SQLConnMain(pinRetrieve);
		
		try {
			//Needed to get the info because result stores the information like an array
			while(resultPin.next()) {
			pin = resultPin.getString(1);
			}
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		this.Pin = pin;
		String firstName = GetMemNameFirst();
		String lastName = GetMemNameLast();
		this.FirstName = firstName;
		this.LastName = lastName;
		String fullname = new StringBuilder().append(firstName).append(lastName).toString();
		this.FullName = fullname;
		return pin;
		
		
		
		
	}
	public String GetMemNameFirst() {
		SQL s = new SQL();
		
		String MemberName = "";
		String FirstName = "";
		
		
		// this gets the user_firstName from the user table
		MemberName = "SELECT user_firstname FROM users WHERE user_name = '" + MemberUserName + "'";
		ResultSet resultName = s.SQLConnMain(MemberName);
		
		try {
			//Needed to get the info because result stores the information like an array
			while(resultName.next()) {
			 FirstName = resultName.getString(1);
			}
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return FirstName;
	
	
	}
	
	
	
	public String GetMemNameLast() {
		SQL s = new SQL();
		
		String MemberName = "";
		String LastName = "";
		
		
		// this gets the user_firstName from the user table
		MemberName = "SELECT user_lastname FROM users WHERE user_name = '" + MemberUserName + "'";
		ResultSet resultName = s.SQLConnMain(MemberName);
		
		try {
			//Needed to get the info because result stores the information like an array
			while(resultName.next()) {
			 LastName = resultName.getString(1);
			}
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return LastName;
	
	
	}
	
	
	
	public String getISBN() {
		SQL s = new SQL();
		
		/* I must get the book name first
		 * then i must check to see if that book is there
		 * then I see if the book is checked out
		 * if book is not checked out check if the book is on hold
		 * if the book is not on hold
		 * add book to user
		 * 
		 * ****might need to use book id to reference which book is out****
		 * I do, so i'll check to see if the book is available, if it is then I grab the book id number and set that book to the user who wants to check it out
		 * ending should look like this: FullName Pin "checked out" BookID for BookName
		 */
		String ISBN = "";
		String BookName = "";
		Scanner scan = new Scanner(System.in);
		System.out.println( "What is the book title?");
		BookName = scan.nextLine();
		
		//We shall grab the isbn of the bookname
		String BookTitle = "SELECT checkbooks_ISBN FROM checkbooks WHERE checkbooks_title = '" + BookName + "'";
		ResultSet result = s.SQLConnMain(BookTitle);
		
		try {
			//Needed to get the info because result stores the information like an array
			while(result.next()) {
			 ISBN = result.getString(1);
			}
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return ISBN;
	}
	
	
	
	
	public String MaxBooksCheckOut(){
		SQL s = new SQL();
		String count = "";
		int max = 10;
		int current = Integer.parseInt(count);
		
		//Grabbing the amount of books currently checked out
		String BookNum = "SELECT user_checkedoutnumber FROM users WHERE user_pin = '" + Pin + "'";
		ResultSet result = s.SQLConnMain(BookNum);
		
		try {
			//Needed to get the info because result stores the information like an array
			while(result.next()) {
			 count = result.getString(1);
			}
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		if(current < max || current != max) {
			//write this method
			checkOutBook();
		}
		
		else {
			System.out.println("You have reached the maximum amount of books checked-out");
		}
		
		
		return null;
	}
	
	
	
	
	public void checkOutBook() {
		//make a SQL command call with isbn() in it and also grab the id of the book too.
	}
	
	
	
	
}
