package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import Model.*;

public class SelectController implements EventHandler, Initializable
{
	@FXML Button Createid;
	@FXML Button Requestid;
	@FXML Button Backid;
	@FXML Button Populateid;
	
	boolean P, R;

	public SelectController(boolean p, boolean r) 
	{
		Music.mp3 = "";
		
		Music.handleMusic("SpaceSelect.mp3");
		this.P = p;
		this.R = r;
	}

	@FXML public void CreateOnAction(Event e ) 
	{
		System.out.println("In Create");
		
		ViewManager.getinstance().LoadSceneNotStart("../View/Create.fxml", new CreateController());
	}
	
	@FXML public void RequestOnAction(Event e) 
	{
		System.out.println("In Request");
		
		ViewManager.getinstance().LoadSceneNotStart("../View/Request.fxml", new RequestController());
	}
	
	public void BackOnAction(Event e) 
	{
		System.out.println("In Back");
		
		ViewManager.getinstance().LoadSceneNotStart("../View/Back.fxml", new BackController());
	}
	
	public void PopulateOnAction(Event e) 
	{
		System.out.println("In Populate");
		
		ViewManager.getinstance().LoadSceneNotStart("../View/Populate.fxml", new PopController());
	}	
	
	@Override public void initialize(URL location, ResourceBundle resources) 
	{	
		Populateid.setDisable(P);
		
		Requestid.setDisable(R);
	}

	@Override public void handle(Event event) {}
}