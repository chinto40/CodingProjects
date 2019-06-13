package Controller;

import java.awt.ActiveEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import Gateway.BookGateway;
import Models.Book;
import Models.Library;
import Models.ThreadingCount;
import Models.ViewManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *  BookList controller, controls the proper actions need when there is an Event happening in the BookList scene.
 * @author Jacinto Molina
 */
public class BookListController implements EventHandler, Initializable{
	
	/***
	 * Variables of the BookListController class. 
	 */
	@FXML
	private ListView BookList;
	
	@FXML
	private MenuItem createId;
	@FXML private MenuItem QuitId;
	private Library lib;
	private Book newBook;
	@FXML Label pageInfoId;
	@FXML Button firstPageId;
	@FXML Button lastPageId;
	@FXML Button nextPageId;
	@FXML Button previousPageId;
	
	@FXML TextField searchId;
	@FXML Button SearchBId;
	@FXML private Label nameLabelId;
	
	private int mult = 1;
	int max = 50;
	int min = 1;
	
	boolean isWild = false;
	
	/**
	 * Constructs a new BookListController Object. 
	 */
	public BookListController() {
		lib = new Library();
		newBook = new Book();
	}
	 /**
     * Action Event when a button is clicked. 
     * @param e Event occurring. 
     */
	public void handle(Event e) {
		// TODO Auto-generated method stub
		
	}
	
