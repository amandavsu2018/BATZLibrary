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
	SQL s = new SQL();
	int noOfDays = 14;
	String id = "";
	String pin = "";
	String isbn = "";
	String title = "";
	String choice = "";
	
	//Returns the current date
	public LocalDate getCurrentDate() {
		LocalDate today = LocalDate.now();
		return today;
	}
	
	//Returns date 2 weeks from current
	public LocalDate getTwoWeeksDate(LocalDate today) {
		LocalDate twoWeeksAdd = today.plus(2, ChronoUnit.WEEKS);
		return twoWeeksAdd;
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
				checkOutBook(checkbooksid, pinnum);
				System.out.println("Book Checked out successfully!\n");
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
	
	//Check out a book, update checkedbooks table, users table
	public boolean checkOutBook(String checkbookid, String pinnum) {
		CheckbooksTableMethods cbtm = new CheckbooksTableMethods();
		UsersTableCheckout utc = new UsersTableCheckout();
		int amount = 0;
		String getAmount = "";
		LocalDate today = getCurrentDate();
		LocalDate twoWeeksDate = getTwoWeeksDate(today);
		
		//update checbooks table
		cbtm.setCheckedOutDateOnBook(today, checkbookid);
		cbtm.setReturnDateOnBook(twoWeeksDate, checkbookid);
		cbtm.setPINNumberOnBook(pinnum, checkbookid);
		
		//update users table (amount of books checked out)
		getAmount = utc.getAmountCheckedOut(pinnum);
		amount = Integer.parseInt(getAmount);
		amount++;
		getAmount = Integer.toString(amount);
		utc.setAmountCheckedOut(pinnum, getAmount);
		
/*		//This updates the number of books checkout for the user
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
*/		
		//System.out.println("The User " + FullName + " has successfully checked out" + title + " which is due on the "+ returnTime);
		return true;
	}
}


