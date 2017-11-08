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
		boolean ccubool;
		String query = "SELECT * FROM users WHERE user_pin = '" + pinnum + "'";
		String question = "\nIs this the correct user?";
		BATZUtils utils = new BATZUtils();
		UsersTable usertable = new UsersTable();
		usertable.setPin(pinnum);
		ccubool = usertable.checkIfPinExistsInDB(query);
		if(ccubool == true) {
			usertable.connect();
			
			Scanner scanp = new Scanner(System.in);
			ccubool = utils.yesOrNo(question);
		} 
		return ccubool;
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
		String question = "\nIs this the correct book? (y/n)";
		BATZUtils utils = new BATZUtils();
		BooksTable bt = new BooksTable();
		ResultSet result = bt.returnExistingBook(isbn);
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
		
		/*
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
		*/
		utils.yesOrNo(question);
		
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
						BooksQueueTable bqt = new BooksQueueTable();
						bqt.setBookHold(isbnnum, pinnum);
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
		BATZUtils utils = new BATZUtils();
		int amount = 0;
		String getAmount = "";
		LocalDate today = utils.getCurrentDate();
		LocalDate twoWeeksDate = utils.getTwoWeeksDate(today);
		
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
		
		return true;
	}
}


