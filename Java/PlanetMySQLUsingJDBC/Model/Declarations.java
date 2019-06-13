package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Declarations {
	public static int lenOfSID = 6;
	public static int LenOfFID = 7;
	public enum categoryBio{NULL,Frozen,Artic,Tundra,Taiga,Coniferous,Decidous,Mediterranean,Forest,Desert,Scorching,Lava};
	public enum categoryHumidity{NULL,Dry, Moist,Wet,Rain,Desert,DryScrub,Steppe,Savanna,TrueGrassLand,Chaparral,Frozen,Artic,Tundra,Scorching,Lava};
	public enum categoryRegion{NULL, absZero, Polar,Subpolar,Temperate,Tropical,Torrid,absHot};
	public enum categoryHumiditySubclass{Anomaly,NULL,SuperArid, Perarid, Arid, SemiArid, SubHumid, Humid, PerHumid, SuperHumid};
	public static String chosenOne = "";
	public static char color = ' ';
	// prototype for users... 
	public enum categoryUser{NULL, Student, Teacher,Admin,NotAdmin};
	
	
	public static User currentUser = new User();
	// prototype for life on planets... 
	public enum ToLifeOrNotToLife{Life, NoLife};
	
	
	public static categoryHumidity listOfHumidity[][] = new categoryHumidity[][] {
		{categoryHumidity.Dry,categoryHumidity.Dry,categoryHumidity.Moist,categoryHumidity.Moist,categoryHumidity.Wet,categoryHumidity.Wet,categoryHumidity.Rain,categoryHumidity.Rain},
		{categoryHumidity.Desert,categoryHumidity.DryScrub,categoryHumidity.Moist,categoryHumidity.Moist,categoryHumidity.Wet,categoryHumidity.Wet,categoryHumidity.Rain,categoryHumidity.Rain},
		{categoryHumidity.Desert,categoryHumidity.Chaparral,categoryHumidity.Steppe,categoryHumidity.Moist,categoryHumidity.Wet,categoryHumidity.Wet,categoryHumidity.Rain,categoryHumidity.Rain},
		{categoryHumidity.Desert,categoryHumidity.Chaparral,categoryHumidity.TrueGrassLand,categoryHumidity.Dry,categoryHumidity.Moist,categoryHumidity.Wet,categoryHumidity.Rain,categoryHumidity.Rain},
		{categoryHumidity.Desert,categoryHumidity.Chaparral,categoryHumidity.TrueGrassLand,categoryHumidity.Savanna,categoryHumidity.Dry,categoryHumidity.Moist,categoryHumidity.Wet,categoryHumidity.Rain}
	};

	
	// Create Hashmap for Class of Star...  //(Key, Data)
	//public static HashMap<String, String> starClass = new HashMap<String,String>();
	
	// to be used onces to generate UserID.. 
	public static final Logger logger = LogManager.getLogger();
	
	
	public static String getColorForLauren(){
		switch((""+color).toUpperCase().charAt(0)) {
			case 'M':
				return "Red";
			case 'K':
				return "Orange";
			case 'G':
				return "Yellow";
			case 'F':
				return "Light Yellow";
			case 'A':
				return "White";
			case 'B':
				return "Blue";
			default:
				return "Purple";
		}
	}
	
	
	public static double getSunRadiusForLauren() {
		
		switch((""+color).toUpperCase().charAt(0)) {
			case 'M':
				return 0.3;
			case 'K':
				return 0.8;
			case 'G':
				return 1.0;
			case 'F':
				return 1.3;
			case 'A':
				return 1.7;
			case 'B':
				return 5.0;
			case 'O':
				return 10.0;
			default:
				return 0.0;
		}
		
	}
	
	// returns a list of all composition elements...
	public static ArrayList<AtmosphereComposition> getListOfElements(String planetName,int type){
		int randMax = 99;
		ArrayList<AtmosphereComposition> acList = new ArrayList<AtmosphereComposition>();
		ArrayList<String> elements;
		if(type == 1) {	
			 elements = PlanetGateway.getInstance().getListOfElements(1);
		}else {
			elements = PlanetGateway.getInstance().getListOfElements(2);
		}
		
		
		
		
		Collections.shuffle(elements);
		
		int i = 0;
		while(randMax > 10) {
			double per = (double) Math.random()* randMax +1;
			if(!elements.get(i).equals("xxx")) {
				acList.add(new AtmosphereComposition(planetName, elements.get(i),per));
				//elements.remove(0);
				randMax -= per;
				i++;
				if(randMax < 0) {
					randMax = 0;
				}
			}else {
				i++;
			}
		}
		if(elements.get(i).equals("xxx")) {
			i++;
		}
		double left = 0;
		//System.out.println("Size is: " + acList.size());
		for(int index = 0; index < acList.size(); index++) {
			left += acList.get(index).getPercentage();
			//System.out.println("Left in Loop: "+ left);
		}
		System.out.println("Left is: "+ left);
		double per = (100 - left);
		
		acList.add(new AtmosphereComposition(planetName, elements.get(i), per));
		return acList;
	}
	
	
	public static String generateUserID(int type) {
		String userID = "";
		int let = 0;
		int num = 0;
		int size = 0;
		String tableName = "";
		String colName = "";
		//Do while(userId !=  functionListArrayListOfUserID) // runs at least once
		do{
		// Number random generator for numbers and letters.. use ascii 
		 // caps A-Z 65 -90 
		 /// lower case is 97-122..
			if(type == 0) { // faculty
				size = LenOfFID;
				tableName = "dbFaculty";
				colName = "Faculty_ID";
			}else {//student
				size = lenOfSID;
				tableName = "dbStudent";
				colName = "Student_ID";
			}
		for(int i = 0; i < size; i++) {
			//new ID everytime.. 
			if(i < 3) { // abc
				let = (int)(Math.random() * 25)+65; // 65-90
				userID += (char)let;
			}else { // 123
				num = (int)(Math.random() *10)+1; // 1-10
				userID += num;
			}
		}
		System.out.println("UserID: " + userID);
		}while(PlanetGateway.getInstance().getUserID(colName, tableName).contains(userID));
		
		// got letter and num in there proper range...
		// create user id based on certain pattern... "abc123" || "123abc" || "a1b2c3" || just whatever comes out.
		
		return userID;
	}

}
