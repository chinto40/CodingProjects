package Controller;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import Model.*;
import Processing.SolarSystemProcessing;

public class PopController implements EventHandler, Initializable 
{
	public PopController() 
	{
		Music.mp3 = "";
		
		Music.handleMusic("SpaceAvalon.mp3");
		
		new java.util.Timer().schedule
		( 
		        new java.util.TimerTask() 
		        {
		            @Override
		            public void run() 
		            {
		                // Call Lauren's Code
		            	SolarSystemProcessing.main("");
		            }
		        }, 5000 
		);
	}
	
	@Override public void initialize(URL location, ResourceBundle resources) {}

	@Override public void handle(Event event) {}
}