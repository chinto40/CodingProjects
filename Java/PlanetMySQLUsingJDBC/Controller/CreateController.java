package Controller;


import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import Model.*;


public class CreateController implements EventHandler, Initializable
{
	@FXML ChoiceBox starType;
	@FXML Button Create_ID;
	@FXML Button Return_ID;
	@FXML Button Delete_ID;
	@FXML ListView pull_view;
	@FXML Label create_str_only;
	@FXML Label pull_str_only;
	@FXML Label del_str_only;
	@FXML Label int_only;
	@FXML Label float_only1;
	@FXML Label float_only2;
	@FXML Label float_only3;
	@FXML Label float_only4;
	@FXML Label float_only5;
	@FXML Label float_only6;
	@FXML TextField pull_name;
	@FXML TextField del_name;
	@FXML TextField create_name;
	@FXML TextField radius;
	@FXML TextField orbital_speed;
	@FXML TextField humidity;
	@FXML TextField no_of_planets;
	@FXML TextField distance;
	@FXML TextField mass;
	@FXML TextField temperature;
	
	String color = "";
	private static int only_one_PB = 0;
	private static int display = 1;
	
	private ArrayList<Planet> listOfPlanet = new ArrayList<Planet>();
	
	static boolean setP = true, setR = true;
	
	public CreateController() 
	{
		Music.mp3 = "";
		
		Music.handleMusic("SpaceStation.mp3");
	}
		
