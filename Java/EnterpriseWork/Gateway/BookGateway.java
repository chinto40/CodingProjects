package Gateway;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.sql.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import Models.AuditTrailEntry;
import Models.Author;
import Models.AuthorBook;
import Models.Book;
import Models.User;
import javafx.stage.Stage;

/**
 * Creates a Link between A database and this program. 
 * @author Jacinto Molina
 */
public class BookGateway {
	/*
	 * Logger error for Checking Log stuff.
	 */
	private static final Logger logger = LogManager.getLogger();
	// CRUD - Create, 
	/*
	 * Connection varible to allow for SQL Connection. 
	 */
	private Connection connection;
	
	//public static BigInteger version;
	
	/*
	 * Data source connection for sql Data source.
	 */
	private MysqlDataSource db = new MysqlDataSource();
	//singleton pattern 
	private static BookGateway instance = null;
	
	public static Book bookSelected;
	
	public static Author authorSelected;
	
	public static int SizeofTables;
	
	public static int AccessLevel =0 ;
	public static User currentUser;
	
	/**
	 * Constructs a new BookGateway Object. 
	 */
	private BookGateway() {
		try {
			System.out.println("Creating Connection...");			
			db.setURL("jdbc:mysql://easel2.fulgentcorp.com/xtd781");
			db.setUser("xtd781");
			db.setPassword("29IOaTqa45wBI3Ti07OL");
			this.connection= db.getConnection();
			//version = new BigInteger("00000");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error: in Connection Init SQL...");
			e.printStackTrace();
		}
	}
	
	/*
	 * Returns an instance of this class. 
	 */
	public static BookGateway getInstance() {
		if(instance == null) {
			instance = new BookGateway();
		}
		return instance;
	}

