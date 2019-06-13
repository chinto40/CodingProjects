package Model;

import java.awt.Point;
import java.math.BigInteger;
import java.util.ArrayList;

import Model.Declarations.*;
import processing.core.PImage;

public class Planet  {
	
	private Atmosphere atom;
	private String planetName;
	private PImage texture; // Drawing Image for wrapper class
	
	// TODO: Texture Wrap member variable.
	
	// Distance from the sun to tell if its in the goldie-lock Zone.. 
	private Point distanceFromthesun;
	private Physics planetPhysics;
	private double mass; // going to save the mass here from the DB. 
	private String zone;
	private String Category;
	
	private String color;
	
	public Planet(String name, double newMass,String zne, String catego,  double temp, int Hum, Physics ph) {
		//System.out.println("Name: " + name + " mass: " + newMass + " Temp: " + temp + " Hum: " + Hum);
		this.planetName = name;
		this.mass = newMass;
		this.zone = zne;
		this.Category = catego;
		
		
		this.atom = new Atmosphere(new Temperature(temp), new Humidity(Hum));
		//this.mass = new BigInteger(newMass);
		this.planetPhysics = ph;
		
		this.color = null;
		
		//String mass  = newMass;
		//System.out.println("After");
	}
	
	//TODO: Creatures...
	
	/*
	 * 
	 *
	 */
	public Planet() {
		
	}
	
	public String getPlanetColor() {
		this.color = setColor();
		return this.color;
	}
	
	//public enum categoryRegion{NULL, absZero, Polar,Subpolar,Temperate,Tropical,Torrid,absHot};
	public String setColor() {
		categoryBio n = this.atom.getProfile().getCategoryBio();
		switch (n) {
		case Frozen:
			return "Blue";
		case Artic:
			return "White";
		case Tundra:
			return "Blue";
		case Taiga:
			return "Blue";
		case Coniferous:
		case Decidous:
		case Mediterranean:
		case Forest:
			return "Green";
		case Desert:
			return "Yellow";
		case Scorching:
			String[] c = {"Red","Pink"};
			return c[((int)(Math.random()*2))];
		case Lava:
			return "Orange";
		default:
			return null;
		}
	}
	
	
	public Physics getPlanetPhysics() {
		return this.planetPhysics;
	}
	
	public Point getLocation() {
		return this.distanceFromthesun;
	}
	
	public String getName() {
		return this.planetName;
	}
	
	public String getZone() {
		return this.zone;
	}
	
	public String getCategory() {
		return this.Category;
	}
	
	public Atmosphere getAtom() {
		return this.atom;
	}
	public void setName(String newPlanetName) {
		this.planetName = newPlanetName;
	}
	
	public double landMass() {
		return this.mass;
	}
	
	public String printPlanetStuff() {
		String pbname = "\n************************************************\n";
		pbname += this.planetName + "\n*****************************************\n";
		pbname += "-Zone: " + this.zone +"\n";
		pbname += "-Category: "  + this.Category + "\n";
		pbname += "-LandMass: " + this.mass + "\n";
		
		return pbname;
		
	}
	
	public String toString() {
		return printPlanetStuff() + this.atom.toString() + this.planetPhysics.toString();
	}
}