	@FXML public void CreateOnAction(Event e) 
	{
		if(Authenticate_Create()) 
		{
			// move on
			setP = false;
			
			// here get the information...
			
			chooseStarType();
			Declarations.color = color.charAt(0);
			//Insert new planet into the Database... Here...
			PlanetGateway.getInstance().InsertNewPlanet(this.create_name.getText(), Float.parseFloat(this.orbital_speed.getText()), Float.parseFloat(this.radius.getText()), Integer.parseInt(this.humidity.getText()), Float.parseFloat(this.distance.getText()), Double.parseDouble(this.temperature.getText()), this.mass.getText());
			ViewManager.getinstance().LoadSceneNotStart("../View/Select.fxml", new SelectController(setP, true));
		}
		else
		{
			Alert alert = new Alert(AlertType.CONFIRMATION);
			
            alert.setTitle("Error");
            
            if(only_one_PB == 1)
            	alert.setContentText("Exceeding the number of PBs created.");
            else if(display == 0)
            	alert.setContentText("Incorrect field.");
            
            ButtonType Ok = new ButtonType("Ok");

            alert.getButtonTypes().setAll(Ok);
            
            Optional<ButtonType> event = alert.showAndWait();
            
            if(event.get() == Ok) 
            {
                // Event Click... 
            }
		}
		
		System.out.println("In Create");
	}

	
	public boolean Authenticate_Create() 
	{
		// Student
		if(only_one_PB != 1)
		{
			if((controller.user == 1) &&
					((this.create_name.getText().length() > 0) && (this.create_name.getText().length() < 64) && 
					(this.radius.getText().length() > 0) && (this.radius.getText().length() < 64 ) &&
					(this.orbital_speed.getText().length() > 0) && (this.orbital_speed.getText().length() < 64) &&
					(this.humidity.getText().length() > 0) &&  (this.humidity.getText().length() < 64) &&
					(this.distance.getText().length() > 0) && (this.distance.getText().length() < 64) &&
					(this.mass.getText().length() > 0) && (this.mass.getText().length() < 64) &&
					(this.temperature.getText().length() > 0) && this.temperature.getText().length() < 64));
			{
				
		
				/*
				 * Get Jacinto's logical understand to fix the logical
				 * error.
				 */
				/*if(this.create_name.getText().length() > 64)
					create_str_only.setText("Field exceeding 64 characters");
				else if(this.radius.getText().length() > 6)
					float_only1.setText("Field exceeding 6 characters");
				else if(this.orbital_speed.getText().length() > 7)
					float_only3.setText("Field exceeding 7 characters");
				else if(this.humidity.getText().length() > 11)
					float_only5.setText("Field exceeding 11 characters");
				else if(this.distance.getText().length() > 12)
					float_only2.setText("Field exceeding 12 characters");
				else if(this.mass.getText().length() > 12)
					float_only4.setText("Field exceeding 12 characters");
				else if(this.temperature.getText().length() > 8)
					float_only6.setText("Field exceeding 8 characters");
				else
				{*/
					if(isFloat(this.radius.getText()) != true)
						float_only1.setText("Float numbers only");
					else if(isFloat(this.orbital_speed.getText()) != true)
						float_only3.setText("Float numbers only");
					else if(isFloat(this.humidity.getText()) != true)
						float_only5.setText("Float numbers only");
					else if(isFloat(this.distance.getText()) != true)
						float_only2.setText("Float numbers only");
					else if(isFloat(this.temperature.getText()) != true)
						float_only6.setText("Float numbers only");
					else
					{
						float_only1.setText("");
						float_only2.setText("");
						float_only3.setText("");
						float_only4.setText("");
						float_only5.setText("");
						float_only6.setText("");
						int_only.setText("");

						only_one_PB = 1;
						
						display = 1;

						return true;
					}
				//}
			}
		}
		// Faculty
		else if((controller.user == 0) &&
				(this.create_name.getText().length() > 0) && (this.create_name.getText().length() > 64) &&
				(this.radius.getText().length() > 0) &&  (this.radius.getText().length() < 6) &&
				(this.orbital_speed.getText().length() > 0) && (this.orbital_speed.getText().length() < 7) &&
				(this.humidity.getText().length() > 0) && (this.humidity.getText().length() < 11) &&
				((this.no_of_planets.getText().length() > 0) && (Integer.parseInt(this.no_of_planets.getText()) < 13)) && 
				(this.distance.getText().length() > 0) && (this.distance.getText().length() < 12) &&
				(this.mass.getText().length() > 0) &&  (this.mass.getText().length() < 12) &&
				(this.temperature.getText().length() > 0) && (this.temperature.getText().length() > 8))
		{
			/*
			 * Get Jacinto's logical understand to fix the logical
			 * error.
			 */
			/*if(this.create_name.getText().length() > 64)
				create_str_only.setText("Field exceeding 64 characters");
			else if(this.radius.getText().length() > 6)
				float_only1.setText("Field exceeding 6 characters");
			else if(this.orbital_speed.getText().length() > 7)
				float_only3.setText("Field exceeding 7 characters");
			else if(this.humidity.getText().length() > 11)
				float_only5.setText("Field exceeding 11 characters");
			else if(this.distance.getText().length() > 12)
				float_only2.setText("Field exceeding 12 characters");
			else if(this.mass.getText().length() > 12)
				float_only4.setText("Field exceeding 12 characters");
			else if(this.temperature.getText().length() > 8)
				float_only6.setText("Field exceeding 8 characters");
			else if(this.no_of_planets.getText().length() > 12)
				int_only.setText("Field exceeding 12 characters");
			else
			{*/
				if(isInt(this.no_of_planets.getText()) != true)
					int_only.setText("Integer numbers only");
				else if(Integer.parseInt(this.no_of_planets.getText()) > 12)
					int_only.setText("Field must be less than 13.");
				else if(isFloat(this.radius.getText()) != true)
					float_only1.setText("Float numbers only");
				else if(isFloat(this.orbital_speed.getText()) != true)
					float_only3.setText("Float numbers only");
				else if(isFloat(this.humidity.getText()) != true)
					float_only5.setText("Float numbers only");
				else if(isFloat(this.distance.getText()) != true)
					float_only2.setText("Float numbers only");
				else if(isFloat(this.temperature.getText()) != true)
					float_only6.setText("Float numbers only");
				else
				{
					float_only1.setText("");
					float_only2.setText("");
					float_only3.setText("");
					float_only4.setText("");
					float_only5.setText("");
					float_only6.setText("");
					int_only.setText("");
					
					display = 1;
			
					return true;
				}
			//}
		}
		
		display = 1;
		
		return false;
	}
	
	public boolean isFloat(String s) 
	{
		try 
		{
			Float.parseFloat(s);
		} 
		catch(NumberFormatException e) 
		{ 
			return false; 
		}
		
		return true;
	}
	
	public boolean isInt(String s) 
	{
		try 
		{
			Integer.parseInt(s);
		} 
		catch(NumberFormatException e) 
		{ 
			return false; 
		}
		
		return true;
	}
	
