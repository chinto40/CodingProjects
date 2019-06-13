package Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Gateway.BookGateway;
import Models.AuditTrailEntry;
import Models.ViewManager;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

/**
 * Controller for the AuditTrailView fxml file. 
 * @author Jacinto Molina
 *
 */
public class AuditTrailController implements EventHandler, Initializable{

	@FXML
	private ListView AuditList;
	
	@FXML
	private Button ReturnID;
	
	
	/**
	 * Constructs a new AuditTrailController object. 
	 */
	public AuditTrailController() {
			
	}
	
	@Override
	public void handle(Event arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Return event handler for the return button. 
	 * @param e Event
	 */
	public void ReturnOnAction(Event e) {
		ViewManager.getInstance().switchViews("/View/Menu.fxml", e, new MenuController());
		
	}
	
	/***
	 * Runs before the program but after the constructor and populates the list.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		ArrayList<AuditTrailEntry> auditList = BookGateway.getInstance().getListAuditTrailEntry();

		for(int index = 0; index < auditList.size(); index++) {
			this.AuditList.getItems().add(auditList.get(index));
		}
		
	}

}
