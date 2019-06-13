package Model;

import Model.Declarations.*;

//user - Teracher...
public class User {
	
	private Declarations.categoryUser myUserType = Declarations.categoryUser.NULL;
	private String Fname;
	private String Mname;
	private String Lname;
	private String userID;
	
	public User(String fName,String mInit,String lName,String id, categoryUser s) {
		this.Fname = fName;
		this.Mname = mInit;
		this.Lname = lName;
		this.userID = id;
		this.myUserType = s;
	}
	
	public User() {
		
		this.Fname = "xx";
		this.Mname = "xx";
		this.Mname = "xx";
		this.userID = "xx";
	}
	
	public Declarations.categoryUser getUserType(){
		return this.myUserType;
	}
	
	public Declarations.categoryUser setUserType(Declarations.categoryUser type){
		return this.myUserType = type;
	}
	
	
	public String getName() {
		return this.Fname + " " + this.Mname + " " + this.Lname;
	}
	
	public String getFirstName() {
		return this.Fname;
	}
	public String getLastName() {
		return this.Lname;
	}
	public String getMiddleName() {
		return this.Mname;
	}
	public void setFirstName(String newName) {
		this.Fname = newName;
	}
	public void setLastName(String newName) {
		this.Lname = newName;
	}
	public void setMiddleName(String newName) {
		this.Mname = newName;
	}
	public String getUserID() {
		return this.userID;
	}
	public void setUserID(String newID) {
		this.userID = newID;
	}
}
