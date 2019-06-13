package Model;

import java.math.BigInteger;

public class Physics {
	
	//private String name;
	private BigInteger mass; //---------------
	private String strMass;
	private double gravity;
	private float distance;
	private double velocity;
	private double radius;
	private BigInteger rotaionalDirection; //---------
	private double surfaceArea;
	private double axis; 
	
	

	public Physics(String MassMult, String MassExpo, double Gra, float Dis,
				double vel, double rad, double axi) {
		this.strMass = MassMult + "x" + MassExpo;
		this.gravity = Gra;
		this.distance = Dis;
		this.velocity = vel;
		this.radius = rad;
		this.axis = axi;
		
	}
	
	
	
	public Physics() {
		this.strMass = "Null";
		this.gravity = 0;
		this.distance = 0;
		this.velocity = 0;
		this.radius = 0;
		this.axis = 0;
		
	}
	
	public String getStrMass() {
		return strMass;
	}



	public void setStrMass(String strMass) {
		this.strMass = strMass;
	}



	public double getGravity() {
		return gravity;
	}



	public void setGravity(double gravity) {
		this.gravity = gravity;
	}



	public float getDistance() {
		return distance;
	}



	public void setDistance(float distance) {
		this.distance = distance;
	}



	public double getVelocity() {
		return velocity;
	}



	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}



	public double getRadius() {
		return radius;
	}



	public void setRadius(double radius) {
		this.radius = radius;
	}



	public BigInteger getRotaionalDirection() {
		return rotaionalDirection;
	}



	public void setRotaionalDirection(BigInteger rotaionalDirection) {
		this.rotaionalDirection = rotaionalDirection;
	}



	public double getAxis() {
		return axis;
	}



	public void setAxis(double axis) {
		this.axis = axis;
	}

		
	


	public String toString() {
		String objectPrint = "\nPhysics\n----------------------------\n";
		objectPrint += "-Planet Mass: " + this.strMass + "\n";
		objectPrint += "-Planet Gravity: " + this.gravity+ "\n";
		objectPrint += "-Planet Distance: " + this.distance+ "\n";
		objectPrint += "-Planet Velocity: " + this.velocity+ "\n";
		objectPrint += "-Planet Radius: " + this.radius+ "\n";
		objectPrint += "-Planet Axis: " + this.axis+ "\n";
		return objectPrint;
	}
	
	
	

}
