package Model;

import java.math.BigInteger;
import java.util.ArrayList;

import Model.Declarations.categoryBio;
import Model.Declarations.categoryHumidity;
import Model.Declarations.categoryRegion;

public class Atmosphere {
	//Going to determine the atmosphere of the planet. 
	private Profile profile;
	private boolean isSet;
	
	private final BigInteger absHot = new BigInteger("2500000000000");
	private final double absZero = -459.68;
	private double atmSpace;
	private double spaceLeft;
	
	
	private ArrayList<AtmosphereComposition> elements;
	
	/**
	 * Survivability - Artic -Desert Habitable, Sertile, Life
	 * Sertile  = Frozen, Scoorching, Lava
	 * Life = Artic - desert
	 * Habitable = Based on distance from the sun
	 */
	public Atmosphere(Temperature temp, Humidity hp) {
		
		this.isSet = true;
		this.profile = new Profile(temp,hp);
		this.profile.setTempProfile(temp);
		this.profile.setHumidProfile(hp);
		makeProfile();
		//System.out.println(toString());
		this.elements = new ArrayList<AtmosphereComposition>();
		this.atmSpace = 100.0;
		this.spaceLeft = this.atmSpace - 100.0;
	}
	
	public Atmosphere(int temp, int hum) {
		//fixer upper
		this.profile = new Profile();
	
		
	}
	
	public Atmosphere() {
		this.profile = new Profile();
		this.isSet = false;
	}
	
	private double combinedPerc() {
		double per = 0;
		for(int i = 0; i < this.elements.size(); i++) {
			per += this.elements.get(i).getPercentage();
		}
		return per;
	}
	
	public ArrayList<AtmosphereComposition> getAtmoshpereComposition(){
		return this.elements;
	}
	
	public void setAtmoshpereComposition(ArrayList<AtmosphereComposition> ele){
		this.elements = ele;
	}
	
	// adds an element to the List, if it is less than 100 and if the percent is less than space left.... 
	public void AddElement(String elementName, double percent) {
		
		this.atmSpace = combinedPerc();
		this.spaceLeft = this.atmSpace - 100;
		
		if(this.atmSpace < 100.0) { // check the percentage check
			//valid
			
			// then your able to add
			if(percent < this.spaceLeft) {
				// valid
				// add
				this.elements.add(new AtmosphereComposition(elementName, percent));
			}else {
				System.err.println("Error: percent is tooo Much!!");
			}
		}else {
			System.err.println("Error: Not enough Atmosheric space!!");
		}
		// before adding
	}
	
	public Profile getProfile() {
		return this.profile;
	}
	public void setProfile(Profile pr) {
		this.profile = pr;
	}
	private boolean isRange( Long max, int min) {
		if(this.profile.getTempProfile().getTemp() < max && this.profile.getTempProfile().getTemp() >= min) {
			return true;
		}
		return false;
	}
	
	private boolean isRange( double max, double min) {
		if(this.profile.getTempProfile().getTemp() < max && this.profile.getTempProfile().getTemp() >= min) {
			return true;
		}
		return false;
	}
	