	public void chooseStarType() 
	{
		String type = this.starType.getSelectionModel().getSelectedItem() + "";
		
		int star = 0;
		
		if(type.equals("M"))
		{
			star = 1;

			color = "Red";
		}
		else if(type.equals("K")) 
		{
			star = 2;

			color = "Orange";
		}
		else if(type.equals("G")) 
		{
			star = 3;

			color = "Yellow";
		}
		else if(type.equals("F")) 
		{
			star = 4;
			
			color = "Light Yellow";
		}
		else if(type.equals("A")) 
		{
			star = 5;
			
			color = "White";
		}
		else if(type.equals("B")) 
		{
			star = 6;

			color = "Blue";
		}
		else 
		{
			star = 7;

			color = "Purple";
		}
		
		
		
	}
	
	@FXML public void PullOnAction(Event e) 
	{
		if(Authenticate_Pull()) 
		{
			// move on
			setR = false;
			
			ViewManager.getinstance().LoadSceneNotStart("../Select.fxml", new SelectController(true, setR));
		}
		else
		{
			Alert alert = new Alert(AlertType.CONFIRMATION);
			
            alert.setTitle("Error");
            
            alert.setContentText("Incorrect field.");

            ButtonType Ok = new ButtonType("Ok");

            alert.getButtonTypes().setAll(Ok);
            
            Optional<ButtonType> event = alert.showAndWait();
            
            if(event.get() == Ok) 
            {
                // Event Click... 
            }
		}
		
		System.out.println("In Create");
	}
	
	public boolean Authenticate_Pull() 
	{
		if(this.pull_name.getText().length() > 0)
		{
			/*if(this.pull_name.getText().length() > 64)
				pull_str_only.setText("Field exceeding 64 characters");
			else*/
			
			if(!(this.pull_name.getText().equals("a")))
				return false;
			else
				return true;
		}
		
		return false;
	}
	
	@FXML public void DelOnAction(Event e) 
	{
		if(Authenticate_Del()) 
		{
			//new SelectController(true, true);
		}
		else
		{
			Alert alert = new Alert(AlertType.CONFIRMATION);
			
            alert.setTitle("Error");
            
            alert.setContentText("Incorrect field.");

            ButtonType Ok = new ButtonType("Ok");

            alert.getButtonTypes().setAll(Ok);
            
            Optional<ButtonType> event = alert.showAndWait();
            
            if(event.get() == Ok) 
            {
                // Event Click... 
            }
		}
		
		System.out.println("In Create");
	}
	
	public boolean Authenticate_Del() 
	{
		if(this.del_name.getText().length() > 0)
		{
			/*if(this.pull_name.getText().length() > 64)
				pull_str_only.setText("Field exceeding 64 characters");
			else*/
			
			if(!(this.del_name.getText().equals("a")))
				return false;
			else if(this.del_name.getText().equals("a"))
			{
				only_one_PB = 0;
				
				return true;
			}
		}
		
		return false;
	}
	
	@FXML public void ReturnOnAction(Event e)
	{
		System.out.println("In Return");
	
		ViewManager.getinstance().LoadSceneNotStart("../View/Select.fxml", new SelectController(setP, setR));
	}
	
	
	@Override public void handle(Event event) {}
	
	@Override public void initialize(URL location, ResourceBundle resources) 
	{
		this.starType.getItems().addAll("M", "K", "G", "F", "A", "B", "O");
		
		this.starType.getSelectionModel().selectFirst();
		
		if(controller.user == 1)
		{
			this.no_of_planets.setText("1");
			
			this.no_of_planets.setEditable(false);
		}
		
		
		//pull_view
		this.listOfPlanet = PlanetGateway.getInstance().getStudentPlanet();
		
		if(this.listOfPlanet != null) {
		
			for(int index = 0; index < this.listOfPlanet.size(); index++) {
				//BookList.getItems().add(this.lib.getListOfBooks().get(index).toString());
				this.pull_view.getItems().add(this.listOfPlanet.get(index).getName());
				//System.out.println(this.lib.getListOfBooks().get(index).toString());
		}
		//anon class
		this.pull_view.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {	
				//BookGateway.bookSelected = (Book)BookList.getSelectionModel().getSelectedItem();
				//ViewManager.getInstance().switchViews("/View/BookDetailView.fxml", e, new BookDetailController((Book)BookList.getSelectionModel().getSelectedItem(), 0));
			}
			
		});
		
		}else {
			this.pull_view.getItems().add("No Planets found on Database....");
		}
		
		
	}
}