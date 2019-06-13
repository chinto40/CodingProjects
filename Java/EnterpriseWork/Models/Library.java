package Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Controller.MenuController;
import Gateway.BookGateway;

/**
 * Holds a list of Books Object in a library.
 * @author Jacinto Molina
 */
public class Library {
	
	/*
	 * The main Data structure for this class. Holds Book Object Found in the library.
	 */
	private ArrayList<Book> books;
	
	 /**
     * Logger for error printing to the console. 
     */
	private static Logger logger = LogManager.getLogger(MenuController.class);
	
	/*
	 * Constructs a new Library Object with predefined list of Books.
	 */
	public Library(ArrayList<Book> newListOfBooks) {
		this.books = newListOfBooks;
		
	}
	
	/*
	 * Constructs a default Library Object. 
	 */
	public Library() { 
		this.books = BookGateway.getInstance().read(0);
		System.out.println("Finished Reading!!");
	
	}
	
	/**
	 * Returns a List of Book Found in this library. 
	 * @return - A list of books
	 */
	public ArrayList<Book> getListOfBooks(){
		return this.books;
	}
	
	/**
	 * Resets the List of books found in the library. 
	 * @param newList - The list of books being passed in. 
	 */
	public void resetBookList(ArrayList<Book> newList) {
		this.books = newList;
	}
	
	/**
	 * Adder Function to add a single book to the list found in the library.
	 * @param b
	 */
	public void addBook(Book b) {
		this.books.add(b);
	}
	
	/**
	 * Removal function to remove a single book in this library list of books.  
	 * @param b - The book to die!!!
	 */
	public void removeBook(Book b) {
		this.books.remove(findBook(b));
	}

	/**
	 * Returns a specific book in the list of books of this library. 
	 * @param b The book we are looking for. 
	 * @return -  The book Found.
	 */
	public int findBook(Book b) {
		for(int index = 0; index < this.books.size(); index++) {
			if(this.books.get(index).toString().equals(b.toString())){
				return index;
			}
		}
		logger.error("Book Doesn't exsist in the list.");
		return -1;
	}
}