	public void makeProfile() {
		//if(this.isSet == true) {
		/*
		//  x < -459.67  			**  abs zero
		// -459.67 < x < -126		** Frozen
		// -30 < x < 0
		// 0 < x < 34.7
		// 34.7 < x < 37.4
		// 37.4 < x < 42.8
		// 42.8 < x < 53.6
		// 53.6 < x < 75.2
		// 75.2 < x < 168.2
		// 168.2 < x < 202.3
		// 202.3 < x < 863.6
		// 863.6 < x < 2.55^1032
		 * 
		 * 
		 *  Fro - Frozen is -500 ~ -125
		 *  Arc - Arctic is -126 ~ -29
		 *  Tun - Tundra is -30 ~ 33
		 *  Tai - Taiga is 34 ~ 36
		 *	Con - Coniferous is 37 ~ 41
		 *	Dec - Deciduous is 42 ~ 52
		 *	Med - Mediterranean is 53 ~ 74
		 *	For - Forest is 75 ~ 167
		 *	Des - Desert is 168 ~ 205
		 *	Scor - Scorching is 206 ~ 864
		 * 	Lav - Lava is 865 ~ 2.55 x 1032
		*/
			
		//TODO: Need to change temperature to Doubles, more accruate. 
		// absolute zero
		if(this.profile.getTempProfile().getTemp() < this.absZero ) { 
			//System.out.println(categoryBio.Fro);
			//System.out.println(categoryRegion.absZero);
			// here we have the Temperature and can use this to determine the range of the 2d array;
			this.profile.setCategoryBio(categoryBio.Frozen);
			this.profile.setCategoryRegion(categoryRegion.absZero);
			
		
		}else if(isRange(-125,-459.67)) { // Frozen- Polar
			//System.out.println(categoryBio.Fro);
			//System.out.println(categoryRegion.Polar);
			this.profile.setCategoryBio(categoryBio.Frozen);
			this.profile.setCategoryRegion(categoryRegion.Polar);
			
			
		}else if(isRange(-31,-126)) { // Artic
			//System.out.println(categoryBio.Arc);
			//System.out.println(categoryRegion.Polar);
			this.profile.setCategoryBio(categoryBio.Artic);
			this.profile.setCategoryRegion(categoryRegion.Polar);
			
		}else if(isRange(0,-30)) {
			//System.out.println(categoryBio.Tun); // Tundra -- Sub polar or Polar
			//System.out.println(categoryRegion.Subpolar);
			//System.out.println(categoryRegion.Polar);
			this.profile.setCategoryBio(categoryBio.Tundra);
			this.profile.setCategoryRegion(categoryRegion.Polar);
			
			
		}else if(isRange(34.7,0)) { // Taiga -- Temperate
			//System.out.println(categoryBio.Tai);
			//System.out.println(categoryRegion.Subpolar);
			this.profile.setCategoryBio(categoryBio.Taiga);
			this.profile.setCategoryRegion(categoryRegion.Subpolar);
			
			
		}else if(isRange(37.4,34.8)) { // 
			//System.out.println(categoryBio.Con);
			//System.out.println(categoryRegion.Temperate);
			this.profile.setCategoryBio(categoryBio.Coniferous);
			this.profile.setCategoryRegion(categoryRegion.Temperate);
			
			
			
		}else if(isRange(42.8,37.5)) {
			//System.out.println(categoryBio.Dec);
			//System.out.println(categoryRegion.Temperate);
			//System.out.println(categoryRegion.Tropical);
			this.profile.setCategoryBio(categoryBio.Decidous);
			this.profile.setCategoryRegion(categoryRegion.Temperate);
			
		}else if(isRange(53.6,42.9)) {
			this.profile.setCategoryBio(categoryBio.Mediterranean);
			this.profile.setCategoryRegion(categoryRegion.Tropical);
			
			
		}else if(isRange(75.2,53.7)) {
			//System.out.println(categoryBio.For);
			//System.out.println(categoryRegion.Tropical);
			this.profile.setCategoryBio(categoryBio.Forest);
			this.profile.setCategoryRegion(categoryRegion.Tropical);
			
		}else if(isRange(202.3,75.3)) {
			//System.out.println(categoryBio.Des);
			//System.out.println(categoryRegion.Tropical);
			//System.out.println(categoryRegion.absZero);
			this.profile.setCategoryBio(categoryBio.Desert);
			this.profile.setCategoryRegion(categoryRegion.Tropical);
			
		}else if(isRange(864,202.4)) {
			//System.out.println(categoryBio.Scor);
			//System.out.println(categoryRegion.Torrid);
			this.profile.setCategoryBio(categoryBio.Scorching);
			this.profile.setCategoryRegion(categoryRegion.Torrid);
			
		}else if(isRange(this.absHot.intValue(),864.1)) {
			//System.out.println(categoryBio.Lav);
			//System.out.println(categoryRegion.absHot);
			this.profile.setCategoryBio(categoryBio.Lava);
			this.profile.setCategoryRegion(categoryRegion.absHot);
		}else {
			Declarations.logger.error("\n >:| , Variables aren't set yet. \n");
		}
		
		this.profile.setCategoryHumidity(getEnumHumidType());
		//}
		
	}
	
	
	
	public categoryHumidity getEnumHumidType() {
		
		// here would need to check the range of the temperature to 
		// make sure we are in the 2d array range...
		// we have 3 for Freezing and 3 types of Hotness.
		// everything in the middle is the 2d array that we are going to use.
		if(this.profile.getTempProfile().getTemp() < 34.7) {
			if(isRange(-30,-126)) {
				return categoryHumidity.Artic;
			}else if(isRange(34.6,-30)) {
				return categoryHumidity.Tundra;
			}else {
				return categoryHumidity.Frozen;
			}
		}else if(this.profile.getTempProfile().getTemp() >= 168.2) {
			if(isRange(203,168.2)) {
				return categoryHumidity.Desert;
			}else if(isRange(863.6,203)) {
				return categoryHumidity.Scorching;
			}else {
				return categoryHumidity.Lava;
			}
		}else {
			return Declarations.listOfHumidity[this.profile.getTempProfile().getTemperatureLevel()][this.profile.getHumidProfile().humidLevel()-1];
		}		
	}
	public String printElements() {
		String ele = "\n"
				+ "Elements:\n-----------------------------------\n";
		for(int index = 0; index < this.elements.size(); index++) {
			ele += "-"+this.elements.get(index).toString();
		}
		return ele;
	}
	
	public String toString() {
		return this.profile.toString() + "\n" + printElements();
		}
}
