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
	String id = "";
	String pin = "";
	String isbn = "";
	String title = "";
	String choice = "";
	
	//Checks to see if the book limit has not been met | if not then they can check out a book
	public void MaxBooksCheckOut(){
		SQL s = new SQL();
		String count = "";
		int max = 10;
		int current = Integer.parseInt(count);
		
		//Grabbing the amount of books currently checked out
		String BookNum = "SELECT user_checkedoutnumber FROM users WHERE user_pin = '" + pin + "'";
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
			//checkOutBook();
		}
		
		else {
			System.out.println("You have reached the maximum amount of books checked-out");
		}
		
		
		// return string saying ( The "bookname" has succesfully been checkout out by "memberName" and is due by "returnDate")
		
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
	
	public boolean checkUserExists(String pinnum) {
		boolean cuebool = false;
		String query = "SELECT * FROM users WHERE user_pin = '" + pinnum + "'";
		
		//check to see if user exists
		CheckUserExists cue = new CheckUserExists();
		cue.setPin(pinnum);
		cuebool = cue.checkIfPinExistsInDB(query);
		return cuebool;
	}
	
	public boolean checkCorrectUser(String pinnum) {
		boolean cuebool = false;
		String query = "SELECT * FROM users WHERE user_pin = '" + pinnum + "'";
		CheckUserExists cue = new CheckUserExists();
		cue.setPin(pinnum);
		cuebool = cue.checkIfPinExistsInDB(query);
		if(cuebool == true) {
			cue.connect();
		}
		System.out.println("\nIs this the correct user? (y/n)");
		Scanner scanp = new Scanner(System.in);
		while(true) {
			choice = scanp.nextLine();
			if(choice.equals("y") || choice.equals("Y") || choice.equals("yes")) {
				cuebool = true;
				break;
			} else if(choice.equals("n") || choice.equals("N") || choice.equals("no")) {
				cuebool = false;
				break;
			} else {
				System.out.println("Please enter a 'y' or an 'n'");
			}
		}
		
		return cuebool;
	}
	
	public boolean checkUserLockedStatus(String pin) {
		boolean cuebool = false;
		String status = "";
		UsersTableCheckout utc = new UsersTableCheckout();
		status = utc.getUserLockedStatus(pin);
		if(status.equals("true")) {
			cuebool = true;
		}
		
		return cuebool;
	}
	
	public boolean checkCorrectBook(String isbn){
		boolean bool = false;
		String[] bookinfo = {"Title: ", "Authors: ", "ISBN: ", "Publication Year: ", "Keywords: "};
		String var = "";
		CheckBookExists cbe = new CheckBookExists();
		ResultSet result = cbe.returnExistingBook(isbn);
		try{
			while(result.next()){
				for (int i = 1; i < 6; i++) {
					var = result.getString(i+1);
					System.out.print(bookinfo[i-1]);
					System.out.println(var);
				}
				System.out.println("");
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		System.out.println("\nIs this the correct book? (y/n)");
		Scanner scanp = new Scanner(System.in);
		while(true) {
			choice = scanp.nextLine();
			if(choice.equals("y") || choice.equals("Y") || choice.equals("yes")) {
				bool = true;
				break;
			} else if(choice.equals("n") || choice.equals("N") || choice.equals("no")) {
				bool = false;
				break;
			} else {
				System.out.println("Please enter a 'y' or an 'n'");
			}
		}
		
		return bool;
	}
	
	public void checkOut(String isbnnum, String pinnum) {
		CheckbooksTableMethods cbtm = new CheckbooksTableMethods();
		ResultSet result = null;
		boolean cobool = false;
		String checkbooksid = "";
		
		//check if books are not all checked out
		result = cbtm.getCheckbookAvailable(isbnnum);
		try{
			if(result.first() == true){
				checkbooksid = result.getString(1);
				/** NEED TO ADD CODE **/
			} else {
				System.out.println("This book's inventory is currently all checked out.\n");
				System.out.println("Would you like to place a hold on this book? (y/n)");
				Scanner scanp = new Scanner(System.in);
				while(true) {
					choice = scanp.nextLine();
					if(choice.equals("y") || choice.equals("Y") || choice.equals("yes")) {
						BookqueueTableMethods bqtm = new BookqueueTableMethods();
						bqtm.setBookHold(isbnnum, pinnum);
						break;
					} else if(choice.equals("n") || choice.equals("N") || choice.equals("no")) {
						break;
					} else {
						System.out.println("Please enter a 'y' or an 'n'");
					}
				}
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		
	}
	
//	public void checkOutBook() 
//	{		
		//make a SQL command call with isbn() in it and also grab the id of the book too.
//		SQL s = new SQL();
//		getISBN();
		/*String book = getISBN();
		//Split the return from isbn at the " " and set the info to strings
		
		String isbn= "";
		String id = "";
		String title = "";
		
		// or change isbn to void and set the isbn, id ,and title to public variables
		*/
/*		
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
		
	}*/
}


