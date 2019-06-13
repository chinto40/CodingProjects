package Model;

public class Star {
	
	private String name;
	private String Category;
	private double luminosity;
	
	// TODO: 
	// Private Temperature...
	
	
	// specific constructor... 
	public Star(String n, String cat, double lum) {
		this.name = n;
		this.Category = cat;
		this.luminosity = lum;
		
	}
	
	// Default constructor... 
	public Star() {
		this.name = "Unclassified";
		this.Category = "V";
		this.luminosity = 1;
		}
	
	
	// Sets the temp based on the Luminosity... 
	public void setTemperature() {
		
		
		
	} 
	
	
	
	
	public String getName() {
		return this.name;
	}
	public String getCategory() {
		return this.Category;
	}
	public double getLuminosity() {
		return this.luminosity;
	}
	
	public void setName(String newName) {
		this.name = newName;
	}
	public void setCategory(String nCat) {
		this.Category = nCat;
		
	}
	public void setLuminosity(double nLum) {
		this.luminosity = nLum;
	}

}
