package Model;

import Model.Declarations.*;

public class Profile {
	private Temperature temp;
	private Humidity humid;
	private categoryHumidity enumHumidity;
	private categoryRegion enumRegion;
	private categoryBio enumBio;
	
	
	public Profile() {
		this.temp = new Temperature();
		this.humid = new Humidity();
		this.enumHumidity = categoryHumidity.NULL;
		this.enumRegion = categoryRegion.NULL;
		this.enumBio = categoryBio.NULL;
	}
	public Profile(Temperature te, Humidity hum) {
		this.temp = te;
		this.humid = hum;
		this.enumHumidity = categoryHumidity.NULL;
		this.enumRegion = categoryRegion.NULL;
		this.enumBio = categoryBio.NULL;
	}
	
	public Profile(Temperature temp,Humidity hum, categoryHumidity catHum, categoryRegion catReg, categoryBio catBio) {
		this.temp = temp;
		this.humid = hum;
		this.enumBio = catBio;
		this.enumHumidity = catHum;
		this.enumRegion = catReg;
	}
	
	public Temperature getTempProfile() {
		return this.temp;
	}
	
	public void setTempProfile(Temperature t) {
		this.temp = t;
	}
	
	public Humidity getHumidProfile() {
		return this.humid;
	}
	
	public void setHumidProfile(Humidity hum) {
		this.humid = hum;
	}
	
	public categoryHumidity getCategoryHumidity() {
		return this.enumHumidity;
	}
	
	public void setCategoryHumidity(categoryHumidity can) {
		this.enumHumidity = can;
	}
	
	public categoryRegion getCategoryRegion() {
		return this.enumRegion;
	}
	
	public void setCategoryRegion(categoryRegion cat) {
		this.enumRegion = cat;
	}
	
	public categoryBio getCategoryBio() {
		return this.enumBio;
	}
	public void setCategoryBio(categoryBio cat) {
		this.enumBio = cat;
	}
	
	//TODO: make toString in the profile. 

	public String toString() {
		String before = "\n Planet Profile\n---------------------------------------------\n";
				before += "-Bio: " + this.enumBio + "\n";
				before += "-Region: " + this.enumRegion + "\n";
				before += "-Humidity: " + this.enumHumidity + "\n";
				before +="-"+ this.temp.toString() +"\n";
				before += "-" +this.humid.toString() + "\n";
				return before;
	}
}
