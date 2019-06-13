/**
 * @author Shawn & Kwan - JavaFX
 * @author Shawn - Properly implemented music, difficulty, and added toggleScene
 * @author Katherine & Jeffrey - Added slider and drop menu button
 * @author Wayne - Added game map code
 */

package application.Controller;

import java.io.IOException;

import application.Main;
import application.model.GameManager.Events;
import application.model.GameManager.GameManager;
import application.model.GameManager.KeyboardEvent;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.awt.Graphics2D;
import java.awt.Image;
//import javafx.scene.paint.Color;
import java.io.File;

public class controller implements EventHandler<ActionEvent> {
	@FXML
	private Button buttonPantheon;
	@FXML
	private Button buttonLimbus;
	@FXML
	private Button buttonPlay;	
	@FXML
	private Button buttonSettings;
	
	// Katherine & Jeffrey notification: I changed the text for volumeLable in the 
	// Setting.fxml to start off as 50
	@FXML
	private Label volumeLabel = new Label();
	@FXML
	private Slider volumeSlider = new Slider(0, 100, 50);
	
	static String textVolume = "50";
	//

	// Shawn
	public static int mode;
	public static boolean mainMenu = true;
	
	public static int numberOfLevels = 10;
	
	public static boolean verifyComplete = false;
	
	static double volume;
	
	static String songTitle = "";
	static Media sound = new Media(transictionalMusic().toURI().toString());
	public static MediaPlayer mediaPlayer = new MediaPlayer(sound);
	//
	
	// Katherine & Kwan
	@FXML
	private ChoiceBox<String> difficulty = new ChoiceBox<String>();
	//
	
	// Jacinto
	private static boolean pause = false;

	private static int frameX = 832;
	private static int frameY = 832;
	//
	
	// Shawn
	public static boolean toggle = false;
	
	public static boolean toggleScene() {	
		return toggle;
	}
	//
	
	private void handleStageScene(Parent root, ActionEvent event) throws IOException {
		/*// Get the source of the event
		Object eventSource = pantheon.getSource(); 

		// The event only knows its source is some kind of object, however, we
		// registered this listener with a button, which is a Node, so we know
		// the actual runtime type of the source must be Button (which is a Node)
		// So tell the compiler we are confident we can treat this as a Node:

		Node sourceAsNode = (Node) eventSource;

		// Get the scene containing the Node (i.e. containing the button):
		Scene oldScene = sourceAsNode.getScene();

		// Get the window containing the scene:
		Window window = oldScene.getWindow();

		// Again, the Scene only knows it is in a Window, but we know we specifically
		// put it in a stage. So we can downcast the Window to a Stage:

		Stage stage = (Stage) window ;

		// Equivalently, just omitting all the intermediate variables:*/
		//Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		Scene scene = new Scene(root);
		Main.stage.setScene(scene);
	}
	//
	
	@FXML
	public void handleMenu(ActionEvent menu) throws IOException {	
		if(toggleScene() == false) {
			Parent root = FXMLLoader.load(getClass().getResource("../../LockedMenu.fxml"));
			
			handleStageScene(root, menu);
		}
		else {
			Parent root = FXMLLoader.load(getClass().getResource("../../UnlockedMenu.fxml"));
			
			handleStageScene(root, menu);
		}
	}
	
	@FXML
	private void handlePantheon(ActionEvent pantheon) throws IOException {				
		if(toggleScene() == false) {
			Parent root = FXMLLoader.load(getClass().getResource("../../LockedPantheon.fxml"));
			
			handleStageScene(root, pantheon);
		}
		else {
			Parent root = FXMLLoader.load(getClass().getResource("../../UnlockedPantheon.fxml"));
			
			handleStageScene(root, pantheon);
		}
	}
	
	@FXML
	private void handleLimbus(ActionEvent limbus) throws IOException {	
		Parent root = FXMLLoader.load(getClass().getResource("../../Limbus.fxml"));

		handleStageScene(root, limbus);
	}
	
	@FXML
	private void handlePlay(ActionEvent play) throws IOException {	
		mainMenu = false;
		controller.mediaPlayer.stop();
		
		// Jacinto
		sound = new Media(transictionalMusic().toURI().toString());
		
		mediaPlayer = new MediaPlayer(sound);
		//
		
		start();
		
		controller.mediaPlayer.setCycleCount(5);
		mediaPlayer.play();
	}
	
