/**
 * @author Shawn & Kwan - JavaFX
**/
package application;

import application.Controller.controller;
import application.model.GameManager.Load;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application
{
	public static Stage stage;
	private static Load load = new Load();
	
	@Override
	public void start(Stage primaryStage) 
	{
		stage = primaryStage;
		
		try
		{
			// Shawn
			if(controller.mainMenu == true)
				controller.mediaPlayer.setCycleCount(15);
			
			controller.mediaPlayer.play();	
			//
			
			// Load the FXML document (Created with SceneBuilder)
			FXMLLoader loader = new FXMLLoader();
			
			if(controller.toggle == false) 
				loader.setLocation( Main.class.getResource("../LockedMenu.fxml") );
			else
				loader.setLocation( Main.class.getResource("../UnlockedMenu.fxml") );
			
			// Load the layout from the FXML and add it to the scene
			AnchorPane layout = (AnchorPane) loader.load();
			
			// Scene scene = new Scene( layout, 832, 832 );
			Scene scene = new Scene( layout );
			
			// Set the scene to stage and show the stage to the user
			primaryStage.setTitle("The Tower");
			primaryStage.setResizable(false);
			
			//primaryStage.centerOnScreen();
			
			primaryStage.setScene(scene);
			
			primaryStage.centerOnScreen();
			
			primaryStage.show();
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}

	public static Load getLoad(){
		return load;
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}
