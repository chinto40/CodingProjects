package application.model.GameManager;

import application.Controller.controller;
import application.model.GameManager.Player.dir;
//import gameStuff.Player.dir;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Handles all keyboard Event needed for the game.
 * @author Jacinto Molina
 *
 */
public class KeyboardEvent implements EventHandler<KeyEvent>{
	
	private GameManager gameM;
	private boolean isEventDown;
	private static int oneButton = 0;
	private boolean[] isDown;
	private int pauseCheck = 0;
	public KeyboardEvent(GameManager gm, boolean isKeyUp) {
		this.gameM = gm;
		this.isEventDown = isKeyUp;
		this.isDown = new boolean[4];
		for(boolean i: this.isDown) {
			i = false;
		}
	}
	
	@Override
	/**
	 * Event handle.
	 */
	public void handle(KeyEvent key) {
		// up ---------------------------------------------------------------------
		if((key.getCode() == KeyCode.W && this.isEventDown == true) ) { 
			//System.out.println("Up");
			this.gameM.getPlayer().toggleMovement(dir.UP);
			oneButton = 1;
			key.consume();
		}
		if(key.getCode() == KeyCode.W && this.isEventDown == false ) {
			//System.out.println("Released w buttton");
			if(oneButton == 1) {
				System.out.println("-----------------------------------------------------");
				this.gameM.getPlayer().toggleMovement(dir.Idle);
				
			}
		}
		
		// down ---------------------------------------------------------------------
		if((key.getCode() == KeyCode.S && this.isEventDown == true) ) { 
			//System.out.println("Down");
			this.gameM.getPlayer().toggleMovement(dir.DOWN);
			oneButton = 2;
			key.consume();
		}
		
		if(key.getCode() == KeyCode.S && this.isEventDown == false) { 
			//System.out.println("Released S");
			if(oneButton == 2) {
				this.gameM.getPlayer().toggleMovement(dir.Idle);
			}
		}
		
		// left ---------------------------------------------------------------------
		if((key.getCode() == KeyCode.A && this.isEventDown == true)){
			this.gameM.getPlayer().toggleMovement(dir.LEFT);
			oneButton = 3;
			key.consume();
		}
		if(key.getCode() == KeyCode.A && this.isEventDown == false) {
			//System.out.println("Released A");
			if(oneButton == 3) {
				this.gameM.getPlayer().toggleMovement(dir.Idle);
			}
		}
		
		// right ---------------------------------------------------------------------
		if((key.getCode() == KeyCode.D && this.isEventDown == true )) {
			//System.out.println("Right");
			this.gameM.getPlayer().toggleMovement(dir.RIGHT);
			oneButton = 4;
			key.consume();
		}
		if(key.getCode() == KeyCode.D && this.isEventDown == false) {
			//System.out.println("ReleasedRight");
			if(oneButton == 4) {
				this.gameM.getPlayer().toggleMovement(dir.Idle);
			}
		}
		
		// space ---------------------------------------------------------------------
		if(key.getCode() == KeyCode.SPACE && this.isEventDown == true) {
			//System.out.println("Space");
			//this.gameM.getPlayer().toggleMovement(dir.Idle);
			this.gameM.getPlayer().setIsAttacking(); // starts the attack motion
			
		}

		if(key.getCode() == KeyCode.DIGIT0 && this.isEventDown == true) {
			//System.out.println("0");
			this.gameM.getPlayer().resetPlayersPosition();
		}
		
		if(key.getCode() == KeyCode.P && this.isEventDown == true) {

				if(controller.getPause() == false) {
					controller.setPause(true);
					this.pauseCheck++;
				}else {
					controller.setPause(false);
					this.pauseCheck++;
				}
			
		}
		
	}

}
