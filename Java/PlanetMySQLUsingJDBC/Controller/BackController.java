package Controller;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import Model.*;

public class BackController implements EventHandler, Initializable
{
	@FXML Button Backid;
	
	public BackController() 
	{
		Music.mp3 = "";
		
		Music.handleMusic("RocketThruster.mp3");
	}
	
	@FXML public void BackOnAction(Event e) 
	{
		System.out.println("In Back");
		
		ViewManager.getinstance().LoadSceneNotStart("../View/Login.fxml", new controller());
		
		Music.mp3 = "";
		
		Music.handleMusic("NeverFrozenBottomFlows.mp3");
		
		controller.flag = 0;
	}
	
	@Override public void initialize(URL location, ResourceBundle resources) {}

	@Override public void handle(Event event) {}
}