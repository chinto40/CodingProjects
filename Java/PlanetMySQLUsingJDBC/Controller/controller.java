package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import application.Main;
import Model.*;

public class controller implements EventHandler, Initializable 
{
	@FXML public TextField textfieldUser;
	@FXML public TextField textfieldPW;
	@FXML private Button buttonLogin;
	@FXML private Button buttonRegister;
	@FXML private Button buttonReturn;
	@FXML private Label userWarn;
	@FXML private Label pwWarn;
	
	public static int flag = 0;
	public static int locked = 0;
	private static int limit = 3;
	
	// User set to 0 indicate, faculty.
	public static int user = 0;
	
	public controller() {}
	
	static void handleStageScene(Parent root, ActionEvent event) throws IOException 
	{	
		Scene scene = new Scene(root);
		Main.stage.setScene(scene);
	}
	
	public void handleAuthentication()
	{
		if(textfieldUser.getText().length() == 6 && locked != 1)
		{
			//if(textfieldUser.getText().equals("abc123") && textfieldPW.getText().equals("abc123"))
			if(PlanetGateway.getInstance().isMatchLogin(textfieldUser.getText(), this.textfieldPW.getText()))
			{
				System.out.println("Student Login Successful");
			
				flag = 1;
				
				user = 1;
			}else {
				limit--;
				
				if(limit > 0)
				{
					Alert alert = new Alert(AlertType.CONFIRMATION);
				
					alert.setTitle("Error");
	            
					alert.setContentText("Username or Password is invalid.");

					ButtonType Ok = new ButtonType("Ok");

					alert.getButtonTypes().setAll(Ok);
	            
					Optional<ButtonType> event = alert.showAndWait();
	            
					if(event.get() == Ok) 
					{
						// Event Click... 
					}
				}
				else
	            {
					locked = 1;
					
					Alert alert = new Alert(AlertType.CONFIRMATION);
					
		            alert.setTitle("Error");
		            
		            alert.setContentText("You have reached maximum login attempt.");

		            ButtonType Ok = new ButtonType("Ok");

		            alert.getButtonTypes().setAll(Ok);
		            
		            Optional<ButtonType> event = alert.showAndWait();
		            
		            if(event.get() == Ok) 
		            {
		                // Event Click... 
		            }
	            }
			}
		}
		else if(textfieldUser.getText().length() == 7 && locked != 1)
		{
			if(textfieldUser.getText().equals("aaaa000") && textfieldPW.getText().equals("aaaa000"))
			{
				System.out.println("Faculty Login Successful");
			
				flag = 1;
				
				user = 0;
			}
			/*else
			{
				if(!(textfieldUser.getText().equals("abc123")))
					userWarn.setText("Incorrect Username");
				else if(!(textfieldPW.getText().equals("abc123")))
					pwWarn.setText("Incorrect Password");
			}*/
		}
		else
		{
			limit--;
			
			if(limit > 0)
			{
				Alert alert = new Alert(AlertType.CONFIRMATION);
			
				alert.setTitle("Error");
            
				alert.setContentText("Username or Password is invalid.");

				ButtonType Ok = new ButtonType("Ok");

				alert.getButtonTypes().setAll(Ok);
            
				Optional<ButtonType> event = alert.showAndWait();
            
				if(event.get() == Ok) 
				{
					// Event Click... 
				}
			}
			else
            {
				locked = 1;
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
				
	            alert.setTitle("Error");
	            
	            alert.setContentText("You have reached maximum login attempt.");

	            ButtonType Ok = new ButtonType("Ok");

	            alert.getButtonTypes().setAll(Ok);
	            
	            Optional<ButtonType> event = alert.showAndWait();
	            
	            if(event.get() == Ok) 
	            {
	                // Event Click... 
	            }
            }
		}
	}
	
	@FXML public void LoginOnAction(Event sign_in) throws IOException 
	{	
		handleAuthentication();
		
		if(flag == 1 && locked != 1)
		{
			ViewManager.getinstance().LoadSceneNotStart("../View/SignIn.fxml", new SignInController());
		}
		else
		{
			System.out.println("Login Failed");
			sign_in.consume();
		}
	}
	
	@FXML public void RegisterOnAction(Event e) throws IOException 
	{
		ViewManager.getinstance().LoadSceneNotStart("../View/Register.fxml", new RegController());
	}

	@Override public void initialize(URL location, ResourceBundle resources) {}

	@Override public void handle(Event event) {}
}