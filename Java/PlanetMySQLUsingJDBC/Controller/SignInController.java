package Controller;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import Model.*;

public class SignInController implements EventHandler, Initializable
{
	@FXML Button Selectid;
	
	public SignInController() 
	{
		Music.mp3 = "";
		
		Music.handleMusic("RocketThruster.mp3");
	}
	
	public void SelectOnAction(Event e) 
	{
		System.out.println("In Select");
		
		ViewManager.getinstance().LoadSceneNotStart("../View/Select.fxml", new SelectController(true, true));
	}
	
	@Override public void initialize(URL location, ResourceBundle resources) {}

	@Override public void handle(Event event) {}
}