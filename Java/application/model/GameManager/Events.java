package application.model.GameManager;

import application.Main;
import application.Controller.controller;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;


//Runs my GameLoop
/**
 * Events class to check for events at 60 frams per sec.
 * @author Jacinto Molina
 */
public class Events implements EventHandler<ActionEvent>{

	private final long startTime = System.currentTimeMillis();
	private GraphicsContext gDraw;
	private GameManager gm;
	 
	
	//constructor
	/**
	 * Constructs a new Evetns object.
	 * @param g Graphics Context of this program.
	 * @param gameM Current game manager being used in main.
	 */
	public Events(GraphicsContext g,GameManager gameM) {
		this.gDraw = g;
		this.gm = gameM;
	}
	
	// game loop at 60fps
	/**
	 * Handle the event of running a game loop.
	 */
	public void handle(ActionEvent e) {
		if(controller.getPause() == false) {
		this.gDraw.clearRect(0, 0, this.gDraw.getCanvas().getHeight(), gDraw.getCanvas().getWidth());
		this.gm.update(gDraw);
		this.gm.checkCollusion(gDraw);
		}
	}
}
