package Models;

public class AuthorBook {

	private Author author;
	private Book book;
	private double royalty;
	private boolean newRecord;
	
	public AuthorBook(Author aut, Book bo, double roy) { // database
		this.author = aut;
		this.book = bo;
		this.royalty= (double)(roy * 10000);
		this.newRecord = false;
	}
	
	public AuthorBook() {
		this.author = new Author();
		this.book = new Book();
		this.royalty = 0;
		this.newRecord = true;
	}
	
	public Author getAuthor() {
		return this.author;
		
	}
	
	public void setAuthor(Author a) {
		this.author = a;
	}
	
	public Book getBook() {
		return this.book;
	}
	
	public void setBook(Book b) {
		this.book = b;
	}
	
	public double getRoyalty() {
		return this.royalty;
	}
	
	public void setRoyalty(int percentage) {
		this.royalty = percentage;
	}
	
	public boolean getNewRecord() {
		return this.newRecord;
	}
	
	
}
