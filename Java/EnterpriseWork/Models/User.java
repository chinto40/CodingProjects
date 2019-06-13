package Models;

import Gateway.BookGateway;

public class User {
	
	private String name;
	private String Role;
	private int acessLevel;
	
	
	public User(String n, String r) {
		this.name = n;
		this.Role = r;
		this.acessLevel = setRole();
		BookGateway.AccessLevel = this.acessLevel;
	}
	
	public int setRole() {
		if(this.Role.equals("Administrator")) {
			return 3;
		}else if(this.Role.equals("Data Entry")) {
			return 2;
		}else if(this.Role.equals("Intern")) {
			return 1;
		}
		return 0;
	}
	
	public String getFirstName() {
		String[] token = this.name.split(" ");
		return token[0];
	}
	
	
	public String getRole() {
		return this.Role;
	}
	
	public void setName(String n) {
		this.name = n;
	}
	
	public String getName() {
		return this.name;
	}
	
}
