package batzlibrary;

public class BookqueueTableMethods {
	SQL s = new SQL();
	
	public boolean setBookHold(String isbn, String pin){
		String[] statement = {isbn, pin};
		String query = "INSERT INTO bookqueue (bookqueue_ISBN, bookqueue_pin)"
				+ " VALUES (?, ?);";
		s.SQLConnForMoreThanOnePreparedStatement(query, statement);
		return true;
	}
}
