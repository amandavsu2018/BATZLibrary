# group3 BATZ Library
Brandon Corbett has checked in.   
Amanda Mitchell has checked in.  
Thara Larkins has checked in.  
Zackery Weeks has checked in.  


  
  
1.	Our program requires the MariaDB JDBC jar file in order to run correctly. It can be obtained by following the download link listed       below.
      1.	https://downloads.mariadb.com/Connectors/java/connector-java-2.2.0/mariadb-java-client-2.2.0.jar
      2.	In Eclipse, click on Project -> Properties -> Java Build Path -> Add External JARs.
2.	Once the MariaDB JAR file is added to the build path, the program can be executed and run.
3.	For access to our database online:
    1.	http://batzlibrary.org/phpmyadmin
    2.	username: batzlibrary
    3.	password: XvKk1hl36JLqYLKC

4.	You may select an option:
	   1.	 1. Login 
	   2.	 2. Search for a book that is in the library 
	   3.	 3. To exit.
5.	If you select option 1 you will be prompted to enter a username first. Then a password:

		    1.	For Associate user account type: 
			  i.	Username: thanks
			  ii.	Password: test
		    2.	For Member user account type: 
			  i.	Username: Teset
			  ii.	Password: 3qZLePT
		    3.	For Manager user account type: 
			  i.	Username: testy
			  ii.	Password: test
			  
6.	As an Associate you may choose the following options:

		      1.	Add User to Database 
		      2.	Display User Info
		      3.	Checkout Book for Member 
		      4.	Check In Books From Dropbox
		      5.	Display Holds for User
		      6.	Exit
7.	If option 1 is selected you will be prompted to create a new user:

        1.	Enter username(if username already exists it will add a incrementing number)
        2. The password will be Auto generated
        3. The user pin will be Auto generated
        4. You will have the option to create the member status
		 i. Member
		 ii. Associate
        5. Enter the Users first name
        6. Enter the user’s last name
        7. Enter the user's address
        8. Enter the users City
        9. Enter the user's state
        10. Enter the user's phone number.
	
8.	If option 2 is selected you will be prompted to enter the user's pin

	      1.You will be asked if the current users name is the user you are looking for
			  i. If no is entered it will re-prompt you to put in the user’s pin.
			  ii. If yes is entered it will fetch the users information and display it.
	  
9.	If option 3 is selected you will be prompted to enter the user’s Library card (Aka: user pin)

	      1. If the pin does not exist, you will be prompted it is not in the database
	      2. If the pin does exist and the user is locked out, you will be prompted of the user being locked out and to contact a manager
	      3. If the pin does exist and the user is not locked out, you shall be prompted to enter the ISBN of the book to check out	
	     		 i.If the book is not in the database, you shall be prompted
	      4. If the book is there, and you have 10 books checked out, you will be denied the ability to checkout
	      5. If the book is there and the user has not hit their 10 book limit:
			    i.	The book will be checkout
			    ii.	Or the book’s inventory is completely checked out and the user can have the option of being on the waiting list for a book.
			    
10.	If options 4 is selected the drop box will be checked for books members have returned and set them back into inventory.

11.	If option 5 is selected it will prompt  you for the members library card number(Aka User Pin number)

		      1. The program will display the pin is invalid if you enter a pin that does not exist
		      2. If the pin exist, the users holds shall be shown
		      
12.	If option 6 is selected the program will exit the session

5. (continued from step 5) if Member is selected you will have the option to:

		1. Search for books
		2. Return Books
		3. Exit
		
6. if 1(Search for books) is selected you will be prompted on how you would want to search for a book

	    1. By Isbn
	    2. By Keyword
	    3. Exit
    
7. if 1 is selected, you will be prompted to enter the ISNB number and the books information will be shown

8. if 2 is selected, you will be prompted to enter a keyword from the book and the books information shall be shown

9. if 2(Return books) is selected:

	    1.	The user will be asked to enter their pin
	    2.	Then enter the isbn of the book
	    3.	Then the book will be placed in inventory and you shall be asked if you would like to return another.
			 1.	If yes, the user will be re-prompted to enter a pin.
			 2.	If no, the user will be taken back to the main menu.

10.	If 3 is selected(Exit) the session will end

5.(continued from step 5) As a Manager you may choose: 

        1. Add User to Database.
        2. Create book listing
        3. Edit User Account.
        4. Edit Book.
        5. Reactivate a User Account
        6. Display User Info
        7. Checkout Book for Member 
        8. Check In Books From Dropbox 
        9. Exit
	
6. If option 1 (Add User to Database ) is selected you will be prompted to create a new user:

        1. Enter username(if username already exists it will add an incrementing number)
        2. The password will be Auto generated
        3. The user pin will be Auto generated
        4. You will have the option to create the member status
              i.	Member
              ii.	Associate
              iii.	Manager
        5. Enter the users first name
        6. Enter the user’s last name
        7. Enter the user's address
        8. Enter the users City
        9. Enter the user's state
        10. Enter the user's phone number.
	
