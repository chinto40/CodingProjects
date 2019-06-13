package Models;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;

import Gateway.BookGateway;

/**
 * Book Objects that holds all the Data to represent a Book. 
 * @author Jacinto Molina
 *
 */
public class Book {	
	/**
	 * Variable member data of this object consisting of a Title, ISBN,Author,DOP,Genre,and the Book's Summary.
	 */
	private String bookTitle;
	private int bookIsbn;
	private String author;
	private ArrayList<Author> authorList;
	private String dateOfPublication;
	private String genre;
	private String summary;
	private LocalDateTime timeStamp; // each book has a time stamp and will only be changed if the timestamp read in match the other one... 
	private int PublisherID;
	private int bookId;
	private ArrayList<String> authList;
	
	
	/**
	 * Constructs a new Book Object. 
	 * @param title - The Books Title.
	 * @param isbn - This books ISBN
	 * @param aut- This books Author.
	 * @param DOP - This books Date of Publication.
	 * @param gen - This books genre.
	 * @param sum - This book's summary.
	 */
	public Book(String title, int isbn, String aut, String DOP, String gen, String sum,int pubID,int bookid) {
		this.bookIsbn = isbn;
		this.bookTitle = title;
		this.author = aut;
		this.dateOfPublication = DOP;
		this.genre = gen;
		this.summary = sum;
		this.timeStamp = LocalDateTime.now();
		this.PublisherID = pubID;
		this.bookId = bookid;
		this.authList = parseAuthorList();
		this.authorList = new ArrayList<Author>();
		this.setUpAuthor();
		
	}
	
	
	
	
	/**
	 * Constructs a default Book Object. 
	 */
	public Book() {
		this.bookTitle = "xx";
		this.bookIsbn = 0;
		this.author = "xx";
		this.dateOfPublication = "xx";
		this.genre = "xx";
		this.summary = "xx";
		this.timeStamp = LocalDateTime.now();
		this.PublisherID = 0;
		this.bookId = -1;
		this.authList = new ArrayList<String>();
	}
	
	public void setUpAuthor() {
		String token[] = this.author.split(",");
		//System.out.println(token[0]);
		for(int index = 0; index < token.length; index++) {
			this.authorList.add(BookGateway.getInstance().getMatchingAuthor(token[index]));
		}
	}
	
	
	public ArrayList<Author> getAllAuthor(){
		return this.authorList;
	}
		
	public ArrayList<String> parseAuthorList(){
		ArrayList<String> parsedList = new ArrayList<String>();
		String list[] = this.author.split(",");
		
		for(int index = 0; index < list.length; index++) {
			parsedList.add(list[index]);
		}
		return parsedList;
	}
	
	public ArrayList<String> getAuthorList(){
		return this.authList;
	}
	
	
	public void setAuthorList(ArrayList<String> newList) {
		this.authList = newList;
	}
	
	public void UpdateAuthorList(int selection ,String Authors) {
		if(selection == 0) {
			// add
			this.author = Authors;
			this.authList = new ArrayList<String>();
			String list[] = this.author.split(",");
			
			for(int index = 0; index < list.length; index++) {
				this.authList.add(list[index]);
			}
		}else {

			this.author = "";
			if(authList.size() > 1) {
				for(int index = 0; index < this.authList.size(); index++) {
					if(!(Authors.equals(this.authList.get(index)))){
						//this.authList.add(list[index]);
						this.author += this.authList.get(index);
						this.author += ",";
						//this.author += this.authList.get(index) + ",";
					}
				}
			}
			// cannot be null. 
			//this.author.charAt(this.author.length()-1);
			this.author = this.author.substring(0, this.author.length()-1);
			
			this.authList = parseAuthorList();
		}
	}
	
	
	/***
	 * Returns this books summary.
	 * @return a string variable representation of this book summary.
	 */
	public String getSummary() {
		int maxLen = 40;
		int counter = 0;
		String formattedSummary = "";
		//return this.summary;
		return this.summary;

	}

	/**
	 * Return the book Id for this object. 
	 * @return the book ID.
	 */
	public int getBookID() {
		return this.bookId;
	}
	/**
	 * Sets the TimeStamp of the book object. 
	 * @param date
	 */
	public void setTimestamp(LocalDateTime date) {
		if(date != null) {
			this.timeStamp = date;
		}
	}
	
	/*
	 * Returns the this book object ID. 
	 */
	public int getPublisherID() {
		return this.PublisherID;
	}
	
	/**
	 * Returns the timestamp of the Book. 
	 * @return the timestamp of this book object. 
	 */
	public LocalDateTime getTimeStamp() {
		return this.timeStamp;
	}
	
	
	/**
	 * Sets this book summary.
	 * @param sum the new summary being passed. 
	 */
	public void setSummary(String sum) {
		this.summary = sum;
		
	}
	
	/**
	 * Returns The book's Title.
	 * @return A string representation of the Book's Title.
	 */
	public String getBookTitle() {
		return bookTitle;
	}

	/**
	 * Sets this book Title.
	 * @param sum the new Title being passed. 
	 */
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	/**
	 * Returns The book's ISBN Number.
	 * @return A string representation of the Book's ISBN Number.
	 */
	public int getBookIsbn() {
		return this.bookIsbn;
	}

	/**
	 * Sets this book ISBN NUmber.
	 * @param sum the new ISBN Number being passed. 
	 */
	public void setBookIsbn(int bookIsbn) {
		this.bookIsbn = bookIsbn;
	}

	/**
	 * Returns The book's Author.
	 * @return A string representation of the Book's Author.
	 */
	public String getAuthor() {
		return this.author;
	}
	/**
	 * Sets this book Author.
	 * @param sum the new Author being passed. 
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Returns The book's DOP.
	 * @return A string representation of the Book's DOP.
	 */
	public String getDateOfPublication() {
		return dateOfPublication;
	}

	/**
	 * Sets this book DOP.
	 * @param sum the new DOP being passed. 
	 */
	public void setDateOfPublication(String dop) {
		this.dateOfPublication = dop;
	}

	/**
	 * Returns The book's genre.
	 * @return A string representation of the Book's genre.
	 */
	public String getGenre() {
		return this.genre;
	}

	/**
	 * Sets this book Genre.
	 * @param sum the new genre being passed. 
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	/**
	 * A visual string representation of this object. 
	 */
	public String toString() {
		String dash = "\n-----------------------------------------------------------------------------------------";
		return "\"" +this.bookTitle + "\",\n " +this.author + ", "+ this.dateOfPublication + "\nISBN: " + this.bookIsbn + dash;
		
	}
}