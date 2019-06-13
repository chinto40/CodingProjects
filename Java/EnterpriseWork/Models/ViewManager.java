package Models;

import java.io.IOException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Controller.MenuController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * View Manager class going to swap views. 
 * @author Jacinto Molina
 *
 */
public class ViewManager {

	/**
	 * Instance of this class datatype.
	 */
	private static ViewManager viewSource;
	 /**
     * Logger for error printing to the console. 
     */
	 private static Logger logger = LogManager.getLogger(MenuController.class);
	 
	 public static Stage myStage;
	
	 /**
	  * Private Constructor that constructs a new ViewManager Class. 
	  */
	private ViewManager() {	
	}
	
	//singleton
	/**
	 * Purpose is to create a single instance and return it if one isn't made already.
	 * @return the single instance of this class.
	 */
	public static ViewManager getInstance() {
		if(viewSource == null) {
			viewSource = new ViewManager();
		}
		
		return viewSource;
	}
	
	/**
	 * View to Start the progam.
	 * @param name - name of the FXML File. 
	 * @param primaryStage - The primary Stage that is used. 
	 * @param controller - The controller being used to handle Events. 
	 */
	public void startView(String name, Stage primaryStage,EventHandler controller ) {
		try {
			URL fxmlFile = this.getClass().getResource(name);
			FXMLLoader loader = new FXMLLoader(fxmlFile);
			loader.setController(controller);
			Parent root = loader.load();
			Scene scene = new Scene(root,745,517);
			myStage = primaryStage;
			primaryStage.setScene(scene);
			primaryStage.show();
	} catch(Exception ex) {
			logger.error("Error: "+ex.getStackTrace());
			ex.printStackTrace();
		}	
	}
	
	
	/**
	 * Next View to be shown and swapped for. 
	 * @param name - name of the FXML File. 
	 * @param e - Event occuring. 
	 * @param controller - The controller being used to handle Events. 
	 */
	public void switchViews(String name, Event e, EventHandler controller) {
		
		try {// load a the book scene have to change the info inside.
	    	//Stage mainStage = (Stage)((Node)e.getSource()).getScene().getWindow();
	    	Stage mainStage = myStage;
			URL fxmlFile = this.getClass().getResource(name);
			FXMLLoader loader = new FXMLLoader(fxmlFile);
			loader.setController(controller);
			Parent root = loader.load();
			mainStage.getScene().setRoot(root);
			mainStage.setHeight(560);
			mainStage.setWidth(745);
			mainStage.show();
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			logger.error("Error: "+e1.getStackTrace());
			e1.printStackTrace();
		}	 
	}
	

	/**
	 * Next View to be shown and swapped for. 
	 * @param name - name of the FXML File. 
	 * @param e - Event occuring. 
	 * @param controller - The controller being used to handle Events. 
	 */
	public void switchViews(String name, ActionEvent e, EventHandler controller) {
		
		try {// load a the book scene have to change the info inside.
	    	//Stage mainStage = (Stage)((Node)e.getSource()).getScene().getWindow();
	    	Stage mainStage = myStage;
	    	URL fxmlFile = this.getClass().getResource(name);
			FXMLLoader loader = new FXMLLoader(fxmlFile);
			loader.setController(controller);
			Parent root = loader.load();
			mainStage.getScene().setRoot(root);
			mainStage.setHeight(560);
			mainStage.setWidth(745);
			mainStage.show();
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			logger.error("Error: "+e1.getStackTrace());
			e1.printStackTrace();
		}	 
	}
	

}
