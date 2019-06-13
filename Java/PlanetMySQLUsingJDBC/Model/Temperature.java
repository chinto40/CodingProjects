package Model;

import Model.Declarations.*;

/**
 * Temperature of the Planet. 
 * @author Jacinto Molina
 *
 */
public class Temperature {
	
		private double temp; 
	
	public Temperature(int t) {
		this.temp = t;
	}
	
	public Temperature(double t) {
		this.temp = t;
	}
	
	public Temperature() {
		this.temp = 0.0;
	}
	
	public double getTemp() {
		return this.temp;
	}
	
	public void setTemp(double ET) {
		this.temp = ET;
	}
	
	private boolean isRange( double max, double min) {
		if(this.temp < max && this.temp >= min) {
			return true;
		}
		return false;
	}
	
	public int getTemperatureLevel() {		
		// range between 34.7 and 75.2 
		// range between subpolar and half of tropical 
		if(this.temp >= 34.7 && this.temp <= 168.2) {
			// return the element of the temp depending on if its 
			if( isRange(37.4,34.7)){// 0
				return 0;
			}else if(isRange(37.4,34.7)) { //
				return 1;
			}else if(isRange(42.8,37.4)) { //2
				return 2;
			}else if(isRange(53.6,42.8)) { //3
				return 3;
			}else if(isRange(75.2,53.5)) { //4
				return 4;
			}else if(isRange(168.2,75.2)) { //4
				return 4;
			}
			
		}
		
		
		return -1; // error chekcing...
	}
	
	public String toString() {
		return "Current Temperature: " +this.temp;
	}

}
