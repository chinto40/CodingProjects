package Model;

import java.util.ArrayList;

public class SolarSystem {
	
	// Has Sun
	 private ArrayList<Planet> allPlanets;
	 
	 
	 public SolarSystem() {
		 this.allPlanets = new ArrayList<Planet>();
	 }
	 
	 
	 public void addPlanet(Planet Nibiru) {
		 this.allPlanets.add(Nibiru);
		 }
	 
	 public void removePlanet(String planetName) {
		 for(int i = 0; i < this.allPlanets.size(); i++) {
			 if(this.allPlanets.get(i).getName() == planetName) {
				 this.allPlanets.remove(i);
			 }
		 }
	 }
	 
	 
	 
	 public void populateTheSolarSystem() {
		 try {
			 this.allPlanets = PlanetGateway.getInstance().readAll("dbPlanet");
			// System.out.println("inside Pop: " + this.allPlanets.size());
			 if(this.allPlanets == null) {
				 Declarations.logger.error("Error: List of read All failed to populate...");
				// System.exit(0);
			 }
		 }catch(Exception e) {
			 Declarations.logger.info("Error: In Population...");
			 e.printStackTrace();
		 }
	
	 }
	 
	 public String toString() {
		 String  visualRepresentation = "\n---------------------------------------------\n";
		 
		 for(int index = 0; index < this.allPlanets.size(); index++) {
			 visualRepresentation += this.allPlanets.get(index).getAtom().toString();
			 visualRepresentation += "\n---------------------------------------------\n";
		 }
		 return  visualRepresentation;
	 }

}
