package Controller;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Gateway.BookGateway;
import Models.ViewManager;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Menu controller, controls the proper actions need when there is an Event happening in the main menu scene. 
 * @author Jacinto Molina
 *
 */
public class MenuController implements EventHandler{
		
		@FXML 
		private MenuBar Menu;
		/**
		 * FXML Buttons for the main menu. 
		 */
		@FXML
	    private MenuItem Quitid;

		@FXML
	    private MenuItem Readid;
		
		@FXML private Button LogInId;
		@FXML private TextField userNameId;
		@FXML private TextField passwordId;
		
		@FXML private Pane whiteOutId;
	    
		@FXML private Label nameLabelId;
		
	    /**
	     * Logger for error printing to the console. 
	     */
	    private static Logger logger = LogManager.getLogger(MenuController.class);

	    /**
	     * Constructs a new MenuController object.
	     */
	    public MenuController() {
	  
	    }
	    
	    /**
	     * Action Event when a button is clicked. 
	     * @param e Event occurring. 
	     */
	    @FXML
	    public void QuitOnAction(Event e) {
	    	logger.error("Pressed quit!");
	    	BookGateway.getInstance().stopConnection();
	    	System.exit(0);
	    }
	    
	    
	    public void LogInOnAction(Event e) {
	    	// authenticate password... and user name  
	    	
	    	if(BookGateway.getInstance().getAuthentication(this.userNameId.getText(), this.passwordId.getText())) {
	    		this.whiteOutId.setVisible(false);
	    		this.whiteOutId.setDisable(true);
	    		// TODO: Update label name in the corner.. 
	    		this.nameLabelId.setText("Hello, " + BookGateway.currentUser.getFirstName());
	    	
	    	}else {
	    		// true
	    		messAlert("Invalid","Wrong Username or password");
	    		this.userNameId.setText("");
	    		this.passwordId.setText("");
	    		e.consume();
	    	}
	    	
	    	
	    }
	    
	    public void messAlert(String title, String mess) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle(title);
			
			alert.setContentText(mess);
			
			ButtonType OK = new ButtonType("Ok");
		
			
			alert.getButtonTypes().setAll(OK);
			Optional<ButtonType> event = alert.showAndWait();
			if(event.get() == OK) {
				
			}
	    }
	    
	    /**
	     * Event handle for Audit choice.
	     * @param e  the event being Evented. 
	     */
	    public void AuditOnAction(Event e) {
	    	ViewManager.getInstance().switchViews("/View/AuditTrailView.fxml", e, new AuditTrailController());
	    
	    }

	    /**
	     * Action Event when a button is clicked. 
	     * @param e Event occurring. 
	     */
	    @FXML 
	    public void ReadOnAction(Event e) {
	    	ViewManager.getInstance().switchViews("/View/BookListView.fxml", e, new BookListController());
	    }

	    /**
	     * Action Event when a button is clicked. 
	     * @param e Event occurring. 
	     */
		@Override
		public void handle(Event e) {
			// TODO Auto-generated method stub
			logger.error("Error: handle never pressed. ");
		}
}
