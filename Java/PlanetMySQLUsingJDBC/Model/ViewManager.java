package Model;

import java.io.IOException;
import java.net.URL;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import application.Main;

public class ViewManager 
{
	private static ViewManager instance;
	private static Stage myStage;
	private ViewManager() {}
	
	public static ViewManager getinstance() 
	{
		if(instance == null) 
		{
			instance = new ViewManager();
		}
		
		return instance;
	}
	
	public void LoadSceneNotStart(String name, EventHandler controller) 
	{
		try 
		{
			URL fxmlFile = this.getClass().getResource(name);
			FXMLLoader loader = new FXMLLoader(fxmlFile);
			
			loader.setController(controller);
			
			// Load the layout from the FXML and add it to the scene
			AnchorPane layout = (AnchorPane) loader.load();
			
			// Scene scene = new Scene( layout, 832, 832 );
			Scene scene = new Scene( layout );
			
			// Set the scene to stage and show the stage to the user
			myStage.setTitle("Welcome to Deep Space Nine.");
			myStage.setResizable(false);
			
			myStage.setScene(scene);
			
			myStage.centerOnScreen();
			
			myStage.show();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}	
	}
	
	public void LoadSceneStart(Stage pStage, String name, EventHandler controller) throws IOException 
	{
		myStage = pStage;
		
        URL fxmlFile = this.getClass().getResource(name);
        
		FXMLLoader loader = new FXMLLoader(fxmlFile);
		
		loader.setController(controller);
		
		// Load the layout from the FXML and add it to the scene
		AnchorPane layout = (AnchorPane) loader.load();
		
		// Scene scene = new Scene( layout, 832, 832 );
		Scene scene = new Scene( layout );
		
		// Set the scene to stage and show the stage to the user
		myStage.setTitle("Welcome to Deep Space Nine.");
		myStage.setResizable(false);
		
		myStage.setScene(scene);
		
		myStage.centerOnScreen();
		
		myStage.show();
	}
}