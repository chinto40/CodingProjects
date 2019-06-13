package Models;

import java.sql.Date;
import java.time.LocalDateTime;

/**
 * Constructs a new AuditTrailEntry model. 
 * @author Jacinto Molina
 *
 */
public class AuditTrailEntry {
	
	/*
	 * The Specific book being changed. 
	 */
	private int id;
	
	/*
	 * The TimeStamp of when the changed happened.
	 */
	private LocalDateTime dateAdded;
	
	/*
	 * The changes being made in string format. 
	 */
	private String msg;
	
	/**
	 * Constructs a new AuditTrailEntry Object. 
	 * @param ids the Book id being changed. 
	 * @param dateA The time the Book changed.
	 * @param me the message being changed. 
	 */
	public AuditTrailEntry(int ids, LocalDateTime dateA,String me) {
		this.id = ids;
		this.dateAdded = dateA;
		this.msg = me;
	}
	
	/**
	 * Return the ID of this object. 
	 * @return The Book id of this project. 
	 */
	public int getID() {
		return this.id;
	}
	
	/**
	 * Return the Time stamp of this object. 
	 * @return the TimeStamp of this object. 
	 */
	public LocalDateTime getTimeStamp() {
		return this.dateAdded;
	}
	
	/**
	 * Returns the msg of this object. 
	 * @return
	 */
	public String getMsg() {
		return this.msg;
	}
	
	/**
	 * To String representation of this model
	 */
	public String toString() {
		return "ID: " + this.id + "\nDate Added: "+ this.dateAdded + "\nChanges: " + this.msg
				+ "\n------------------------------------------------------------------------";
	}
	

}
