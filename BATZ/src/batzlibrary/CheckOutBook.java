package batzlibrary;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class CheckOutBook {

	String MemberUserName = "";
	String FirstName = "";
	String LastName = "";
	String FullName = "";
	String Pin = "";
	String id = "";
	String isbn = "";
	String title = "";
	
	
	//Gets member info fist and last name  + their pin	
	public void GetMemInfo() {
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
		MaxBooksCheckOut();

		
	}
	
	
	
	
	
	
	// grabs member name
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
	
	
	//grabs member last name
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
	
	
	//grabs ISBN of book
	public void getISBN() {
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
		String title = "";
		String id = "";
		String isbn = "";
		String BookName = "";
		Scanner scan = new Scanner(System.in);
		System.out.println( "What is the book title?");
		BookName = scan.nextLine();
		
		//Make a loop here to grab all book names from database and check to see if it is an actual book.
		
		//We shall grab the isbn, booktitle and book id of the bookname (might need to be tweaked since I was unable to test this)
		String BookTitle = "SELECT checkbooks_ISBN FROM checkbooks WHERE checkbooks_title = '" + BookName + "'" + " UNION " + " SELECT checkbooks_id FROM checkbooks WHERE checkbooks_title = '" + BookName + "'" + " UNION " + " SELECT checkbooks_title FROM checkbooks WHERE checkbooks_title = '" + BookName + "'" ;
		ResultSet result = s.SQLConnMain(BookTitle);
		String[] dataArray = new String[3];
		try {
			//Needed to get the info because result stores the information like an array
			int i = 0;
			while(result.next()) {
			 dataArray[i] = result.getString(i+1);
			 i++;
			 
			}
			
			isbn = dataArray[0];
			id = dataArray[1];
			title = dataArray[2];
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		this.isbn = isbn;
		this.id = id;
		this.title = title;
		
	}
	
	
	
	//Checks to see if the book limit has not been met | if not then they can check out a book
	public void MaxBooksCheckOut(){
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
		
		
		// return string saying ( The "bookname" has succesfully been checkout out by "memberName" and is due by "returnDate")
		
	}
	
	
	public void checkOutBook() 
	{		
		//make a SQL command call with isbn() in it and also grab the id of the book too.
		SQL s = new SQL();
		getISBN();
		/*String book = getISBN();
		//Split the return from isbn at the " " and set the info to strings
		
		String isbn= "";
		String id = "";
		String title = "";
		
		// or change isbn to void and set the isbn, id ,and title to public variables
		*/
		
		//This updates the number of books checkout for the user
		String checkNum = "SELECT checkedoutnumber FROM users WHERE user_name = '" + MemberUserName + "'";
		ResultSet result = s.SQLConnMain(checkNum);
		String userMax = "";
		try {
			//Needed to get the info because result stores the information like an array
			while(result.next()) {
			 userMax = result.getString(1);
			}
			
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		int num = Integer.parseInt(userMax);
		int max = num + 1;
		String newUpdate = Integer.toString(max);
		
		String update = "Update users SET user_checkedoutnumber ='" + newUpdate +"'" + "WHERE usere_pin = '" + Pin + "'"; 
		ResultSet rzlt = s.SQLConnMain(update); 
		
		//grabs the current time
		LocalDate checkTime = Checkout();
		//This sets the return date
		LocalDate returnTime = Return(checkTime);
		
		//Inserts a new row with this info ( might have to modify code after this -- how to change later when someone wants this book after-----)
		String insert = "INSERT INTO checkbooks (checkbooks_id, checkbooks_ISBN, checkbooks_title, checkbooks_datecheckedout, checkbooks_datetoreturn, checkbooks_pin) VALUES( '" + isbn +", "+ id +", "+ title +"," + checkTime+"," + returnTime +","+ Pin + ")" ;
		ResultSet rezlt1 = s.SQLConnMain(insert);
		
		System.out.println("The User " + FullName + " has successfully checked out" + title + " which is due on the "+ returnTime);
		
		//Return to menu here
		
	}
		
	//Returns the current date
	public LocalDate Checkout() 
	{
		LocalDate checkDay = LocalDate.now();
		return checkDay;
		
	}
	
	
	
	//Returns date 2 weeks from current
	public LocalDate Return(LocalDate time) 
	{
		LocalDate returntime = time.plus(2, ChronoUnit.WEEKS);
		return returntime;
	}
	
	
	
	/* complete method if error in checkoutbook method
	 * public void checkPincheck(){
	 * if(checkboks pin == null)
	 * checkOutBook();
	 * }
	 * esleif( 1 == 1){
	 * System.out.println(" Someone has the book do you want to put it on hold?")
	 * }
	 * else System.out.println(" Member has been set to the waiting queue")
	 * onHold()
	 * }
	 */
}
