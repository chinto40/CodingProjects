package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.j256.ormlite.stmt.PreparedQuery;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import Model.Declarations.*;


/**
 * --Planets
 * Radius 
 * distance from the star
 * color
 * orbital speed or velocity
 * 
 * -- Sun
 * # planets in solar system  -- list of planets.. 
 * 
 */

/**
 * Reading in a planet from the table we have set up. 
 * @author Jacinto  Molina
 *
 */
public class PlanetGateway {
	
	private static PlanetGateway instance;
	private MysqlDataSource db;
	private Connection conn;
	/*
	 * Logger error for Checking Log stuff.
	 */
	//private static final Logger logger = LogManager.getLogger();
	private PlanetGateway() {
		// getDatabase connection here..
		try {
			
			this.db = new MysqlDataSource();
			
			Declarations.logger.info("Connecting Database....");
			db.setURL("jdbc:mysql://easel2.fulgentcorp.com/xtd781");
			db.setUser("xtd781");
			db.setPassword("29IOaTqa45wBI3Ti07OL");
			this.conn = db.getConnection();
			
		}catch(Exception e) {
			e.printStackTrace();
			Declarations.logger.error("Error: Connecting to the Dataase....");
		}
	}


	// singlton pattern so we can only create on instance of this class. 
	public static PlanetGateway getInstance() {
		if(instance == null) {
			instance = new PlanetGateway();
		}
		return instance;
	}
		
		
	/*
	 * NOT VALID RIGHT NOW!!!
	 */
	public void Create(String table, String pname, String mass, String bioDiv, String temp, String reg, String hum) {
		try {
			
			String create = "Insert into " + table + "(Pname, Mass, Biodiversity,Temp,Region,Humidity)" +
					"VALUES (?,?, ?, ?, ?, ?)";
				PreparedStatement query = (PreparedStatement) this.conn.prepareStatement(create);
				query.setString(1, pname);
				query.setString(2, mass);
				query.setString(3, bioDiv);
				query.setString(4, temp);
				query.setString(5, reg);
				query.setString(6, hum);
				query.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
		
	/**
	 * Reads the planet of the name being passed in. 
	 * @param Pname The name of the planet. 
	 * @return Returns the planet object from the database being called. 
	 */
	public Planet readOnePlanet(String Pname){
		try {
			Planet onlyPlanet = null;
			String read = "SELECT * FROM dbPlanet WHERE dbPlanet.PBname='"+Pname+"'";
			PreparedStatement ps = (PreparedStatement)this.conn.prepareStatement(read);
			
			ResultSet result = ps.executeQuery();
			//result.next();
			if(result.next()) {
				//System.out.println(result.getString("PBname"));
			 onlyPlanet = (new Planet(result.getString("PBname"), result.getDouble("Landmass"), result.getString("Zone"), result.getString("Category"),
					getTemperatureForPlanet(result.getString("PBname")), result.getInt("Humidity"),
					getPhysicsForPlanet(result.getString("PBname"))));
			onlyPlanet.getAtom().setAtmoshpereComposition(getAtomCompForPlanet(result.getString("PBname")));
			}
			return onlyPlanet;
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		Declarations.logger.error("Error: Should never get here!!!!");
		return null;
	}
	
	
	/**
	 * Returves the specific Temperature of the planet in the database.. 
	 * @param pName is the name of the planet. 
	 * @return Returns the Temperature of the planet being selected. 
	 */
	public double getTemperatureForPlanet(String pName) {
		
		try {
			String read = "Select Temperature From dbPlanetary_Body WHERE PBname='" + pName+"'";
			PreparedStatement ps = (PreparedStatement)this.conn.prepareStatement(read);
			ResultSet result = ps.executeQuery();
			if(result.next()) {
				return result.getDouble("Temperature");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	/**
	 *  Returns a List of planets from the Database being called in.
	 * @param table The name of the table being retrieved.
	 * @return Returns A list of Planets from the database. 
	 */
	public ArrayList<Planet> readAll(String table){
		
	try {
		String read = "SELECT * FROM " + table;
		ArrayList<Planet> AP = new ArrayList<Planet>();
		PreparedStatement ps = (PreparedStatement)this.conn.prepareStatement(read);
		ResultSet result= ps.executeQuery();

		while(result.next()){ 
			//String name, String newMass, double temp, int Hum, Physics ph
			
			AP.add(new Planet(result.getString("PBname"), result.getDouble("Landmass"), result.getString("Zone"), result.getString("Category"),
					getTemperatureForPlanet(result.getString("PBName")), result.getInt("Humidity"), getPhysicsForPlanet(result.getString("PBname"))));
			AP.get(AP.size()-1).getAtom().setAtmoshpereComposition(getAtomCompForPlanet(result.getString("PBname")));
		}
		return AP;
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	return null;
	}
	//-------------------------Planet stuff -------------------------------
	
	
	
	public ArrayList<String> getListOfElements(int type){
		try {
			ArrayList<String> list = new ArrayList<String>();
			String read;
			if(type == 1) {
				 read = "SELECT DISTINCT Element_Name FROM dbAtmosphere_Composition";
			}else {
				 read = "SELECT DISTINCT Element_Name FROM dbPlanet_Composition";
			}
			PreparedStatement ps = (PreparedStatement) this.conn.prepareStatement(read);
			ResultSet result = ps.executeQuery();
			
			while(result.next()) {
				list.add(result.getString("Element_Name"));
				
			}
			
			return list;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
		
		
	}
	

	public ArrayList<AtmosphereComposition> getAtomCompForPlanet(String pName){
		try {
			String read = "Select * FROM dbPlanet_Composition Where PBname='"+ pName+"'";
			ArrayList<AtmosphereComposition> ele = new ArrayList<AtmosphereComposition>();
			
			PreparedStatement ps = (PreparedStatement)this.conn.prepareStatement(read);
			ResultSet result;
			
				result = ps.executeQuery();
			
			
			while(result.next()) {
				ele.add(new AtmosphereComposition( result.getString("Element_Name"), result.getDouble("Percentage")));
			}
			return ele;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return null;
	}
	
	/**
	 * Returns the Physics of a certain planet. 
	 * @param PName - The name of the planet being passed in. 
	 * @return The Physics of the planet named being passed in. 
	 */
	public Physics getPhysicsForPlanet(String PName) {
		try {
			String read = "Select * FROM dbPhysics WHERE PBname='" + PName+"'";
			Physics phy;
		
			PreparedStatement ps  = (PreparedStatement) this.conn.prepareStatement(read);
			ResultSet result = ps.executeQuery();
		
			result.next();
			//String MassMult, String MassExpo, double Gra, float Dis, double vel, double rad, double axi) {
			phy = new Physics(result.getString("MassMult"), result.getString("MassExpo"), 
					result.getDouble("Gravity"), result.getFloat("Distance"), result.getDouble("Velocity"), result.getDouble("Radius")
					, result.getDouble("Axis"));
			
			return phy;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	//------------------------------------End of planet Stuff -------------------------------------
	
	//---------------Database tables------------------------
	
	/*
	 * dbPhysics --table	|	dbPlanet -- table--		|	dbPlanetary_body				|	dbPlantay_System			
	 * PBname - String		|	PBname String			|	PBname - String					|	Name - String
	 * Distance - double	|	Landmass  - double		|	Name - String (Solar system)	|	Faculty_ID --string 7
	 * Gravity - double		|	Zone - String			|	Type - String 					|
	 * MassExpo - double	|	Category - String		|	Temperature - double			|
	 * MassMult - double 	|	Humidity int(11)		|									|
)	 * Velocity - float		|							|									|	
	 * Radius - float		|							|									|
	 * Axis - float			|							|									|
	 * __________________________________________________________________________________________
	 * dbPlanet_Composition    |
	 * PBname - string         |
	 * Element_Name - String   |
	 * Percentage - double     |
	 * _________________________
	 */
	
	/*
	 * I need to know how are we getting the mass for the user input.
	 * I need to know how we want to decide how each planet composition will be after creation (user pick or randomly generated.).
	 * I need to know how 
	 */
	/***************************Create a Planet**************************************************************************************/
	// mass is going to be a problem...,  Orbital speed == Velocity???,
	public void InsertNewPlanet(String planetName, float orbitalSpeed,  float Radius, int Humidity,float distance, double Temperature, String Mass) {
		try {
			//String name, double newMass,String zne, String catego,  double temp, int Hum, Physics ph
			//create a plane
			// populate planet
			
			// Function to insert into Database Here... 
			/*
			 * Need to update 
			 * dbPhysics - PBname, MassMult, MassExpo, Gravity, Distance, Velocity, Radius, Rotational_Direction, Axis
			*/
			//String PlanetName, double MassMul, double MassEx, float distance, float radius, float velocity
				char[] token = Mass.toCharArray();
				String massMul = "";
				String massExpo = "";
				int lineSplit = 0;
				for(int i = 0; i < token.length; i++) {
					System.out.println("Index: " + i + ", Char is: " + token[i]);
					if(token[(i)] == 'x') {
						lineSplit++;
					}else {
						if(lineSplit == 0) {
							massMul += token[i];
						}else {
							massExpo += token[i];
						}
					}
				}
				
				insertPlanetPhysics(planetName, Double.valueOf(massMul.trim()), Double.valueOf(massExpo.trim()), distance, Radius, orbitalSpeed);
			
			 /* ------------------------------------------------------------------------
			  * dbPlanet - PBname, Landmass, Zone, Category, Humidity
			  * /*Distance 
			  * 0 - 0.723 -- Hot
			  * 0.724 - 1.524 -- Gold
			  * 1.525 > Cold
			  */
			
				//String planetName, double Landmass, String Zone,String category, int Humidity
			
				String[] cat = {"Terrestrial", "Gas", "Dwarf"};
			String z = getZone(getColorScheme(),distance);
				
			insertPlanetPlanet(planetName,100, z, cat[((int)Math.random() * 3)],Humidity );
				//--------------------------------------------------------
			
			/* dbPlanetary_Body - PBname , Name (SolarSystem), Type(Planet || Star), Temperature.
			 *
			 */
				insertPlanetPlantaryBody(planetName, "Solar System", "Planet", Temperature);
			
			 /* 
			 * dbPlanet_Composition - PBname, Element_Name, Percentage
			 */
				ArrayList<AtmosphereComposition> element = Declarations.getListOfElements(planetName,1);
				for(int i = 0; i < element.size(); i++) {
					insertAtomComposition(planetName, element.get(i).getElementName(),element.get(i).getPercentage());
				}
				
				element = Declarations.getListOfElements(planetName,2);
				for(int i = 0; i < element.size(); i++) {
					insertPlanetComposition(planetName, element.get(i).getElementName(),element.get(i).getPercentage());
				}
				
				insertPlanetConnection(planetName,Temperature ,Declarations.currentUser.getUserID());
				
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private int getColorScheme() {
		char color = Declarations.color;
		if(color == 'M' && color == 'K') {
			return 1;
		}else if(color == 'G' && color == 'F' && color == 'A') {
			return 2;
		}else if(color == 'B' && color == 'O') {
			return 3;
		}
		return 0;
	}
	
	private String getZone(int num, double distance) {
	//	String  z = "";
		switch(num) {
		case 1:
			String  z = "";
				if(distance >= 0 && distance <= .4 ) {
					return "Hot";
				}else if(distance > .4 && distance <= 1) {
					return "Goldilock";
				}else {
					return "Cold";
				}
		case 2:
		
			if(distance >= 0 && distance <= .724 ) {
				return "Hot";
			}else if(distance > .724 && distance <= 1.524) {
				return "Goldilock";
			}else {
				return "Cold";
			}
		case 3:
			if(distance >= 0 && distance <= 1 ) {
				return "Hot";
			}else if(distance > 1 && distance <= 5.2) {
				return "Goldilock";
			}else {
				return "Cold";
			}
		default:
			return "Non Are Worthy";
		}
	}
	
	public void insertPlanetPhysics(String PlanetName, double MassMul, double MassEx, float distance, float radius, float velocity) {
		try {
			String insert = "INSERT INTO dbPhysics (PBname, MassMult, MassExpo, Gravity, Distance, Velocity, Radius, Rotational_Direction, Axis)"
					+ " VALUES (?,?,?,?,?,?,?,?,?);";
			PreparedStatement ps = (PreparedStatement) this.conn.prepareStatement(insert);
			ps.setString(1, PlanetName);
			ps.setDouble(2, MassMul);
			ps.setDouble(3, MassEx);
			ps.setDouble(4, ((double)Math.random() * 35+1)); // calculate gravity
			ps.setFloat(5, distance);
			ps.setFloat(6, velocity);
			ps.setFloat(7, radius);
			ps.setInt(8, 0);
			ps.setFloat(9,((float)  (Math.random()* 199 +1) ));
			ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insertPlanetPlanet(String planetName, double Landmass, String Zone,String category, int Humidity) {
		try {
			String insert = "INSERT INTO dbPlanet (PBname, Landmass, Zone, Category, Humidity) "
							+ "Values (?,?,?,?,?)";
			PreparedStatement ps = (PreparedStatement)this.conn.prepareStatement(insert);
			ps.setString(1, planetName);
			ps.setDouble(2, Landmass);
			ps.setString(3, Zone);
			ps.setString(4, category);
			ps.setInt(5, Humidity);
			ps.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insertPlanetPlantaryBody(String planetName, String solarSystemName, String Type, double Temperature) {
		try {
			String insert = "INSERT INTO dbPlanetary_Body (PBname, Name, Type, Temperature)"
					+ "Values (?,?,?,?)";
			PreparedStatement ps = (PreparedStatement) this.conn.prepareStatement(insert);
			ps.setString(1, planetName);
			ps.setString(2, solarSystemName);
			ps.setString(3, Type);
			ps.setDouble(4, Temperature);
			ps.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insertAtomComposition(String planetName, String ElementName, double percentage) {
		try {
			String insert = "INSERT INTO dbAtmosphere_Composition (PBname, Element_Name, Percentage)"
						+ "Values (?,?,?)";
			PreparedStatement ps = (PreparedStatement)this.conn.prepareStatement(insert);
			ps.setString(1, planetName);
			ps.setString(2, ElementName);
			ps.setDouble(3, percentage);
			ps.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insertPlanetComposition(String planetName, String ElementName, double percentage) {
		try {
			String insert = "INSERT INTO dbPlanet_Composition (PBname, Element_Name, Percentage)"
						+ "Values (?,?,?)";
			PreparedStatement ps = (PreparedStatement)this.conn.prepareStatement(insert);
			ps.setString(1, planetName);
			ps.setString(2, ElementName);
			ps.setDouble(3, percentage);
			ps.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void insertPlanetConnection(String planetName,double temp ,String studentID) {
		try {
			String insert = "INSERT INTO dbStudent_Planets (PBname, Type,Tempture,Student_Id) "
					+ "VALUES (?,?,?,?)";
			PreparedStatement ps = (PreparedStatement)this.conn.prepareStatement(insert);
			
			ps.setString(1, planetName);
			ps.setString(2,"Planet");
			ps.setDouble(3, temp);
			ps.setString(4, studentID);
			ps.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/****************************************Create Planet ends**************************************************************/
	
	
	/**************************************** Get user id and pass *************************************************************/
	
	public class Wrap{
		private Wrap instance;
		private String userId;
		private String pass;
		
		private Wrap() {
			
		}
		public Wrap getInstance() {
			
			if(instance == null) {
				instance = new Wrap();
			}
			return instance;
		}
		public String getUserId() {
			return this.userId;
		}
		public String pass() {
			return this.pass;
		}
		public void setValues(String user, String pass) {
			this.userId = user;
			this.pass = pass;
		}
	}
	
	
	/*
	 * 			String read = "Select * FROM dbPhysics WHERE PBname='" + PName+"'";
			Physics phy;
		
			PreparedStatement ps  = (PreparedStatement) this.conn.prepareStatement(read);
			ResultSet result = ps.executeQuery();
		
			result.next();
	 */
	
	
	public boolean isMatchLogin(String userid, String pass) {
		try {
			String read = "SELECT * FROM dbStudent Where Student_ID='"+userid + "'";
			PreparedStatement ps = (PreparedStatement)this.conn.prepareStatement(read);
			ResultSet result = ps.executeQuery();
			
			if(result.next()) {
				
				// can save the stuff here... if any...
				//String fName,String mInit,String lName,String id, categoryUser s
				Declarations.currentUser = new User(result.getString("Fname"),result.getString("Minit"),result.getString("lName"),result.getString("Student_ID"),Declarations.categoryUser.Student);
				return true;
			}
			
			return false;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public ArrayList<Planet> getStudentPlanet(){
		try {
			ArrayList<Planet> studentPlanet = new ArrayList<Planet>();
			String read = "SELECT * FROM dbStudent_Planets WHERE Student_Id='" + Declarations.currentUser.getUserID() + "'";
			PreparedStatement ps = (PreparedStatement)this.conn.prepareStatement(read);
			ResultSet result = ps.getResultSet();
			
			if (result == null) {
				return null;
			}
			
			while(result.next()) {
				studentPlanet.add(PlanetGateway.getInstance().readOnePlanet(result.getString("PBname")));
			}
			return studentPlanet;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	/******************************************* ends user Id and pass *********************************************************/
	/****************************************Planet Delete********************************************************************/
	
	public void DeletePlanet(String planetName) {
		/*	insertPlanetPhysics(planetName, Double.valueOf(massMul.trim()), Double.valueOf(massExpo.trim()), distance, Radius, orbitalSpeed);
			
			 /* ------------------------------------------------------------------------
			  * dbPlanet - PBname, Landmass, Zone, Category, Humidity
			  * /*Distance 
			  * 0 - 0.723 -- Hot
			  * 0.724 - 1.524 -- Gold
			  * 1.525 > Cold
			  */
			
				//String planetName, double Landmass, String Zone,String category, int Humidity
				//insertPlanetPlanet(planetName,100, z, cat[((int)Math.random() * 3)],Humidity );
				//--------------------------------------------------------
			
			/* dbPlanetary_Body - PBname , Name (SolarSystem), Type(Planet || Star), Temperature.
			 *
			 */
				//insertPlanetPlantaryBody(planetName, "Solar System", "Planet", Temperature);
			
			 /* 
			 * dbPlanet_Composition - PBname, Element_Name, Percentage
			 */
				/*ArrayList<AtmosphereComposition> element = Declarations.getListOfElements(planetName,1);
				for(int i = 0; i < element.size(); i++) {
					insertAtomComposition(planetName, element.get(i).getElementName(),element.get(i).getPercentage());
				}
				
				element = Declarations.getListOfElements(planetName,2);
				for(int i = 0; i < element.size(); i++) {
					insertPlanetComposition(planetName, element.get(i).getElementName(),element.get(i).getPercentage());
				}*/
				
				PlanetGateway.getInstance().deletePlanetComposition(planetName);
				PlanetGateway.getInstance().deleteAtomComposition(planetName);
				PlanetGateway.getInstance().deletePlanetPhysics(planetName);
				PlanetGateway.getInstance().deletePlanetPlanet(planetName);
				PlanetGateway.getInstance().deletePlanetPlantaryBody(planetName);
				
				
		
	}
	
	
	public void deletePlanetPlanet(String planetName){
		try {
			String delete = "DELETE FROM dbPlanet WHERE PBname='" + planetName + "'";
			PreparedStatement ps = (PreparedStatement)this.conn.prepareStatement(delete);
			ps.executeUpdate();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deletePlanetPhysics(String planetName){
		try {
			String delete = "DELETE FROM dbPhysics WHERE PBname='" + planetName + "'";
			PreparedStatement ps = (PreparedStatement)this.conn.prepareStatement(delete);
			ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deletePlanetPlantaryBody(String planetName){
		try {
			String delete = "DELETE FROM dbPlanetary_Body WHERE PBname='" + planetName + "'";
			PreparedStatement ps = (PreparedStatement)this.conn.prepareStatement(delete);
			ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteAtomComposition(String planetName){
		try {
			String delete = "DELETE FROM dbAtmosphere_Composition WHERE PBname='" + planetName + "'";
			PreparedStatement ps = (PreparedStatement)this.conn.prepareStatement(delete);
			ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deletePlanetComposition(String planetName){
		try {
			String delete = "DELETE FROM dbPlanet_Composition WHERE PBname='" + planetName + "'";
			PreparedStatement ps = (PreparedStatement)this.conn.prepareStatement(delete);
			ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**********************************************End planet Delete *******************************************************/
	
	
	public ArrayList<Planet> getListOfPlanets(String table) throws Exception{
		return  readAll( table) ;
	}
	
	
	public void delete(String table,String name) {
		try {
			String delete = "DELETE FROM " + table + " WHERE Pname=?";
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(delete);
			ps.setString(1, name);
			Declarations.logger.info(ps);
			ps.executeUpdate();
			
		}catch(Exception e) {
			Declarations.logger.error("Error: Delete...");
		}
	}
	
	
	// Retrieves a list of student ID from the Database... 
	public ArrayList<String> getUserID(String colName, String tableName){
		try {
			String read = "Select " + colName + " FROM " + tableName;
			PreparedStatement ps = (PreparedStatement) this.conn.prepareStatement(read);
			ResultSet result = ps.executeQuery();
			ArrayList<String> listOfUsersID = new ArrayList<String>();
			while(result.next()) {
				listOfUsersID.add(result.getString(colName));
			}
			
			
			return listOfUsersID;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void stopConnection() {		
		try {
			this.conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Declarations.logger.error("Error: Closing connection Fail...");
		}
	}
	
}
