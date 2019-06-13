package Model;

import Model.Declarations.categoryHumidity;
import Model.Declarations.categoryHumiditySubclass;

public class Humidity {
	
	private int humidLevel;
	private int humidityElementNumber;
	private categoryHumiditySubclass  subclass;
	private categoryHumidity catHum;
	private boolean isSet = false;
	
	/**
	 * Subnames
	 * Super Arid, Perarid, Arid, Semi-arid, SubHumid, Humid, Perhumid, SuperHumid
	 * Ranges 
	 * 0 -50 = Dry, Desert
	 * 50-100 = Moist, 
	 * 100-150 = Wet,
	 * 150-200 = Rain.
	 * 200-250 = Rain
	 */
	public Humidity(int range) {
		this.humidityElementNumber = 0;
		this.humidLevel = range;
		this.isSet = true;
		this.subclass = categoryHumiditySubclass.NULL;
		humidLevel();
	}

	public Humidity() {
		this.humidLevel = 0;
		this.humidityElementNumber = 0;
		this.subclass = categoryHumiditySubclass.NULL;
		humidLevel();
	}
	
	private boolean isRange( int max, int min) {
		if(this.humidLevel <= max && this.humidLevel >= min) {
			return true;
		}
		return false;
	}
	
	public int humidLevel() {
		if(isSet) {
			// Thinking of a 2d array...
			if(isRange(50,0)) {
				this.subclass = categoryHumiditySubclass.SuperArid;
				return 1;				
			}else if(isRange(100,50)) {
				this.subclass = categoryHumiditySubclass.Perarid;
				return 2;
			}else if(isRange(150,100)) {
				this.subclass = categoryHumiditySubclass.Arid;
				return 3;
			}else if(isRange(200,150)) {
				this.subclass = categoryHumiditySubclass.SemiArid;
				return 4;
			}else if(isRange(250,200)) {
				this.subclass = categoryHumiditySubclass.SubHumid;
				return 5;
			}else if(isRange(300,250)) {
				this.subclass = categoryHumiditySubclass.Humid;
				return 6;
			}else if(isRange(350,300)) {
				this.subclass = categoryHumiditySubclass.PerHumid;
				return 7;
			}else if(isRange(400,350)) {
				this.subclass = categoryHumiditySubclass.SuperHumid;
				return 8;
			}else {
				this.subclass = categoryHumiditySubclass.Anomaly;
			}
			return 0;
		}else {
			System.out.println(">: | Please set the values.");
		}
		System.out.println("Error: Shouldn't get here!!");
		return 0;
	}
	
	public categoryHumiditySubclass getHumidSubClass() {
		return this.subclass;
	}
	public void setHumidSubName(categoryHumiditySubclass newName) {
		this.subclass = newName;
	}
	
	// return the string of the humidity.
	public String toString() {
		return "Humidity: " + this.humidLevel + " SubName: " + this.subclass;
	}
	
	
}