7. If option 2 (Create book Listing) is selected, the user will be able to create a book listing.

        1. The user will be prompted to enter an ISBN number.
            	a.	If the ISBN number is not 10 or 13 characters long, the user will be re-prompted to enter an ISBN number.
        2. The user will be prompted to enter a book title.
        3. The user will be prompted to enter author.
        4. The user will be prompted to enter the book publishing year.
        5. The user will be prompted to enter book keywords.
        6. The user will be prompted to enter the amount of books that are in inventory. 

8. if option 3 (Edit User Account) is selected you will be prompted to enter the users pin number

		 Once the pin number is entered you will have the following options:
		  1. Generate new password
		  2. Set lock for user
		  3. Edit Status
		  4. Edit First Name
		  5. Edit Last name
		  6. Edit Street Address
		  7. Edit City
		  8. Edit State
		  9. Enter Zip code
		  10. Edit Phone
		  11. Exit
		    a)	If option 1 is selected a new randomly generated password is created.
		    b)	If option 2 is selected you will be prompted to enter true to lock the user account or false to not lock the user’s                     account
		    c)	If option 3 is selected you will be prompted to enter Manager, associate, or member to set the new status of the member
		    d)	If option 4 is selected you will be prompted to enter the First Name
		    e)	If option 5 is selected you will be prompted to enter the Last Name
		    f)	If option 6 is selected you will be prompted to enter the Street Address
		    g)	If option 7  is selected you will be prompted to enter the City
		    h)	If option 8 is selected you will be prompted to enter the State
		    i)	If option 9 is selected you will be prompted to enter the Zip code
		    j)	If option 10 is selected you will be prompted to enter the phone number
		    k)	If option 11 is selected you will exit the menu
            
If option 4 (edit book) is selected you will be prompted to enter the book’s isbn 

        1. Then you will be prompted to choose 1 of the following:
			a. Edit title.
			b. Edit author.
			c. edit ISBN
			d. edit publishing year
			e. edit keywords (separated by commas)
			f. edit inventory number
			g. exit
        
1.	if option 1 is selected you will be prompted to enter the Title

2.	if option 2 is selected you will be prompted to enter the Author

3.	if option 3 is selected you will be prompted to enter the ISBN

4.	if option 4 is selected you will be prompted to enter the publishing year

5.	if option 5 is selected you will be prompted to enter the keywords

6.	if option 6 is selected you will be prompted to enter the inventory number

7.	if option 7 is selected you will exit the menu

If option 5 (Reactivate User) is selected you will be prompted to enter the user’s pin number that you want to reactivate.

      1. If the pin does not match a pin in the database, the user will be prompted to enter another pin.
      2. If the pin exists, the user will be unlocked (locked status set to false)
      
If option 6 (Display User info) is selected you will be prompted to:

    1. Enter the user’s pin
        1. if user pin does not exist in the database, the user will be prompted to enter another pin.
        2. if user pin is in the database, the corresponding user’s information will be displayed.
If option 7 (Check out book for member) is selected you will be prompted to enter the user’s Library card (Aka: user pin)

        1. If the pin does not exist, the program will display that the pin is not in the database and the user will return to the main              menu.
        2. If the pin does exist and the user is locked out, you will be prompted of the user being locked out and to contact a manager
        3. If the pin does exist and the user is not locked out, you will be prompted to enter the ISBN of the book to check out	
              1. If the book is not in the database, the user will be sent back to the main menu
              2. If the book does exist in the database, the book will be checked out if available.
              3. If the book exists in the database but all are currently checked out, the user will be asked if the book would like to                   be placed on hold.
                    a.	if yes, the book will go in the book queue.
                    b.	if no, the user will return to the main menu.
        4. If the book is there, and you have 10 books checked out, you will be denied the ability to checkout
        5. If the book is there and the user has not hit their 10 book limit:
                  i.	The book will be checkout
                  ii.	Or the book’s inventory is completely checked out and the user can have the option of being on the waiting list                         for a book.

If option 8 (Check In Books From Dropbox) is selected the drop box will be checked for books members have returned and set them back into inventory.

If option 9 (exit)  is selected the session will exit
	
						**(Continued from the main login prompt)**
3. (continued from step 4) if option 2 (Search) selected you will be prompted on how you would want to search for a book

        1.By ISBN
        2.By Keyword
        3. Exit
	
4. if 1 is selected, you will be prompted to enter the ISBN number and the books information will be displayed.

5. if 2 is selected, you will be prompted to enter a keyword from the book and the books information shall be displayed

        A. if the keyword does not exist in any entry in the database, no information will be displayed and the user will be re-                   prompted for search options.
	
6. if option 3 is selected you will exit the menu

3. (continued from step 4) If option 3 (exit) is selected you will exit the program