	// Jacinto 
	public static void start() {	
		mediaPlayer.setVolume(volume);
		
		try {
			GameManager gm = new application.model.GameManager.GameManager();

			Main.stage.setResizable(false);
			AnchorPane root = new AnchorPane();
			Scene scene = new Scene(root, frameX, frameY); // size

			scene.addEventFilter(KeyEvent.KEY_PRESSED, new KeyboardEvent(gm,true));
			scene.addEventFilter(KeyEvent.KEY_RELEASED, new KeyboardEvent(gm,false));
			//scene.addEventFilter(keyEvent.ke, eventFilter);

			Main.stage.setScene(scene);

			Canvas can = new Canvas(frameX, frameY);
			root.getChildren().add(can); // add it to the scene panel stuff.

			GraphicsContext gDraw = can.getGraphicsContext2D(); // render to canvas panel

			//gDraw.fillOval(10, 10, 30, 30);
			Timeline gameLoop = new Timeline();
			gameLoop.setCycleCount(Timeline.INDEFINITE);

			KeyFrame frames = new KeyFrame(Duration.seconds(0.017), new Events(gDraw,gm)); 

			gameLoop.getKeyFrames().add(frames);
			gameLoop.play();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int getFrameX() {
		return frameX;
	}


	public static int getFrameY() {
		return frameY;
	}

	public static boolean getPause() {
		return pause;
	}

	public static void setPause(boolean pa) {
		pause = pa;
	}
	//
	
	@FXML
	private void handleSettings(ActionEvent settings) throws IOException {	
		Parent root = FXMLLoader.load(getClass().getResource("../../Settings.fxml"));
		
		handleStageScene(root, settings);
	}

	// Kwan collaboration with Jeffrey & Katherine.
	// Shawn - Adjusted the code to update and retain the updated value
	// 		   when switching between scenes.
	@FXML
	public void initialize() {	
		// Shawn
		volumeSlider.setValue(mediaPlayer.getVolume() * 50);
		volumeLabel.setText(textVolume);
		//
		
		// Katherine & Jeffrey - Set volume slider.
		volumeSlider.valueProperty().addListener
		(
				new ChangeListener<Number>() {
					@Override
					public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {	
						// Shawn
						volume = volumeSlider.getValue() / 50;
						mediaPlayer.setVolume(volume);
						//
						
						// Katherine & Jeffrey
						textVolume = String.format("%.0f", newValue);

						volumeLabel.setText(textVolume);
						//
					}

				}
		);
		
		// Kwan
		difficulty.getItems().addAll("Novice", "Intermediate", "Veteran");
		//
		
		// Shawn - Set difficulty by default.
		difficulty.getSelectionModel().selectFirst();
		
		// If either of the three choices have been selected.
		if(mode == 1) {
			difficulty.getSelectionModel().selectFirst();
		}
		if(mode == 2) {
			difficulty.getSelectionModel().selectNext();
		}
		if(mode == 3) {
			difficulty.getSelectionModel().selectLast();
		}
		//
		
		// Kwan
		difficulty.getSelectionModel().selectedItemProperty().addListener(( v, oldValue, newValue) -> handleDifficulty(newValue)); //getChoice(newValue));
		//
	}
	
	// Shawn
	private void handleDifficulty(String difficultyMode) {
		if(difficultyMode.equals("Novice")) {
			mode = 1;
			numberOfLevels = 10;
		}
		if(difficultyMode.equals("Intermediate")) {
			mode = 2;
			numberOfLevels = 7;
		}
		if(difficultyMode.equals("Veteran")) {
			mode = 3;
			numberOfLevels = 3;
		}
	}
	
	public static void handleLevelsRemaining() {		
		if(numberOfLevels > 0) {
			System.out.println("Above decrement: " + numberOfLevels);
		
			numberOfLevels--;

			System.out.println("Below decrement: " + numberOfLevels);
		}
		
    	if(numberOfLevels == 0) {
    		verifyComplete = true;
    	}

	}
	
	public static File transictionalMusic() {
		if(mainMenu == false) {
			//System.out.println("Inside mainMenu Conditional Statement.");
			
			if(mode == 0 || mode == 1) {
				songTitle = "A Closed-off City.mp3";
				//System.out.println("Easy: " + songTitle);
			}
			else if(mode == 2) {
				songTitle = "Wander's Death.mp3";
				//System.out.println("Intermediate: " + songTitle);		
			}
			else if(mode == 3) {
				songTitle = "Idol Collapse.mp3";
				//System.out.println("Veteran: " + songTitle);				
			}
		}
		else
			songTitle = "Rebirth & Hope.mp3";
		
		//System.out.println("Outside IF/ELSE: " + songTitle);
		
		File musicFile = new File("Music/" + songTitle);
	
		return musicFile;
	}
	//
	
	/*****************************************************************************************************
	 * @author MadProgrammer																			 *
	 * 																									 *
	 * URL: https://stackoverflow.com/questions/17644900/how-to-add-imagepanel-in-jpanel-inside-jframe   *
	 * 																									 *
	 *****************************************************************************************************/
	
	// Remove loading screen
	// TODO: Need to add loading screen
	
	@Override
    public void handle(ActionEvent arg) { /*TODO Auto-generated method stub*/ }
}