	public void SearchOnAction() {
		if(this.SearchBId.getText().length() > 0) {
			if(this.isWild == false) {
				this.isWild = true;
				
				// over write current list
				this.mult = 1;
				this.lib.resetBookList(BookGateway.getInstance().readWild(0, this.searchId.getText()));
				this.searchId.setDisable(true);
				Thread t = new Thread(new ThreadingCount(2,this.searchId.getText()));
				t.start();
				this.SearchBId.setText("Clear");
				
				this.BookList.getItems().clear();
				
				for(int index = 0; index < this.lib.getListOfBooks().size(); index++) {
					//BookList.getItems().add(this.lib.getListOfBooks().get(index).toString());
					//if(index < this.lib.getListOfBooks().size()) {
						BookList.getItems().add(this.lib.getListOfBooks().get(index));
						//System.out.println(this.lib.getListOfBooks().get(index).toString());
					//}
				}
				
				if(this.lib.getListOfBooks().size() == 0) {
					this.SearchBId.fire();
					messAlert("No results!","There were no result that start with: " + this.searchId.getText());
				}
				// reinit listview.
			
				// change the text of the serach button to clear
				// create a toggle to diasble search and reintint the list and the listview..
			}else{
				this.isWild = false;
				this.searchId.setDisable(false);
				this.searchId.setText("");
				this.SearchBId.setText("Search");
				// reint the original list. 
				this.mult = 1;
				this.lib.resetBookList(BookGateway.getInstance().read(0));
				Thread t = new Thread(new ThreadingCount(1, null));
				t.start();
				this.BookList.getItems().clear();
				
				for(int index = 0; index < this.lib.getListOfBooks().size(); index++) {
					//BookList.getItems().add(this.lib.getListOfBooks().get(index).toString());
					//if(index < this.lib.getListOfBooks().size()) {
						BookList.getItems().add(this.lib.getListOfBooks().get(index));
						//System.out.println(this.lib.getListOfBooks().get(index).toString());
					//}
				}
			
			}
		}else {
			// message here
			messAlert("No Pattern","Please type in a pattern to look for..");
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
	
	
	public void FirstPageOnAction(Event e) {
	
			this.mult = 1;
			getSet();
		
	
	}
	
	public void LastPageOnAction(Event e) {
		
			this.mult = (BookGateway.SizeofTables/50);
			getSet();
		
	}
	
	public void NextPageOnAction(Event e) {
	//	System.out.println((this.lib.getListOfBooks().size()/50));
		
			if(this.mult < (BookGateway.SizeofTables/50)) {
				this.mult++; //unless mult is listsize/50
			}
		 	getSet();
		
	}
	
	public void PrevoiusPageOnAction(Event e) {
		
			if(this.mult > 1) {
				this.mult--; //unless mult is one;
		 	}
		 	getSet();
		
	}
	
	
	public void getSet() {
		max = 50 * this.mult;
		min = max - 50;
		
		this.pageInfoId.setText("Content: " + (min+1) + " - "+ max);
		//this.BookList.getSelectionModel().clearSelection(); 
		this.BookList.getItems().clear();
		if(this.isWild == false) {
			this.lib.resetBookList(BookGateway.getInstance().read(min));
		}else {
			this.lib.resetBookList(BookGateway.getInstance().readWild(min, this.searchId.getText()));
		}
		
		
		for(int index = 0; index < this.lib.getListOfBooks().size(); index++) {
			//BookList.getItems().add(this.lib.getListOfBooks().get(index).toString());
			//if(index < this.lib.getListOfBooks().size()) {
				BookList.getItems().add(this.lib.getListOfBooks().get(index));
				//System.out.println(this.lib.getListOfBooks().get(index).toString());
			//}
		}
		
	}
	
	
	public void QuitOnAction() {
		BookGateway.getInstance().stopConnection();
    	System.exit(0);
	}
	
	/**
	 * Creates a Pop up window for Creating a new Book. 
	 */
	public void pop() {
		Stage mrPopo = new Stage();
		mrPopo.initModality(Modality.APPLICATION_MODAL);
		mrPopo.setTitle("Create Your new Book");
		
		TextField title = new TextField();
		title.setPromptText("Write Title of Book: Format <= 255 Characters...");
		TextField author = new TextField();
		author.setPromptText("Write Author of Book: Format <= 255 Characters...");
		TextArea summary = new TextArea();
		summary.setPromptText("Enter new Book Summary: Format <= 255");
		TextField isbn = new TextField();
		isbn.setPromptText("Write ISBN Number of Book: Format <= 20");
		TextField genre = new TextField();
		genre.setPromptText("Write Genre of Book:Format <= 255");
		TextField dop = new TextField();
		dop.setPromptText("Write Date Of Publisher of Book: Format <= 255");
		
		ComboBox pubChoices = new ComboBox();
		pubChoices.setPromptText("Publisher Options");
		pubChoices.getItems().addAll(BookGateway.getInstance().getPublisherNames());
		
		//TODO: if selected item is null then the default value will be unknown and the id index is 1
		
		Button quit = new Button("Quit");
		quit.setOnAction(new EventHandler() {
			@Override
			public void handle(Event e) {
				// TODO Auto-generated method stub
				System.out.println("Quit Clicked");
				System.out.println("Publisher is: " +pubChoices.getSelectionModel().getSelectedItem());
				mrPopo.close();
			}
			
		});
		Button save = new Button("Save");
		save.setOnAction(new EventHandler() {
			@Override
			public void handle(Event saveE) {
				// TODO Auto-generated method stub
				System.out.println("Save Clicked");
				//Book newBook = new Book(title.getText(), Integer.parseInt(isbn.getText()), author.getText(), dop.getText(), genre.getText(), summary.getText());
				
				if(summary.getText().length() > 255 || title.getText().length() > 255 || genre.getText().length() > 255 ||author.getText().length() > 255|| isbn.getText().length() > 20||dop.getText().length() > 255) {
					
					saveE.consume(); // doesnt work.
					
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Error: >:|");
					alert.setHeaderText("String too long..");
					alert.setContentText("Please rewrite your statement with charcters that follow the Format.");
					
					ButtonType Ok = new ButtonType("Ok");
					
					alert.getButtonTypes().setAll(Ok);
					Optional<ButtonType> event = alert.showAndWait();
					if(event.get() == Ok) {
						// Event Click... 
					}
						
				}else {
				//TODO: Add a check for a limit of Length for the summary..  <= 255 characters...
					
					BookGateway.getInstance().create(title.getText(), author.getText(), genre.getText(), summary.getText(), dop.getText(), isbn.getText(),BookGateway.getInstance().getPublisherID(pubChoices.getSelectionModel().getSelectedItem()+""));
					BookGateway.getInstance().getInstance().createAuditMessage("Book Added: " + title.getText(), BookGateway.getInstance().getBookID(isbn.getText()));
					mrPopo.close();
				}
			}
		});
		
		VBox layout = new VBox(8);
		layout.getChildren().addAll(title,author,summary,isbn,genre,dop,pubChoices,quit,save);
		layout.setAlignment(Pos.CENTER);
		Scene s = new Scene(layout,400,400);
		mrPopo.setScene(s);
		mrPopo.showAndWait();	
	}
	
	 /**
     * Action Event when a button is clicked. 
     * @param e Event occurring. 
     */
	public void MenuOnAction(Event e) {
		ViewManager.getInstance().switchViews("/View/Menu.fxml", e, new MenuController());
	}
	
	/**
	 * Create on action button event system. 
	 * @param e Event. 
	 */
	public void CreateOnAction(Event e) {
		
		// pop up a window
		//pop();
		// need to reinit the list. 
		//this.lib = new Library();
		//this.BookList.getItems().clear();
		/*for(int index = 0; index < this.lib.getListOfBooks().size(); index++) {
			//BookList.getItems().add(this.lib.getListOfBooks().get(index).toString());
			BookList.getItems().add(this.lib.getListOfBooks().get(index));
			//System.out.println(this.lib.getListOfBooks().get(index).toString());
		}*/
		//Book theMagic = new Book();
		//ViewManager.getInstance().switchViews("/View/BookDetailView.fxml", e, new BookDetailController(theMagic,1));
		//System.out.println("On action for Create");
		ViewManager.getInstance().switchViews("/View/BookDetailCreate.fxml", e, new BookCreateControllers());
		
		
		
	}
	
	public void AuthorOnAction(Event e) {
		ViewManager.getInstance().switchViews("/View/ModifyAuthor.fxml", e, new ModifyController());
		
	}
	
	/**
	 * initializes the variable after being created. 
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.nameLabelId.setText(BookGateway.currentUser.getName());
		System.out.println("INIT!!");
		for(int index = 0; index < 50; index++) {
			//BookList.getItems().add(this.lib.getListOfBooks().get(index).toString());
			if(index < this.lib.getListOfBooks().size()) {
				BookList.getItems().add(this.lib.getListOfBooks().get(index));
			}
			//System.out.println(this.lib.getListOfBooks().get(index).toString());
		}
		//anon class
		BookList.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {	
				BookGateway.bookSelected = (Book)BookList.getSelectionModel().getSelectedItem();
				ViewManager.getInstance().switchViews("/View/BookDetailView.fxml", e, new BookDetailController((Book)BookList.getSelectionModel().getSelectedItem(), 0));
			}
			
		});
	}
}
