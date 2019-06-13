package Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Gateway.BookGateway;
import Models.AuditTrailEntry;
import Models.AuthorBook;
import Models.Book;
import Models.ViewManager;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *  MeBookDetail controller, controls the proper actions need when there is an Event happening in the BookDetail view scene.
 * @author Jacinto Molina
 *
 */
//-----------------------------------------------------------------------------------------------------
/*
 * TODO: for creation of new Book i need to hide some buttons Audit button and restore them when i save the book .
 * I need to take of the Delete button... ill keep the Return to go back and keep the save button. maybe keep just change the text of the update button.. 
 */
//-----------------------------------------------------------------------------------------------------

public class BookDetailController implements EventHandler, Initializable {

	/**
	 * Variable found within the BookDetailController view. 
	 */
	Book bookDetailClicked;

    @FXML
    private Button ReturnB;
    
    @FXML
    private Button DeleteId;
    
    @FXML
    private Button UpdateId;
    
    @FXML 
    private Button AuditId;
    
    @FXML
    private Button save;
    
    @FXML
    private MenuButton authListId;
    
    
    @FXML 
    private TextArea TextAreaid;
    
    @FXML 
    private TextField TextTitle;
    @FXML
    private TextField TextSummary;
    @FXML
    private TextField TextDatePublished;
    @FXML
    private TextField TextGenre;
    @FXML
    private TextField TextISBN;
    @FXML
    private TextField TextAuthor;
    
    @FXML
    private TextField TextPub;
    
    @FXML 
    private ComboBox PubID;

    @FXML
    private MenuButton AuthorEditID;
    
    @FXML ListView AuthorListid;
    
    
    private boolean isCreate;
    private boolean check;
    private ArrayList<String> authList;
    private boolean isEditable;
    private int levelSaveCheck;
    private boolean afterFirst;
    
