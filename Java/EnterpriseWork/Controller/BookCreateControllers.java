package Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import Gateway.BookGateway;
import Models.Author;
import Models.Book;
import Models.ViewManager;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
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

public class BookCreateControllers implements EventHandler, Initializable{

		
		@FXML
	    private Button ReturnB;
		@FXML
		private Button Createid;
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
	    private ComboBox PubID;
	    
	    
	    @FXML MenuItem AddAuthid;
	    @FXML MenuItem DeleteAuthid;
	    
	    
	    ArrayList<String> listOfAuthor;
	    ArrayList<Double> listOfPercentage;
	    
	    @FXML
	    private ListView AuthorListid;
	    
	    public BookCreateControllers() {
	    	this.listOfAuthor = new ArrayList<String>();
	    	this.listOfPercentage = new ArrayList<Double>();
	    }
	    
		@Override
		public void handle(Event arg0) {
			// TODO Auto-generated method stub
			
		}
		
		public void DeleteAuthOnAction(Event e) {
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
			
			Button create = new Button("Create");
			create.setOnAction(new EventHandler() {

				@Override
				public void handle(Event arg0) {
					// TODO Auto-generated method stub
					// here i would need to create a new author...
					
					
					
					
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
		
		public void AddAuthOnAction(Event e) {
			System.out.println("in Add ");
			System.out.println("Adds Author");
			// retrieve authro list. 
			
			// display list in a list view.. to be selected and added to the list. 
			
			Stage mrPopo = new Stage();
			mrPopo.initModality(Modality.APPLICATION_MODAL);
			mrPopo.setTitle("Create Your new Book");
			
			//ListView 
			ListView list = new ListView();
			
			//TODO: if selected item is null then the default value will be unknown and the id index is 1
			ArrayList<Author> authors = BookGateway.getInstance().getListAuthorFromAuthorAA();
			
			
			if(this.listOfAuthor.size() > 0) {
				for(int index = 0; index < this.listOfAuthor.size(); index++) {
					for(int i = 0;i < authors.size(); i++){
						if(this.listOfAuthor.get(index).equals(authors.get(i).getName())){
							authors.remove(i);
						}
					}
				}
			}
			
			
			for(int index = 0; index < authors.size(); index++) {
				list.getItems().add(authors.get(index).getName());
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
			
			//System.out.println(bookDetailClicked.getAuthor());
			//this.TextAuthor.setText(this.bookDetailClicked.getAuthor()+"");
			//BookGateway.getInstance().updateOneCol("Author", this.bookDetailClicked.getAuthor(), this.bookDetailClicked.getBookIsbn()+"");	
			
		}

		public boolean authenticateChanges() {
			
			//check strings
			
			System.out.println("Changes: "+this.PubID.getValue());
			
			if( (this.TextAreaid.getText().length() > 0)  && (this.TextTitle.getText().length() > 0) &&
				 (this.TextDatePublished.getText().length() > 0) &&
				(this.TextGenre.getText().length() >  0)  && (this.TextISBN.getText().length() > 0) &&
				(this.listOfAuthor.size() > 0) && (this.PubID.getValue() != null)) { // need to check publisher
					return true;
			}
			return false;
		}
		
		@FXML
		public void CreateOnAction(Event e) {
			System.out.println("In Create!!");
			// have to check if all textfields have been modified.  if true then changes made to all the text stuff..
			System.out.println(authenticateChanges());
			if(authenticateChanges()) {
				String author = "";
				for(int index = 0; index < listOfAuthor.size();index++) {
					author += listOfAuthor.get(index);
					if((index+1) != listOfAuthor.size()) {
						author += ",";
					}
				}
				
				
				// then all field were changed.
				BookGateway.getInstance().create(this.TextTitle.getText(), author, this.TextGenre.getText(), this.TextAreaid.getText(), this.TextDatePublished.getText(), this.TextISBN.getText(),BookGateway.getInstance().getPublisherID(this.PubID.getSelectionModel().getSelectedItem()+""));
				BookGateway.getInstance().getInstance().createAuditMessage("Book Added: " + this.TextTitle.getText(), BookGateway.getInstance().getBookID(this.TextISBN.getText()));
				
				int b = BookGateway.getInstance().getBookID(this.TextISBN.getText());
				
				//listOfAuthor.add(list.getSelectionModel().getSelectedItem()+"");
				//listOfPercentage.add(Double.valueOf(tf.getText()));
				
				for(int index = 0; index < listOfAuthor.size(); index++) {
					//BookList.getItems().add(this.lib.getListOfBooks().get(index).toString());
					//AuthorListid.getItems().add(listOfAuthor.get(index) + ", " + listOfPercentage.get(index)+"%");
					BookGateway.getInstance().CreateAuthorBook(listOfAuthor.get(index), listOfPercentage.get(index), b);
					//System.out.println(this.lib.getListOfBooks().get(index).toString());
				}
				
				ViewManager.getInstance().switchViews("/View/BookListView.fxml", e, new BookListController());
			}else {
				// if so then create new Book. 
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Error: >:|");
				//alert.setHeaderText("String too long..");
				alert.setContentText("All fields must be overwritten... ");
				
				ButtonType Ok = new ButtonType("Ok");
				
				alert.getButtonTypes().setAll(Ok);
				Optional<ButtonType> event = alert.showAndWait();
				if(event.get() == Ok) {
					// Event Click... 
				}
			}
		}
		
		@FXML
		public void ReturnBOnAction(Event e) {
			System.out.println("In Return!!");
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
    		alert.setTitle("Warning ");
    		alert.setHeaderText("Are you sure you want to leave, you will lose any input data not created.");
    		//alert.setContentText("");
    		
    		ButtonType Yes = new ButtonType("Yes");
    		ButtonType cancle = new ButtonType("Cancle");
    		
    		alert.getButtonTypes().setAll(Yes, cancle);
    		Optional<ButtonType> event = alert.showAndWait();
    		if(event.get() == Yes) {
    			System.out.println("Pressed Update button");
    			ViewManager.getInstance().switchViews("/View/BookListView.fxml", e, new BookListController());
    			
    		}else if(event.get() == cancle) {
    			e.consume();
    		}
			
		}
		
		

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			
			//Publisher
			ArrayList<String> pubName = BookGateway.getInstance().getPublisherNames();
			
			for(int index = 0; index < pubName.size(); index++) {
				this.PubID.getItems().add(BookGateway.getInstance().getPublisherNames().get(index));
			}
			
		}

	
}
