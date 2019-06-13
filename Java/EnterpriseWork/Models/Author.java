package Models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Author {
	
	private int id;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private String gender;
	private String website;
	
	public Author(int iD, String firstN, String lastN, LocalDate dob, String gen, String web) {
		this.id = iD;
		this.firstName = firstN;
		this.lastName = lastN;
		this.dateOfBirth = dob;
		this.gender = gen;
		this.website = web;
		
	}
	
	public Author() {
		this.id = 0;
		this.firstName = "xx";
		this.lastName = "xx";
		this.dateOfBirth = LocalDate.now();
		this.gender ="Unkown";
		this.website = null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public String getName() {
		return this.firstName + " " + this.lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
	
	public String toString() {
		return "Author Name: " + this.firstName +" " + this.lastName +
				"\nGender: " + this.gender +
				"\nDate of Birth: " + this.dateOfBirth +
				"\n--------------------------------------------------------------";
	}

}
