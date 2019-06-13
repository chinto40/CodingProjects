package application;
	
import java.net.URL;
import Controller.MenuController;
import Models.ThreadingCount;
import Models.ViewManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

/**
 * Main Start of this application. 
 * @author Jacinto Molina -- CS 4743 Assignment3
 *
 */
public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		
		//Event SetOnCloseReqquest here to set an anonymous class here to use with the app closing... 
		
		ViewManager.getInstance().startView("/View/Menu.fxml", primaryStage, new MenuController());
	
	}
	
	public static void main(String[] args) {
		try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				
				Thread t = new Thread(new ThreadingCount(1,null));
				t.start();
				
			launch(args);
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Error: main");
				}
			//System.out.println((0.00025 * 10000));
			//System.out.println((float)(2.5 / 1000));
			
			/*Insert INTO author_book (author_id,book_id,royalty) VALUES (10,4,0.00025);*/
			
		}
}
