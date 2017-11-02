package batzlibrary.Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import batzlibrary.Database;

public class TestDatabase {
	//String testcase = "jdbc:mariadb://batzlibrary.org/BATZlibrary";
	String database = null;
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testGetDatabase() {
		String testcase1 = "blahblah";
		String testcase2 = "jdbc:mariadb://batzlibrary.org/BATZlibrary";
		Database db = new Database();
		database = db.getDatabase();
		System.out.println(database);
		
		// failure case
		// assertEquals(testcase1, database);
		
		// non-failure case
		// assertEquals(testcase2, database);
	}
}