	//CRUD - Create, Read, Update, Delete
	/**
	 * Inserts a row into the Table specified. 
	 * @param title - The Title of the book being created. 
	 * @param author - The Author of the book.
	 * @param gen - The Genre of the Book being created. 
	 * @param sum - The Summary of the Book being created. 
	 * @param dop - The Date of Publication of the Book being created.
	 * @param isbn - The ISBN of the Book being created. 
	 */
	public void create(String title,String author, String gen,String sum,String dop, String isbn,int pubID) {
		try {
		String create = "Insert into Books(Title, Author, Genre, Summary, Dop,Isbn, publisher_id)" +
				"VALUES (?,?, ?, ?, ?, ?,?)";
			PreparedStatement query = (PreparedStatement) this.connection.prepareStatement(create);
			query.setString(1, title);
			query.setString(2, author);
			query.setString(3, gen);
			query.setString(4, sum);
			query.setString(5, dop);
			query.setString(6, isbn);
			query.setInt(7, pubID);
			query.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Creates and Audit Message and adds it to the database. 
	 * @param msg Message being saved into the database. 
	 * @param bookID the specific book being added.
	 */
	public void createAuditMessage(String msg, int bookID) {
		try {
			String create = "INSERT into book_audit_trail(book_id, entry_msg) VALUES (?,?)";
			PreparedStatement ps = (PreparedStatement) this.connection.prepareStatement(create);
			ps.setInt(1, bookID);
			ps.setString(2, msg);
			ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/*********************Password*********************************/
	public boolean getAuthentication(String login, String Password) {
		try {
			String read = "Select * from Users where Login='"+login+"'";
			PreparedStatement ps = (PreparedStatement) this.connection.prepareStatement(read);
			ResultSet result = ps.executeQuery();
			
			if(result == null) {
				return false;
			}
			
			
			if(result.next()) {
				if(Password.equals(result.getString("Pass"))) {
					currentUser = new User(result.getString("Name"),result.getString("Role"));
					return true;
				}
			}
			return false;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	/*********************Password end****************************/
	
	
	
	/*
	 * public boolean isMatchLogin(String userid, String pass) {
		try {
			String read = "SELECT * FROM dbStudent Where Student_ID='"+userid + "'";
			PreparedStatement ps = (PreparedStatement)this.conn.prepareStatement(read);
			ResultSet result = ps.executeQuery();
			
			if(result.next()) {
				
				// can save the stuff here... if any...
				//String fName,String mInit,String lName,String id, categoryUser s
				Declarations.currentUser = new User(result.getString("Fname"),result.getString("Minit"),result.getString("lName"),result.getString("Student_ID"),Declarations.categoryUser.Student);
				return true;
			}
			
			return false;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	 */
	
	
	
	
	/**
	 * Returns the specific id of a book. 
	 * @param isbn the isbn number of the book being looked for. 
	 * @return the id of the book.
	 */
	public int getBookID(String isbn) {
		try {
			String read = "Select ID from Books where Isbn=?";
			PreparedStatement ps = (PreparedStatement) this.connection.prepareStatement(read);
			ps.setString(1, isbn);
			ResultSet result = ps.executeQuery();
			result.next();
			int id = result.getInt("ID");
			return id;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int getCountOfDBWild(String wild) {
		try {
			String count = "Select Count(publisher_id) From Books Where Title Like '" + wild + "%'";
			System.out.println(count);
			PreparedStatement ps = (PreparedStatement) this.connection.prepareStatement(count);
			ResultSet result = ps.executeQuery();
			if(result.next()) {
				return result.getInt(1);
			}
			return 0;
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int getCountOfDB() {
		try {
			String count = "Select Count(publisher_id) From Books";
			PreparedStatement ps = (PreparedStatement) this.connection.prepareStatement(count);
			ResultSet result = ps.executeQuery();
			if(result.next()) {
				return result.getInt(1);
			}
			return 0;
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public ArrayList<Book> readWild(int min, String wild) {
		try {
			System.out.println("In Read!!");
			ArrayList<Book> books = new ArrayList<Book>();
			String read = "Select * FROM Books Where Title Like '" + wild + "%'" + " LIMIT " + min +",50";
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(read);
			ResultSet result= ps.executeQuery();
			
			while(result.next()) {
				books.add(new Book(result.getString("Title"), Integer.parseInt(result.getString("Isbn")),
								   result.getString("Author"), result.getString("Dop"),
								   result.getString("Genre"), result.getString("Summary"), Integer.parseInt(result.getString("Publisher_id")), result.getInt("ID")));
				
				books.get(books.size()-1).setTimestamp(result.getTimestamp("last_modified").toLocalDateTime());
			}
			//System.out.println("After Read!!");
			return books;
			
		}catch(Exception e) {
			logger.error("Error: Read");
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public ArrayList<Book> read(int min) {
		try {
			System.out.println("In Read!!");
			ArrayList<Book> books = new ArrayList<Book>();
			String read = "Select * FROM Books LIMIT " + min +",50";
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(read);
			ResultSet result= ps.executeQuery();
			
			while(result.next()) {
				books.add(new Book(result.getString("Title"), Integer.parseInt(result.getString("Isbn")),
								   result.getString("Author"), result.getString("Dop"),
								   result.getString("Genre"), result.getString("Summary"), Integer.parseInt(result.getString("Publisher_id")), result.getInt("ID")));
				
				books.get(books.size()-1).setTimestamp(result.getTimestamp("last_modified").toLocalDateTime());
			}
			//System.out.println("After Read!!");
			return books;
			
		}catch(Exception e) {
			logger.error("Error: Read");
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	/**
	 * Reads the table of books and saves each book into a book Object in a list. Returns that list. 
	 * @param table The name of the Table being read from.  
	 * @return The List of books being returned. 
	 */
	public ArrayList<Book> read(String table) {
		try {
			System.out.println("In Read!!");
			ArrayList<Book> books = new ArrayList<Book>();
			String read = "Select * from " + table;
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(read);
			ResultSet result= ps.executeQuery();
			
			while(result.next()) {
				books.add(new Book(result.getString("Title"), Integer.parseInt(result.getString("Isbn")),
								   result.getString("Author"), result.getString("Dop"),
								   result.getString("Genre"), result.getString("Summary"), Integer.parseInt(result.getString("Publisher_id")), result.getInt("ID")));
				
				books.get(books.size()-1).setTimestamp(result.getTimestamp("last_modified").toLocalDateTime());
			}
			//System.out.println("After Read!!");
			return books;
			
		}catch(Exception e) {
			logger.error("Error: Read");
			e.printStackTrace();
		}
		return null;
	}
	
	
	public Book readBook(String isbn) {
		try {
			System.out.println("In Read!!");
			String read = "Select * from Books WHERE Isbn=?";
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(read);
			ps.setString(1, isbn);
			ResultSet result= ps.executeQuery();
			Book b = null;
			if(result.next()) {
			 b = new Book(result.getString("Title"), Integer.parseInt(result.getString("Isbn")),
								   result.getString("Author"), result.getString("Dop"),
								   result.getString("Genre"), result.getString("Summary"), Integer.parseInt(result.getString("publisher_id")), result.getInt("ID"));
				
				b.setTimestamp(result.getTimestamp("last_modified").toLocalDateTime());
			}
			//System.out.println("After Read!!");
			return b;
			
		}catch(Exception e) {
			logger.error("Error: Read");
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * Returns a list of AuditTrailEntry objects from the database for a specific book.
	 * @param The id of the book being returived.  
	 * @return A list of auditTrailEntry object from the database. 
	 */
	public ArrayList<AuditTrailEntry> getListAuditTrailEntry(String id) {
		try {
			//System.out.println("In Read!!");
			ArrayList<AuditTrailEntry> audit = new ArrayList<AuditTrailEntry>();
			String read = "Select * from book_audit_trail WHERE book_id=?";
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(read);
			ps.setString(1, id);
			ResultSet result= ps.executeQuery();
			
			while(result.next()) {
				audit.add(new AuditTrailEntry( Integer.parseInt(result.getString("book_id")),
								   result.getTimestamp("date_added").toLocalDateTime(), result.getString("entry_msg")));
			}
			//System.out.println("After Read!!");
			return audit;
			
		}catch(Exception e) {
			logger.error("Error: Read");
			e.printStackTrace();
		}
		return null;
	}
	
	
	// gets the string associated with one book.. 
	public String getCurrentBookAuthor(String bookISBN) {
		try {
			String read = "Select Author FROM Books WHERE Isbn=?";
			PreparedStatement ps = (PreparedStatement) this.connection.prepareStatement(read);
			ps.setString(1, bookISBN);
			ResultSet result = ps.executeQuery();
			
			result.next();
			String bookAuthor = result.getString("Author");
			
			return bookAuthor;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public ArrayList<String> getListAuthorFromBooks(){
		try {
			ArrayList<String> auth = new ArrayList<String>();
			String read = " Select Author FROM Books";
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(read);
			ResultSet result= ps.executeQuery();
			
			while(result.next()) {
				//auth.add(result.getString("Author"));
				auth = checkList(auth,result.getString("Author"));
			}
			return auth;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/********************** Authors *********************************************/
	// gets the list of all authors on file...
	public ArrayList<Author> getListAuthorFromAuthorAA(){
		try {
			ArrayList<Author> auth = new ArrayList<Author>();
			String read = "Select * FROM AuthorsId";
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(read);
			ResultSet result = ps.executeQuery();
			
			while(result.next()) {
				//auth.add(result.getString("Author"));
				//int iD, String firstN, String lastN, LocalDate dob, String gen, String web
				auth.add(new Author(result.getInt("id"),result.getString("first_name"),result.getString("last_name"),
									(result.getDate("dob").toLocalDate()), result.getString("gender"),result.getString("web_site"))); // Add new author
			}
			return auth;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void DeleteAuthorAA(int id) {
		try {
			String delete = "DELETE FROM AuthorsId WHERE id=?";
			PreparedStatement ps = (PreparedStatement)this.connection.prepareStatement(delete);
			ps.setInt(1, id);
			ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insertAuthorAA(String firstN, String lastN, Date dob, String gen, String web) {
		try {
			String insert = "INSERT AuthorsId (first_name,last_name,dob,gender,web_site) VALUES (?,?,?,?,?)";
			PreparedStatement ps =(PreparedStatement) this.connection.prepareStatement(insert);
			ps.setString(1, firstN);
			ps.setString(2, lastN);
			ps.setDate(3, dob);
			ps.setString(4, gen);
			ps.setString(5, web);
			ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void UpdateAuthorAA(int iD, String firstN, String lastN, Date dob, String gen, String web) {
		try {
			String create = "UPDATE AuthorsId SET first_name = ?,last_name =? ,dob=?,gender =? WHERE id = ?";
			PreparedStatement ps = (PreparedStatement) this.connection.prepareStatement(create);
			ps.setString(1, firstN);
			ps.setString(2, lastN);
			ps.setDate(3, dob);
			ps.setString(4, gen);
			ps.setInt(5, iD);
			ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/************************* Authors Ends *************************************/
	
	public ArrayList<String> checkList(ArrayList<String> list, String name){
		ArrayList<String> l = list;
		String[] token = name.split(",");
		
		for(int index = 0; index < token.length; index++ ) {
			if(!(l.contains(token[index]))) {
				l.add(token[index]);
			}
		}
		
		return l;
	}
	
	
	
	
	
	
	
	/**
	 * Returns a list of AuditTrailEntry objects from the database. 
	 * @return A list of auditTrailEntry object from the database. 
	 */
	public ArrayList<AuditTrailEntry> getListAuditTrailEntry() {
		try {
			System.out.println("In Read!!");
			ArrayList<AuditTrailEntry> audit = new ArrayList<AuditTrailEntry>();
			String read = "Select * from book_audit_trail";
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(read);
			ResultSet result= ps.executeQuery();
			
			while(result.next()) {
				audit.add(new AuditTrailEntry( Integer.parseInt(result.getString("book_id")),
								   result.getTimestamp("date_added").toLocalDateTime(), result.getString("entry_msg")));
			}
			//System.out.println("After Read!!");
			return audit;
			
		}catch(Exception e) {
			logger.error("Error: Read");
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * Returns the specific localDateTime of a book. 
	 * @param isbn the isbn number of the book being searched for. 
	 * @return the LocalDateTime of a book. 
	 */
	public LocalDateTime getTimeStamp(String isbn) {
		LocalDateTime r = null; 
		try {
			String read = "SELECT last_modified FROM Books Where Isbn=?";		
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(read);
			ps.setString(1, isbn);
			ResultSet result = ps.executeQuery();
			result.next();
			r = result.getTimestamp("last_modified").toLocalDateTime();
			return r;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * Returns a list of publisher id. 
	 * @return the id of all publisher in the database. 
	 */
	public ArrayList<String> getPublisherAllID() {
		try {
			ArrayList<String> pubList = new ArrayList<String>();
		
		
			System.out.println("In Read!!");
			//ArrayList<Book> books = new ArrayList<Book>();
			String read = "Select id from Publisher";
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(read);
			ResultSet result= ps.executeQuery();
			
			while(result.next()) {
				pubList.add(result.getString("publisher_name"));
			}
			//System.out.println("After Read!!");
			return pubList;
			
		}catch(Exception e) {
			logger.error("Error: Read");
			e.printStackTrace();
		}
		return null;

	}
	
	
	// Going to retrive Database names of all Publishers

	/**
	 * Returns a list of publisher names. 
	 * @return the names of all publisher in the database. 
	 */
	public ArrayList<String> getPublisherNames() {
		try {
			ArrayList<String> pubList = new ArrayList<String>();
		
		
			System.out.println("In Read!!");
			//ArrayList<Book> books = new ArrayList<Book>();
			String read = "Select publisher_name from Publisher";
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(read);
			ResultSet result= ps.executeQuery();
			
			while(result.next()) {
				pubList.add(result.getString("publisher_name"));
			}
			//System.out.println("After Read!!");
			return pubList;
			
		}catch(Exception e) {
			logger.error("Error: Read");
			e.printStackTrace();
		}
		return null;

	}
	
	/*
	 * Returns a specific publisher names from an id. 
	 */
	public String getPublisherName(int id) {
		try {
			
			String read = "SELECT publisher_name FROM Publisher Where id=?";
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(read);
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();
			String name = "";
			if(result.next()) {
				name = result.getString("publisher_name");
			}
			return name;
			
		}catch(Exception e) {
			e.printStackTrace();
			// TODO: Logger message
		}
		return null;
	}
	
	
	// Going to get The Publisher ID of a specific Publisher. 

	/**
	 * Returns an id of publisher using the name.  
	 * @return  the id of a specific publisher. 
	 */
	public int getPublisherID(String pub) {
		try {
		
			if(pub == null) {
				pub = "Unkown";
			}
			System.out.println("In pubint read!!");
			String read = "Select id from Publisher where publisher_name=?";
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(read);
			ps.setString(1,pub);
			ResultSet result= ps.executeQuery();
			
			if(result.next()) {
				int publisherId = result.getInt("id");
				return publisherId;
			}
		}catch(Exception e) {
			logger.error("Error: Read");
			e.printStackTrace();
		}		
		return 0;
	}
	
	/**
	 * Checks the current timestamp and the one in the database for optimistic locking. 
	 * @param Date the date being evaluated. 
	 * @param isbn the isbn number of the book.
	 * @return A boolean value to determine if the book has been changed.
	 */
	public boolean checkTimeStamp(String Date,String isbn) {
		try {
			String read = "SELECT last_modified FROM Books Where Isbn=?";		
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(read);
			ps.setString(1, isbn);
			ResultSet result = ps.executeQuery();
			result.next();
			LocalDateTime r = result.getTimestamp("last_modified").toLocalDateTime();
			
			
			
			//System.out.println("Date: " + r);
			//System.out.println("Date: " + Date);
			// need to read one element... 
			// if they match return true... FIXED IT!! BOOM!!
			System.out.println("Book Date is: " + Date);
			System.out.println("Database rea: " + r);
			if(Date.equals(r+"")) {
				return true;
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
	public void updateOneCol(String col, String unique, String ISBN) {
		try {
			String update = "UPDATE Books SET " + col + "=? WHERE Isbn=?";
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(update);
			ps.setString(1, unique);
			ps.setString(2, ISBN);
			ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *  Updates A row of the table. 
	 * @param title - The Title of the book being Updated. 
	 * @param author - The Author of the book being updated.
	 * @param gen - The Genre of the Book being updated. 
	 * @param sum - The Summary of the Book being updated. 
	 * @param dop - The Date of Publication of the Book being updated.
	 * @param isbn - The ISBN of the Book being updated. 
	 */
	public void update(String table,String title,String author, String gen,String sum,String dop, String isbn, int publisherint,int mainIsbn) { // update with Publisher ID.. 
		try {
			
			String update = "UPDATE Books SET Title=?, Author=?, Genre=?, Summary=?, Dop=?,Isbn=?,publisher_id = ? Where Isbn=?";
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(update);
			ps.setString(1, title);
			ps.setString(2, author);
			ps.setString(3, gen);
			ps.setString(4, sum);
			ps.setString(5, dop);
			ps.setString(6, isbn);
			ps.setInt(7, publisherint);
			ps.setString(8, mainIsbn+"");
			ps.executeUpdate();
			
		}catch(Exception e) {
			logger.error("Error: Update");
			e.printStackTrace();
		}
	}
	
	/**
	 * Deletes a Row from the online Sql Server 
	 * @param table The Name of the Table being updated.
	 * @param isbn The ISBN number of the book being Updated.
	 */
	public void delete(String isbn) {
		try {
			String delete = "DELETE FROM Books WHERE Isbn='?'";
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(delete);
			ps.setString(1, isbn);
			logger.info(ps);
			ps.executeUpdate();
			
		}catch(Exception e) {
			logger.error("Error: Delete");
			e.printStackTrace();
		}
	}
	
	/*
	 * Function Returns A list of books.
	 * @Returns Returns a list of books being read.
	 */
	public ArrayList<Book> getListOfDatabaseBook(){
		return read("Books");
	}
	

	//------------------------------------------Authors--------------------------------------------
	
	
	public ArrayList<Author> getAllAuthor(){
		try {
			ArrayList<Author> list = new ArrayList<Author>();
			String read = "SELECT * FROM AuthorsId";
			PreparedStatement ps = (PreparedStatement) this.connection.prepareStatement(read);
			ResultSet result = ps.executeQuery();
			
			while(result.next()) {
				list.add(new Author(result.getInt("id"), result.getString("first_name"),result.getString("last_name"),
									result.getDate("dob").toLocalDate(), result.getString("gender"),result.getString("web_site")));
			}
			return list;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void updateAuthorBook(String author, double percent, int bookID) { // update with Publisher ID.. 
		try {
			
			String update = "UPDATE author_book SET author_id = ?,book_id = ?,royalty = ?";
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(update);
			ArrayList<Author> list = getAllAuthor();
			int aInd = 0;
			
			for(int index = 0; index < list.size(); index++) {
				if(author.equals(list.get(index).getFirstName() + " " + list.get(index).getLastName())) {
					aInd = list.get(index).getId();
				}
			}
			
			ps.setInt(1, aInd);
			ps.setInt(2, bookID);
			ps.setDouble(3, (percent/10000) );
			ps.executeUpdate();
			
		}catch(Exception e) {
			logger.error("Error: Update");
			e.printStackTrace();
		}
	}
	
	
	public Author getAuthor(int id) {
		try {
			System.out.println("XID: " + id);
			String read = "Select * FROM AuthorsId WHERE id=?";
			PreparedStatement ps = (PreparedStatement) this.connection.prepareStatement(read);
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();
			Author ar = null;
			if(result.next()) {
				ar = new Author(result.getInt("id"), result.getString("first_name"),result.getString("last_name"),
									result.getDate("dob").toLocalDate(), result.getString("gender"),result.getString("web_site"));
			}
			return ar;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Book getBook(int id) {
		try {
			String read = "Select * FROM Books WHERE ID=?";
			PreparedStatement ps = (PreparedStatement) this.connection.prepareStatement(read);
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();
			
			Book ar = null;
			while(result.next()) {
				ar = new Book(result.getString("Title"), Integer.parseInt(result.getString("Isbn")),
						   result.getString("Author"), result.getString("Dop"),
						   result.getString("Genre"), result.getString("Summary"), Integer.parseInt(result.getString("publisher_id")), result.getInt("ID"));
		}
			
			return ar;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public ArrayList<AuthorBook> getAuthorBook(int bookID){
		try {
			ArrayList<AuthorBook> listAB = new ArrayList<AuthorBook>();
			String read = "Select * FROM author_book WHERE book_id = ?";
			PreparedStatement ps = (PreparedStatement) this.connection.prepareStatement(read);
			
			ps.setInt(1, bookID);
			ResultSet result = ps.executeQuery();
		
		
			while(result.next()) {
				listAB.add(new AuthorBook( (getAuthor(result.getInt("author_id"))) , (getBook(result.getInt("book_id"))) , result.getDouble("royalty")));
			}
			return listAB;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	public void CreateAuthorBook(String author, double percent, int bookID) {
		try {
			String create = "Insert INTO author_book (author_id,book_id,royalty) VALUES (?,?,?)";
			PreparedStatement query = (PreparedStatement) this.connection.prepareStatement(create);
			ArrayList<Author> list = getAllAuthor();
			int aInd = 0;
			
			for(int index = 0; index < list.size(); index++) {
				if(author.equals(list.get(index).getFirstName() + " " + list.get(index).getLastName())) {
					aInd = list.get(index).getId();
				}
			}
			
			query.setInt(1, aInd);
			query.setInt(2, bookID);
			query.setDouble(3, (percent/10000) );
			query.executeUpdate();
			
			
			
			
			
		}catch(Exception e) {
			
		}
		
		
	}
	
	// Return author stuff;
	
	public boolean isAuthorBook(String author, int bookID){
		try {
			
			String read = "Select * FROM author_book WHERE author_id = ? AND book_id = ?";
			PreparedStatement ps = (PreparedStatement) this.connection.prepareStatement(read);
			ArrayList<Author> list = getAllAuthor();
			int aInd = 0;
			
			for(int index = 0; index < list.size(); index++) {
				if(author.equals(list.get(index).getFirstName() + " " + list.get(index).getLastName())) {
					aInd = list.get(index).getId();
				}
			}
			ps.setInt(1, aInd);
			ps.setInt(2, bookID);
			ResultSet result = ps.executeQuery();
			if(result.next()) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Author getMatchingAuthor(String name) {
		try {
			ArrayList<Author> authList = getAllAuthor();
			//System.out.println("name: " + name + "\nMatching: "+ authList.get(0).getFirstName()+ " " + authList.get(0).getLastName());
			//System.out.println();
			for(int index=0; index < authList.size(); index++) {
				if(name.equals(String.valueOf(authList.get(index).getFirstName()+ " " + authList.get(index).getLastName()))){
					return authList.get(index);
				}
			}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void deleteAuthorBook(String author, int bookID) {
		try {
			String delete = "DELETE FROM author_book WHERE author_id=? AND book_id=?";
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(delete);
			ArrayList<Author> list =  getAllAuthor();
			int aInd = 0;
			
			for(int index = 0; index < list.size(); index++) {
				if(author.equals(list.get(index).getFirstName() + " " + list.get(index).getLastName())) {
					aInd = list.get(index).getId();
				}
			}
			
			ps.setInt(1, aInd);
			ps.setInt(2, bookID);
			ps.executeUpdate();
			
		}catch(Exception e) {
			logger.error("Error: Delete");
			e.printStackTrace();
		}
	}
	
	// need one for specific authors and books to get royalties.. 
	
	
	
	
	
	//--------------------------------------Author Ends-----------------------------------------------
	
	
	
	
	
	
	
	
	/**
	 * Stops the database Connection. 
	 */
	public void stopConnection() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			logger.error("Error: In Closing SQL Connection...");
			e.printStackTrace();
		}
	}
	
}
