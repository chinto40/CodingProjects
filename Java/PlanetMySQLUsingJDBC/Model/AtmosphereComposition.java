package Model;

public class AtmosphereComposition {
	
	private String name;
	private String ElementName;
	private double percentage;
	
	public AtmosphereComposition(String n,String En, double per) {
		this.name = n;
		this.ElementName = En;
		this.percentage = per;
	}
	
	public AtmosphereComposition(String En, double per) {
		this.name = "xx"; // changing this 
		this.ElementName = En;
		this.percentage = per;
	}
	
	public String getName() {
		return this.name;
	}
	public String getElementName() {
		return this.ElementName;
	}
	public double getPercentage() {
		return this.percentage;
	}
	
	public void setName(String n) {
		this.name = n;
	}
	public void setElementName(String En) {
		this.ElementName = En;
	}
	
	public void setPercentage(double per) {
		this.percentage = per;
	}
	
	public String toString() {
		return this.ElementName + ": " + this.percentage + "\n";
	}
}
