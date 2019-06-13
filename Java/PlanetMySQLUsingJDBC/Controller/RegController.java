package Controller;


import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import Model.*;

public class RegController implements EventHandler, Initializable
{
	@FXML TextField reg_first;
	@FXML TextField reg_middle;
	@FXML TextField reg_last;
	@FXML TextField reg_id;
	@FXML TextField reg_pw;
	@FXML Label regWarn1;
	@FXML Label regWarn2;
	@FXML Label regWarn3;
	@FXML Label regWarn4;
	@FXML Label regWarn5;
	@FXML Button buttonReg;
	@FXML Button buttonReturn;
	
	public RegController() 
	{
		Music.mp3 = "";
		
		Music.handleMusic("SpaceStation.mp3");
	}
	
	@FXML public void RegOnAction(Event e) 
	{
		if(Authenticate_Reg()) 
		{
			Music.mp3 = "";
			
			Music.handleMusic("NeverFrozenBottomFlows.mp3");
			
			// move on
			ViewManager.getinstance().LoadSceneNotStart("../View/Login.fxml", new controller());
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
		
		System.out.println("In Registration");
	}
	
	public boolean Authenticate_Reg() 
	{
		if((this.reg_first.getText().length() > 0) &&
			(this.reg_middle.getText().length() > 0) &&
			(this.reg_last.getText().length() > 0) &&
			(this.reg_id.getText().length() > 0) &&
			(this.reg_pw.getText().length() > 0))
		{
			if(this.reg_first.getText().length() > 16)
				regWarn1.setText("Exceeding over 16 characters.");
			else if(this.reg_middle.getText().length() > 4)
				regWarn2.setText("Exceeding over 4 characters.");
			else if(this.reg_last.getText().length() > 16)
				regWarn3.setText("Exceeding over 16 characters.");
			else if(this.reg_id.getText().length() > 6)
				regWarn4.setText("Exceeding over 6 characters.");
			else if(this.reg_pw.getText().length() > 16)
				regWarn5.setText("Exceeding over 16 characters.");
			else
			{
				regWarn1.setText("");
				regWarn2.setText("");
				regWarn3.setText("");
				regWarn4.setText("");
				regWarn5.setText("");
			
				return true;
			}
		}
		
		return false;
	}
	
	@FXML public void ReturnOnAction(Event e) 
	{
		System.out.println("In Login");
		
		Music.mp3 = "";
		
		Music.handleMusic("NeverFrozenBottomFlows.mp3");
	
		ViewManager.getinstance().LoadSceneNotStart("../View/Login.fxml", new controller());
	}
	
	@Override public void initialize(URL location, ResourceBundle resources) {}

	@Override public void handle(Event event) {}
}