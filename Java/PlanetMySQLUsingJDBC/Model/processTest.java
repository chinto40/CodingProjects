package Model;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

import Model.Declarations.categoryHumidity;
//import processing.core.PApplet;
public class processTest {//extends PApplet{
	
	/*public void setting() {
			size(1000,1000);
	}
	
	public void draw() {
		background(64);
		ellipse(mouseX, mouseY, 20,20);
		//ellipse(0,0,20,20);
	}*/
	
	
	
	public static void main(String[] arg) {
		//String[] appletArgs = new String[] {"My Sketch"};
		//processTest mySketch = new processTest();
		//PApplet.runSketch(appletArgs, mySketch);
		
		//BigInteger absHot = new BigInteger("2500000000000");
		//System.out.println( absHot.longValue() );
		
		//Atmosphere atom = new Atmosphere(new Temperature(863.7),new Humidity(500));
		
		
		//SolarSystem milkyWay = new SolarSystem();
		//milkyWay.populateTheSolarSystem();
		//System.out.println(milkyWay.toString());
		
		// Testing for User ID works... 
		/*for(int index = 0; index < 20; index++) {
			int num = (int)(Math.random() *10)+1; // 1-10
			int let = (int)(Math.random() * 25)+65;
			System.out.println("Num is: " + num + "\nlet is: " + let);
		}*/
		
		//Planet earth = PlanetGateway.getInstance().readOnePlanet("Earth");
		
		
		
		//System.out.println(PlanetGateway.getInstance().getRSA(349152, 65129, 2612693));
	
		//System.out.print(PlanetGateway.getInstance().getRSA(9, 8, 1));
		//System.out.print(PlanetGateway.getInstance().getRSA(1, 7, 2));
		
		
		
		/*********************************************
		//PlanetGateway.getInstance().InsertNewPlanet("Vegeta", (float)15.1, (float)8.5, (int) 8, (float)3.5, (double)79.5, "6x20");
		
		/*PlanetGateway.getInstance().deletePlanetComposition("Vegeta");
		PlanetGateway.getInstance().deletePlanetPhysics("Vegeta");
		PlanetGateway.getInstance().deletePlanetPlanet("Vegeta");
		PlanetGateway.getInstance().deletePlanetPlantaryBody("Vegeta");
		*/
		/***********************************************************/
		for(int i = 0; i < 10; i++) {
			System.out.println((int) (Math.random() * 2));
			/*ArrayList<AtmosphereComposition> list = Declarations.getListOfElements("Vegeta");
			double max = 0;
			System.out.println("--------------------------------------------------------------------");
			for(int j = 0; j < list.size(); j++) {
				System.out.println(list.get(j).toString());
				max += list.get(j).getPercentage();*/
			}
	/*
			System.out.println("Max is: " + max);
			System.out.println("-------------------------------------------------------------------");
		}*/
	
	
		//System.out.println(earth.toString());
		
		//Planet mars = PlanetGateway.getInstance().readOnePlanet("Mars");
		
		//System.out.println(mars.toString());
		// Declarations.generateUserID();
		/*String id = "xxxx";
		int index = 0;
		do {
			
			if(index == 10) {
				id = "aaa002";
			}
			
			System.out.println("Index: " + index);
			index++;
		}while(!PlanetGateway.getInstance().getUserID().contains(id));
		
		System.out.println(PlanetGateway.getInstance().getUserID().contains("aaa001"));
		*/
		
		//PlanetGateway.getInstance().readAll("dbPlanet");
		
		//int f = (int) Math.floor((Math.random() < .5 ? -1 : 1));//tenary operator 
		//double g =  Math.floor((Math.random() *  f) + 1);
		///System.err.println(g);
		
		//atom.makeProfile();
		
		
	}
	
	
}