    ArrayList<String> listOfAuthor = new ArrayList<String>();
	ArrayList<Double> listOfPercentage = new ArrayList<Double>();
    /**
     * Constructs a new BookDetailController object. 
     * @param book - The book being passed in from the Event. 
     */
	BookDetailController(Book book ,int toggle){
		bookDetailClicked = book;
		this.isEditable = false;
		this.levelSaveCheck = 0;
		this.afterFirst = true;
		/*if(toggle == 0) {
			this.isCreate = false;
		}else {
			this.isCreate = true;
			//this.AuditId.setVisible(false);
			// Its the buttons that are Coming in as NULL so its breaking the program.. 
			//this.AuditId.setDisable(true);
			//this.DeleteId.setDisable(true);
			//this.UpdateId.setDisable(true);
			this.save.setDisable(false);
		}*/
		this.authList = BookGateway.getInstance().getListAuthorFromBooks();
		ViewManager.getInstance().myStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent e) {
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Work not Saved! >:| ");
				alert.setHeaderText("Would you like to Save Your work?");
				//alert.setContentText("");
				
				ButtonType No = new ButtonType("No");
				ButtonType Yes = new ButtonType("Yes");
				ButtonType cancle = new ButtonType("Cancle");
				
				alert.getButtonTypes().setAll(Yes, No, cancle);
				Optional<ButtonType> event = alert.showAndWait();
				if(event.get() == Yes) {
					System.out.println("Pressed Update button");
					
					// see if the version match
					if(BookGateway.getInstance().checkTimeStamp(BookGateway.getInstance().bookSelected.getTimeStamp()+"" , BookGateway.getInstance().bookSelected.getBookIsbn()+"")) {
						//match so proceed with update
							save.fire();
					}else {
						// dont match.. 
						couldntUpdatePop();
					}			
					
					//this.ReturnB.fire();
				}else if(event.get() == No) {
					System.out.println("Pressed Quit Button");
					
					// quit the applications. 
				}else if(event.get() == cancle) {
					e.consume();
				}
			}
		});
	}
	
	public BookDetailController() {
		// create a new Book 
		this.bookDetailClicked = new Book();
		this.isCreate = true;
	}
	
	/**
	 * Toggles a button on and off.
	 * @param b The button being toggled.
	 * @param on The value determining if the button will be on or off. 
	 */
	public void toggleButton(Button b, boolean on) {
		if(BookGateway.AccessLevel > 1) {
		
		if(on == true) {
			b.setVisible(true);
			b.setDisable(false);
		}else {
			b.setVisible(false);
			b.setDisable(true);
		}
		}
	}
	
	/**
	 * Checks to see if there are any changes to be stored in the Audit database. 
	 * @param start The field being changed.
	 * @param bookMsg the original book msg. 
	 * @param msg  the msg being saved.
	 * @return String of the message being added. 
	 */
	public String checkAuditString(String start,String bookMsg, String msg) {
		if(bookMsg.equals(msg)) {
			return "";
		}
		String changes = "Changed " + start + ",\n to: " + msg +"\n";
		this.check =true;
		return changes;
	}
	
	
	/**
	 * Adds a pop window with all the audit changes for a specific book. 
	 * @param e Event handler for the Button click. 
	 */
	public void AuditOnAction(Event e) {
		this.isEditable = this.UpdateId.isVisible();	
		boolean isCancle = false;
		
		if(this.isEditable == false) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Work not Saved! >:| ");
		alert.setHeaderText("Would you like to Save Your work?");
		//alert.setContentText("");
		
		ButtonType No = new ButtonType("No");
		ButtonType Yes = new ButtonType("Yes");
		ButtonType cancle = new ButtonType("Cancle");
		
		alert.getButtonTypes().setAll(Yes, No, cancle);
		Optional<ButtonType> event = alert.showAndWait();
		if(event.get() == Yes) {
			System.out.println("Pressed Update button");
			
			// see if the version match
			if(BookGateway.getInstance().checkTimeStamp(BookGateway.getInstance().bookSelected.getTimeStamp()+"" , BookGateway.getInstance().bookSelected.getBookIsbn()+"")) {
				//match so proceed with update
				this.levelSaveCheck = 0;	
				save.fire();
					
			}else {
				// dont match.. 
				couldntUpdatePop();
			}			
			
			//this.ReturnB.fire();
		}else if(event.get() == No) {
			System.out.println("Pressed Quit Button");
			this.levelSaveCheck = 1;
			this.save.fire();
			
			// quit the applications. 
		}else if(event.get() == cancle) {
			e.consume();
			isCancle = true;
		}
		}
		
		if (isCancle == false){
			Stage mrPopo = new Stage();
			mrPopo.initModality(Modality.APPLICATION_MODAL);
			mrPopo.setTitle("Create Your new Book");
			
			//ListView 
			ListView list = new ListView();
			
			//TODO: if selected item is null then the default value will be unknown and the id index is 1
			ArrayList<AuditTrailEntry> auditList = BookGateway.getInstance().getListAuditTrailEntry(this.bookDetailClicked.getBookID()+"");
			
			for(int index = 0; index < auditList.size(); index++) {
				list.getItems().add(auditList.get(index));
			}
			if(auditList.size() == 0) {
				list.getItems().add("No changes on record for were made to this book!!!.");
			}
			Button ok = new Button("Ok");
			ok.setOnAction(new EventHandler() {
				@Override
				public void handle(Event e) {
					// TODO Auto-generated method stub
					System.out.println("ok Clicked");
					//System.out.println("Publisher is: " +pubChoices.getSelectionModel().getSelectedItem());
					mrPopo.close();
				}
				
			});
			VBox layout = new VBox(8);
			layout.getChildren().addAll(list,ok);
			layout.setAlignment(Pos.CENTER);
			Scene s = new Scene(layout,400,400);
			mrPopo.setScene(s);
			mrPopo.showAndWait();
		}
	}
	
	/**
	 * Checks to see if there was any changes while updating and uploads it to the database. 
	 */
	public void checkAudit() {
		String overall = "";
		overall += checkAuditString("Summary",this.bookDetailClicked.getSummary(),this.TextAreaid.getText());
		overall += checkAuditString("Title",this.bookDetailClicked.getBookTitle() ,this.TextTitle.getText());
		overall += checkAuditString("Date Of Publication",this.bookDetailClicked.getDateOfPublication(),this.TextDatePublished.getText());
		overall += checkAuditString("Genre",this.bookDetailClicked.getGenre(),this.TextGenre.getText());
		overall += checkAuditString("Author",this.bookDetailClicked.getAuthor(),this.TextAuthor.getText());
		
		if(this.check == true) {
			BookGateway.getInstance().createAuditMessage(overall,BookGateway.getInstance().getBookID(this.bookDetailClicked.getBookIsbn()+""));
		}// else no changes...
		this.check = false;
	}
	
	/**
	 * Toggles Editable field on or off. 
	 * @param toggle The value determining if it will be on or off. 
	 */
	public void toggleEditables(boolean toggle) {
		// toggle editable fields to writable... 
		this.TextAreaid.setEditable(toggle);
		this.TextTitle.setEditable(toggle);
		this.TextSummary.setEditable(toggle);
		this.TextDatePublished.setEditable(toggle);
		this.TextGenre.setEditable(toggle);
		this.TextAuthor.setEditable(toggle);
		this.TextPub.setEditable(toggle);
		this.TextISBN.setEditable(toggle);
		
		if(this.UpdateId.isVisible() == true) {
			this.PubID.setDisable(true);
			this.TextPub.setDisable(false);
			this.PubID.setVisible(false);
			this.TextPub.setVisible(true);
			if(!afterFirst) {
				this.authListId.setDisable(true);
				this.authListId.setVisible(false);
			}
			
		}else {
			this.PubID.setDisable(false);
			this.TextPub.setDisable(true);
			this.PubID.setVisible(true);
			this.TextPub.setVisible(false);
			
			if(!afterFirst) {
				this.authListId.setDisable(false);
				this.authListId.setVisible(true);
			}
		}
	}
    
	/*
	 * Updates on action event. 
	 */
	@FXML
	public void UpdateOnAction(Event e) {
		//save button...
		if(BookGateway.getInstance().checkTimeStamp(this.bookDetailClicked.getTimeStamp()+"",this.bookDetailClicked.getBookIsbn()+"")) {
			toggleButton(save,true);
			toggleButton(UpdateId,false);
			
			if(BookGateway.AccessLevel >2) {
				toggleButton(DeleteId,false);
			}
			
			// make all texts field editable...
			//this.TextPub.setDisable(false);
			toggleEditables(true);
		}else {
			couldntUpdatePop();
		}
		
		// change the text of the main tile from book chosen to Edit book for changes.. 
	}
		
	/**
	 * Create a pop window alert system for update handle. 
	 */
	public void couldntUpdatePop() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Update Failed");
		alert.setHeaderText("Application Not Updated: Out of Sync");
		alert.setContentText("Would you like to Update or Quit?");
		
		ButtonType update = new ButtonType("Update");
		ButtonType quit = new ButtonType("Quit");
		
		alert.getButtonTypes().setAll(update, quit);
		Optional<ButtonType> event = alert.showAndWait();
		if(event.get() == update) {
			System.out.println("Pressed Update button");
			this.ReturnB.fire();
		}else if(event.get() == quit) {
			System.out.println("Pressed Quit Button");
			// quit the applications. 
		}	
    }
	
	/**
	 * Deletes on action. 
	 * @param e Event. 
	 */
	@FXML
	public void DeleteOnAction(Event e) {
		//Run Delete Code from Database..
		// delete from database...
		
		BookGateway.getInstance().delete(this.TextISBN.getText());
		this.ReturnB.fire();
		System.out.println("Database Updated...");
	}
	
	

	 /**
     * Action Event when a button is clicked. 
     * @param e Event occurring. 
     */
    @FXML
    void ReturnBOnAction(Event e) {
    	this.isEditable = this.UpdateId.isVisible();
    	boolean isCancle = false;
    	if(this.isEditable == false) {
    		Alert alert = new Alert(AlertType.CONFIRMATION);
    		alert.setTitle("Work not Saved! >:| ");
    		alert.setHeaderText("Would you like to Save Your work?");
    		//alert.setContentText("");
    		
    		ButtonType No = new ButtonType("No");
    		ButtonType Yes = new ButtonType("Yes");
    		ButtonType cancle = new ButtonType("Cancle");
    		
    		alert.getButtonTypes().setAll(Yes, No, cancle);
    		Optional<ButtonType> event = alert.showAndWait();
    		if(event.get() == Yes) {
    			System.out.println("Pressed Update button");
    			
    			// see if the version match
    			if(BookGateway.getInstance().checkTimeStamp(BookGateway.getInstance().bookSelected.getTimeStamp()+"" , BookGateway.getInstance().bookSelected.getBookIsbn()+"")) {
    				//match so proceed with update
    				this.levelSaveCheck = 0;	
    				save.fire();
    			}else {
    				// dont match.. 
    				couldntUpdatePop();
    			}			
    			
    			//this.ReturnB.fire();
    		}else if(event.get() == No) {
    			System.out.println("Pressed Quit Button");
    			this.levelSaveCheck = 1;
    			save.fire();
    			
    			// quit the applications. 
    		}else if(event.get() == cancle) {
    			e.consume();
    			isCancle = true;
    		}
    	}
    	
    	if(isCancle == false) {
    		ViewManager.getInstance().switchViews("/View/BookListView.fxml", e, new BookListController());
    	}
    }

    /**
     * Action Event when a button is clicked. 
     * @param e Event occurring. 
     */
    @FXML
    void saveOnAction(Event event) {
    	
    	if(this.levelSaveCheck == 0) {
	    	toggleButton(save,false);
			toggleButton(UpdateId,true);
			if(BookGateway.AccessLevel > 2) {
				toggleButton(DeleteId,true);
			}
			
			checkAudit();
			System.out.println(":: " + BookGateway.getInstance().getPublisherID((this.PubID.getSelectionModel().getSelectedIndex()+1)+""));
			BookGateway.getInstance().update("Books", this.TextTitle.getText(), this.TextAuthor.getText(), this.TextGenre.getText(), this.TextAreaid.getText(), this.TextDatePublished.getText(), this.TextISBN.getText(),   (this.PubID.getSelectionModel().getSelectedIndex()+1)   ,this.bookDetailClicked.getBookIsbn());
			
			String author = "";
			for(int index = 0; index < listOfAuthor.size();index++) {
				author += listOfAuthor.get(index);
				if((index+1) != listOfAuthor.size()) {
					author += ",";
				}
			}
			
			for(int index = 0; index < listOfAuthor.size(); index++) {
				//BookList.getItems().add(this.lib.getListOfBooks().get(index).toString());
				//AuthorListid.getItems().add(listOfAuthor.get(index) + ", " + listOfPercentage.get(index)+"%");
				
				BookGateway.getInstance().deleteAuthorBook(listOfAuthor.get(index),BookGateway.getInstance().getBookID(this.TextISBN.getText()));
				
				//System.out.println(this.lib.getListOfBooks().get(index).toString());
			}
			
			for(int index = 0; index < listOfAuthor.size(); index++) {
				//BookList.getItems().add(this.lib.getListOfBooks().get(index).toString());
				//AuthorListid.getItems().add(listOfAuthor.get(index) + ", " + listOfPercentage.get(index)+"%");
				
				BookGateway.getInstance().CreateAuthorBook(listOfAuthor.get(index), listOfPercentage.get(index),BookGateway.getInstance().getBookID(this.TextISBN.getText()));
				
				//System.out.println(this.lib.getListOfBooks().get(index).toString());
			}
			
			
			BookGateway.getInstance().updateOneCol("Author", author, this.bookDetailClicked.getBookIsbn()+"");
			
			Book b = BookGateway.getInstance().readBook(this.bookDetailClicked.getBookIsbn() + "");
			
			//System.out.println("In save: "+b.getAuthor());
			
			this.bookDetailClicked.setTimestamp(BookGateway.getInstance().getTimeStamp(this.bookDetailClicked.getBookIsbn()+""));
			//this.bookDetailClicked.setTimestamp(b.getTimeStamp());
			
			
			
			System.out.println(this.PubID.getSelectionModel().getSelectedItem()+"");
			this.TextPub.setText(this.PubID.getSelectionModel().getSelectedItem() + "");
			
	    	System.out.println("Book Saved....\n");
	    	System.out.println("Database Updated...");
	    	
	    	
	    	System.out.println("Create the Book...");
	    	//BookGateway.getInstance().create(this.TextTitle.getText(), this.TextAuthor.getText(), this.TextGenre.getText(), this.TextSummary.getText(), this.TextDatePublished.getText(), this.TextISBN.getText(),BookGateway.getInstance().getPublisherID(this.PubID.getSelectionModel().getSelectedItem()+""));
			//BookGateway.getInstance().getInstance().createAuditMessage("Book Added: " + this.TextTitle.getText(), BookGateway.getInstance().getBookID(this.TextISBN.getText()));
			
			toggleEditables(false);
			//this.ReturnB.fire();
    	}else {
    		toggleButton(save,false);
			toggleButton(UpdateId,true);
			toggleButton(DeleteId,true);
			
    	}
    }
    
   
    /**
     * Action Event when a button is clicked. 
     * @param e Event occurring. 
     */
	@Override
	public void handle(Event event) {
		// TODO Auto-generated method stub
		
	}
	
	public String formatString(String line,int numOfChar) {
		String catLine = "";
		int lineIndex = 0;
		int split = numOfChar;
		return line;
	}
	
	
	
	
	
	@FXML
	public void AddOnAction(Event e) {
		System.out.println("Adds Author");
		// retrieve authro list. 
		
		// display list in a list view.. to be selected and added to the list. 
		Stage mrPopo = new Stage();
		mrPopo.initModality(Modality.APPLICATION_MODAL);
		mrPopo.setTitle("Create Your new Book");
		
		//ListView 
		ListView list = new ListView();
		
		//TODO: if selected item is null then the default value will be unknown and the id index is 1
		ArrayList<String> authors = BookGateway.getInstance().getListAuthorFromBooks();
		
		for(int index = 0; index < authors.size(); index++) {
			list.getItems().add(authors.get(index));
		}
		if(authors.size() ==0) {
			list.getItems().add("No authors Found!!!.");
		}
		
		
		// add and eventlist listeneer for selections model.. 
		list.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {	
				//BookGateway.bookSelected = (Book)BookList.getSelectionModel().getSelectedItem();
				//bookDetailClicked.UpdateAuthorList(0,bookDetailClicked.getAuthor() +"," + list.getSelectionModel().getSelectedItem());
				
				// add a small textbox to add a value.. 
				
				Stage m = new Stage();
				m.initModality(Modality.APPLICATION_MODAL);
				m.setTitle("Choose percentage from 1-100");
				Label n = new Label();
				n.setText("Choose percentage from 1-100");
				TextField tf = new TextField();
				tf.setPromptText("Percentage 1 -100");
				Button create = new Button();
				create.setText("Ok");
				
				create.setOnAction(new EventHandler() {

					@Override
					public void handle(Event arg0) {
						// TODO Auto-generated method stub
						
						if(Double.valueOf(tf.getText()) < 100 && Double.valueOf(tf.getText()) > 0) {
							listOfAuthor.add(list.getSelectionModel().getSelectedItem()+"");
							listOfPercentage.add(Double.valueOf(tf.getText()));
							AuthorListid.getItems().clear();
							for(int index = 0; index < listOfAuthor.size(); index++) {
								//BookList.getItems().add(this.lib.getListOfBooks().get(index).toString());
								AuthorListid.getItems().add(listOfAuthor.get(index) + ", " + listOfPercentage.get(index)+"%");
								//System.out.println(this.lib.getListOfBooks().get(index).toString());
							}
							
							// update authors list.  
							mrPopo.close();
							m.close();
						}else {
							arg0.consume();
						}
					}
					
				});
				
				VBox la = new VBox(8);
				la.getChildren().addAll(n,tf,create);
				la.setAlignment(Pos.CENTER);
				Scene sf = new Scene(la,150,150);
				m.setScene(sf);
				m.showAndWait();
				
			}
			
		});
		
		Button ok = new Button("Ok");
		ok.setOnAction(new EventHandler() {
			@Override
			public void handle(Event e) {
				// TODO Auto-generated method stub
				System.out.println("ok Clicked");
				//System.out.println("Publisher is: " +pubChoices.getSelectionModel().getSelectedItem());
				mrPopo.close();
			}
			
		});
		VBox layout = new VBox(8);
		layout.getChildren().addAll(list,ok);
		layout.setAlignment(Pos.CENTER);
		Scene s = new Scene(layout,400,400);
		mrPopo.setScene(s);
		mrPopo.showAndWait();	
		
		//BookGateway.getInstance().updateOneCol("Author", this.bookDetailClicked.getAuthor(), this.bookDetailClicked.getBookIsbn()+"");	
		
	}
	
	@FXML
	public void DeleteEditOnAction(Event e) {
		System.out.println("Deletes Author");
		// returives book author list. 
		
		System.out.println("In Delete");
		Stage mrPopo = new Stage();
		mrPopo.initModality(Modality.APPLICATION_MODAL);
		mrPopo.setTitle("Create Your new Book");
		
		//ListView 
		ListView list = new ListView();
		
		//TODO: if selected item is null then the default value will be unknown and the id index is 1
		//ArrayList<String> authors = this.bookDetailClicked.getAuthorList();
		
		for(int index = 0; index < listOfAuthor.size(); index++) {
			list.getItems().add(listOfAuthor.get(index));
		}
		if(listOfAuthor.size() ==0) {
			list.getItems().add("No authors Found!!!.");
		}
		
		
		// add and eventlist listeneer for selections model.. 
		list.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {	
				//BookGateway.bookSelected = (Book)BookList.getSelectionModel().getSelectedItem();
				//bookDetailClicked.UpdateAuthorList(1,list.getSelectionModel().getSelectedItem()+"");
				// update authors list. 
				for(int index = 0; index < listOfAuthor.size(); index++) {
				
					if(list.getSelectionModel().getSelectedItem().equals(listOfAuthor.get(index))) {
						listOfAuthor.remove(index);
						listOfPercentage.remove(index);
					}
				}
				
			
				AuthorListid.getItems().clear();
				for(int index = 0; index < listOfAuthor.size(); index++) {
					//BookList.getItems().add(this.lib.getListOfBooks().get(index).toString());
					AuthorListid.getItems().add(listOfAuthor.get(index) + ", " + listOfPercentage.get(index)+"%");
					//System.out.println(this.lib.getListOfBooks().get(index).toString());
				}
				
				mrPopo.close();
			}
			
		});
		
		Button ok = new Button("Ok");
		ok.setOnAction(new EventHandler() {
			@Override
			public void handle(Event e) {
				// TODO Auto-generated method stub
				System.out.println("ok Clicked");
				//System.out.println("Publisher is: " +pubChoices.getSelectionModel().getSelectedItem());
				mrPopo.close();
			}
			
		});
		VBox layout = new VBox(8);
		layout.getChildren().addAll(list,ok);
		layout.setAlignment(Pos.CENTER);
		Scene s = new Scene(layout,400,400);
		mrPopo.setScene(s);
		mrPopo.showAndWait();	
		
		System.out.println(this.bookDetailClicked.getAuthor());
		//BookGateway.getInstance().updateOneCol("Author", this.bookDetailClicked.getAuthor(), this.bookDetailClicked.getBookIsbn()+"");	
		this.TextAuthor.setText(this.bookDetailClicked.getAuthor());
	}
	


	/**
	 * initializes the variable after being created. 
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		//if(this.isCreate == false){
			this.TextTitle.setText(this.bookDetailClicked.getBookTitle()+"");
			//this.TextSummary.setText(formatString(this.bookDetailClicked.getSummary(),10));
			//this.summary.getItems().add(this.bookDetailClicked.getSummary());
			this.TextAreaid.setText(this.bookDetailClicked.getSummary()+"");
			this.TextDatePublished.setText(this.bookDetailClicked.getDateOfPublication()+"");
			this.TextAuthor.setText(this.bookDetailClicked.getAuthor()+"");
			this.TextISBN.setText(this.bookDetailClicked.getBookIsbn()+"");
			this.TextGenre.setText(this.bookDetailClicked.getGenre()+"");
			
			
			ArrayList<String> pubName = BookGateway.getInstance().getPublisherNames();
			
			for(int index = 0; index < pubName.size(); index++) {
				this.PubID.getItems().add(BookGateway.getInstance().getPublisherNames().get(index));
			
			}
			System.out.println(BookGateway.getInstance().getPublisherName(this.bookDetailClicked.getPublisherID()));
			this.TextPub.setText(BookGateway.getInstance().getPublisherName(this.bookDetailClicked.getPublisherID()) + "");
			
			this.PubID.setValue(BookGateway.getInstance().getPublisherName(this.bookDetailClicked.getPublisherID()));
			
			
			toggleEditables(false);
			this.afterFirst = false;
			
			System.out.println("ID: " + this.bookDetailClicked.getBookID());
			ArrayList<AuthorBook> game = BookGateway.getInstance().getAuthorBook(this.bookDetailClicked.getBookID());
			
			System.out.println("Size: " + game.size());
			
			for(int index = 0; index < game.size(); index++) {
				this.listOfAuthor.add(game.get(index).getAuthor().getFirstName() + " " + game.get(index).getAuthor().getLastName());
				this.listOfPercentage.add(game.get(index).getRoyalty());
			}
			
			for(int index = 0; index < listOfAuthor.size(); index++) {
				//BookList.getItems().add(this.lib.getListOfBooks().get(index).toString());
				AuthorListid.getItems().add(listOfAuthor.get(index) + ", " + listOfPercentage.get(index)+"%");
				//System.out.println(this.lib.getListOfBooks().get(index).toString());
			}
			if(BookGateway.AccessLevel == 2) {
				this.DeleteId.setDisable(true);
				this.DeleteId.setVisible(false);
			}else if(BookGateway.AccessLevel == 1) {
				this.UpdateId.setDisable(true);
				this.UpdateId.setVisible(false);
				this.DeleteId.setDisable(true);
				this.DeleteId.setVisible(false);
				this.save.setDisable(true);
				this.save.setVisible(false);
			}
		/*}else { // For creation of New Book.. 
			toggleEditables(true);
			this.TextAuthor.setEditable(false);

			ArrayList<String> pubName = BookGateway.getInstance().getPublisherNames();
			
			for(int index = 0; index < pubName.size(); index++) {
				this.PubID.getItems().add(BookGateway.getInstance().getPublisherNames().get(index));
			}
			
		
		}*/
		
		
		
	}
}
