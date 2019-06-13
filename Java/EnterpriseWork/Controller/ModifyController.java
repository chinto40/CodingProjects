package Controller;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import com.sun.javafx.event.EventHandlerManager;

import Gateway.BookGateway;
import Models.Author;
import Models.Book;
import Models.ViewManager;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

public class ModifyController implements EventHandler, Initializable{

	@FXML Button UpdateId;
	@FXML Button DeleteId;
	@FXML Button CreateId;
	
	@FXML ListView listId;
	@FXML TextField idText;
	@FXML TextField FNameId;
	@FXML TextField LNameId;
	@FXML TextField genderId;
	@FXML TextField dobId;
	@FXML Button set;
	@FXML Label updateLabel;
	@FXML Label MainLabelId;
	
	@FXML Button CancleId;
	String original = "Current Authors In System";
	
	private ArrayList<Author> aut = new ArrayList<Author>();
	
	enum choice {NULL,Create, Update,Delete};
	choice isYours = choice.NULL;
	
	public ModifyController() {
		aut = BookGateway.getInstance().getListAuthorFromAuthorAA();
		
	}
	
	public void UpdateOnAction() {
		this.isYours = choice.Update;
		//toggleDisplay(true);
		toggleButtons(false);
		this.MainLabelId.setText("Choose a Label to Update!!");
		//disableList(false);
		
	}
	public void disableList(boolean tog) {
		this.listId.setMouseTransparent(tog);
		//this.listId.setFocusTraversable((!tog));
	}
	public void clearTextfield() {
		this.FNameId.setText("");
		this.LNameId.setText("");
		this.genderId.setText("");
		this.dobId.setText("");
	}
	
	public void setTextfield() {
		this.FNameId.setText(BookGateway.authorSelected.getFirstName());
		this.LNameId.setText(BookGateway.authorSelected.getLastName());
		this.genderId.setText(BookGateway.authorSelected.getGender());
		this.dobId.setText(String.valueOf(BookGateway.authorSelected.getDateOfBirth()));
	}
	
	public void toggleButtons(boolean tog) {
		if(BookGateway.AccessLevel > 1) {
			this.CreateId.setVisible(tog);
			if(BookGateway.AccessLevel > 2) {
				this.DeleteId.setVisible(tog);
			}
			this.UpdateId.setVisible(tog);
		}
	}
	
	public boolean isAuthenticated() {
		if(this.dobId.getText().length() > 0 && 
		   this.FNameId.getText().length() > 0 && 
		   this.LNameId.getText().length() > 0 &&
		   this.genderId.getText().length() > 0){
			   return true;
		   }
				
		return false;
	}
	
	public void ReturnOnAction(Event e) {
		ViewManager.getInstance().switchViews("/View/BookListView.fxml", e, new BookListController());
	}
	
	
	public void message(String mess) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Error: >:|");
		//alert.setHeaderText("String too long..");
		alert.setContentText(mess);
		
		ButtonType Ok = new ButtonType("Ok");
		
		alert.getButtonTypes().setAll(Ok);
		Optional<ButtonType> event = alert.showAndWait();
		if(event.get() == Ok) {
			// Event Click... 
		}
	}
	
	public void SetOnAction(Event e) {
		if(this.isYours == choice.Create) {
			if(isAuthenticated()) {
				//create
				BookGateway.getInstance().insertAuthorAA(this.FNameId.getText(),this.LNameId.getText(), Date.valueOf(this.dobId.getText()),this.genderId.getText(),null);
				toggleDisplay(false);
				toggleButtons(true);
				isYours = choice.NULL;
				this.reinitList();
			}else {
				message("All fields must be overwritten!!");
				e.consume();
			}
			
		}else {
			
			if(isAuthenticated()) {
				//create
				BookGateway.getInstance().UpdateAuthorAA(BookGateway.authorSelected.getId(),this.FNameId.getText(),this.LNameId.getText(), Date.valueOf(this.dobId.getText()),this.genderId.getText(),null);
				toggleDisplay(false);
				//disableList(true);
				toggleButtons(true);
				MainLabelId.setText(original);
				isYours = choice.NULL;
				reinitList();
			}else {
				message("All fields must be overwritten!!");
				e.consume();
			}
		}
	}
	
	
	public void DeleteOnAction(Event e) {
		toggleButtons(false);
		this.isYours = choice.Delete;
		this.MainLabelId.setText("Choose a Label to Delete!!");
		//disableList(false);
		// change labels
	}
	
	public void CreateOnAction(Event e) {
		this.clearTextfield();
		this.isYours = choice.Create;
		toggleDisplay(true);
		toggleButtons(false);
	}
	
	public void CancleOnAction(Event e) {
		toggleButtons(true);
		this.isYours = choice.NULL;
		this.MainLabelId.setText(this.original);
		
	}
	
	public void toggleDisplay(boolean val) {
		boolean reverse = !val;
		this.FNameId.setDisable(reverse);
		this.LNameId.setDisable(reverse);
		this.genderId.setDisable(reverse);
		this.dobId.setDisable(reverse);
		this.updateLabel.setDisable(reverse);
		this.set.setDisable(reverse);
		
		
		this.dobId.setVisible(val);
		this.set.setVisible(val);
		this.updateLabel.setVisible(val);
		this.FNameId.setVisible(val);
		this.LNameId.setVisible(val);
		this.genderId.setVisible(val);
		
	}
	
	
	
	
	@Override
	public void handle(Event event) {
		// TODO Auto-generated method stub
		
	}
	
	public void reinitList() {
		aut = BookGateway.getInstance().getListAuthorFromAuthorAA();
		this.listId.getSelectionModel().clearSelection();
		for(int index = 0; index < this.aut.size(); index++) {
			//BookList.getItems().add(this.lib.getListOfBooks().get(index).toString());
				listId.getItems().add(
						this.aut.get(index));
			//System.out.println(this.lib.getListOfBooks().get(index).toString());
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	
		for(int index = 0; index < this.aut.size(); index++) {
			//BookList.getItems().add(this.lib.getListOfBooks().get(index).toString());
				listId.getItems().add(
						this.aut.get(index));
			//System.out.println(this.lib.getListOfBooks().get(index).toString());
		}
		//anon class
		listId.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {	
				
				if(isYours == choice.Update) {
					BookGateway.authorSelected = (Author)listId.getSelectionModel().getSelectedItem();
					setTextfield();
					toggleDisplay(true);
					//toggleButtons(true);
				}else if(isYours == choice.Delete){
					BookGateway.authorSelected = (Author)listId.getSelectionModel().getSelectedItem();
					BookGateway.getInstance().DeleteAuthorAA(BookGateway.authorSelected.getId());
					//disableList(true);
					reinitList();
					MainLabelId.setText(original);
					isYours = choice.NULL;
					toggleButtons(true);
				}else {
					e.consume();
				}
				
			}
			
		});
		
		System.out.println("*****************" + BookGateway.AccessLevel);
		
		if(BookGateway.AccessLevel == 2) {
			this.DeleteId.setVisible(false);
			this.DeleteId.setDisable(true);
		}else if(BookGateway.AccessLevel == 1) {
			this.DeleteId.setVisible(false);
			this.DeleteId.setDisable(true);
			this.UpdateId.setDisable(true);
			this.UpdateId.setVisible(false);
			this.CreateId.setDisable(true);
			this.CreateId.setVisible(false);
			
		}
	}

}
