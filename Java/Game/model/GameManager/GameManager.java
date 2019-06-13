package application.model.GameManager;

import java.util.ArrayList;

import application.Main;
import application.Controller.controller;
import application.model.GameManager.Player.dir;
import application.model.MapManager.Map;
import application.model.MapManager.MapManager;
import application.model.TileManager.Tile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Manages the Complete game. 
 * @author Jacinto Molina
 *
 */
public class GameManager {
	
	private Player player;
	int row = 0, col = 0;
	private ArrayList<Tile> gameMap;
	private boolean isCollisionSet;
	private Enemy testEnemy;
	private Enemy testEnemy2;
	private Enemy testEnemy3;
	private Map gmap = MapManager.createGameMap(new Map(MapManager.handleLevel().toString()));
	private int colorInd;
	private int playerRedu = 5;
	private int enemyCount;
	private ArrayList<Enemy> enemies;

	/**
	 * Constructs a new Game manager game object.
	 */
	public GameManager() {
		this.enemies = new ArrayList<Enemy>();
		this.player = new Player("Actor1","Character-Attack-Animation-Update-5","swimAnim",0,0,this.gmap.getPlayerStartingX()+5,this.gmap.getPlayerStartingY()+5);
		this.enemies = this.gmap.getEnemies();
		this.gameMap = new ArrayList<Tile>();
		this.isCollisionSet = false;
		this.colorInd = 0;
		this.enemyCount = this.enemies.size();		
		this.gameMap = gmap.getGameMap();
	}
	
	/**
	 * ReInitializes the game variables after each level is completed.
	 */
	public void reInit() {
		this.gmap = MapManager.createGameMap(new Map(MapManager.handleLevel().toString()));
		this.gameMap = gmap.getGameMap();
		this.player = new Player("Actor1","Character-Attack-Animation-Update-5","swimAnim",0,0,this.gmap.getPlayerStartingX()+5,this.gmap.getPlayerStartingY()+5);
		this.enemies = this.gmap.getEnemies();		
	}
	
	/**
	 * Returns the player object of this class.
	 * @return the player object of this class.
	 */
	public Player getPlayer() {
		return this.player;
	}
	
	
	/**
	 * Updates the game itself.
	 * @param gDraw the GraphicsContext of this class.
	 */
	public void update(GraphicsContext gDraw) {
		
		//game map 
		for(int tileIndex = 0; tileIndex < this.gameMap.size(); tileIndex++) {
			gDraw.drawImage(this.gameMap.get(tileIndex).getTilePic(), this.gameMap.get(tileIndex).getxPos(), this.gameMap.get(tileIndex).getyPos());
		}
	
		// player
		gDraw.drawImage(this.player.getMovementSprite(), this.player.getLocatioon().x, this.player.getLocatioon().y);
		
		
		
		// enemies
		if(this.enemies.size() >= 1 && this.enemies.isEmpty() == false) {
			for(int enemyCount = 0; enemyCount < this.enemies.size(); enemyCount++) {
				gDraw.drawImage(this.enemies.get(enemyCount).getMovementSprite(), this.enemies.get(enemyCount).getEnemyLoc().x,this.enemies.get(enemyCount).getEnemyLoc().y);
				this.enemies.get(enemyCount).moveEnemy();
			}
		}
	}
	
	// Shawn
    public void handleLevelCompletion()
    {
        if(controller.mode == 0 || controller.mode == 1 && controller.numberOfLevels == 0)
        {
            System.out.println("Easy Mode Completed.");

            // TODO: Reset application to open main Menu without Limbus Unlocked.
        }
        else if(controller.mode == 2 && controller.numberOfLevels == 0)
        {
            System.out.println("Intermediate Mode Completed.");

            // TODO: Reset application to open main Menu without Limbus Unlocked.
        }
        else if(controller.mode == 3 && controller.numberOfLevels == 0)
        {
            System.out.println("Veteran Mode Completed, now unlocking Limbus of the Damned.");

            boolean toggle = true;

            // TODO: Reset application to open main Menu.
        }
    }
    //
	
	//Collision
    /**
	 * Checks the game for collision.
	 * @param gDraw the GraphicsContext of this class.
	 */
	public void checkCollusion(GraphicsContext g) {
		
		// *Collision with block list. 
			for(int index = 0; index < this.gameMap.size();index++) {
				for(int playerIndex = 0; playerIndex < (this.player.playerSize()-playerRedu); playerIndex++) {
					
					if(this.gameMap.get(index).isEnd() == true)
                    {
                        //check up
                        if((this.player.getPlayerCollisionBox().getTop().get(playerIndex).x == this.gameMap.get(index).getTileLocForEnd().x ) 
                                && (this.player.getPlayerCollisionBox().getTop().get(playerIndex).y == this.gameMap.get(index).getTileLocForEnd().y ) ||
                        // bottom direction
                          (((this.player.getPlayerCollisionBox().getBottom().get(playerIndex).x == this.gameMap.get(index).getTileLocForEnd().x ) 
                                && (this.player.getPlayerCollisionBox().getBottom().get(playerIndex).y == this.gameMap.get(index).getTileLocForEnd().y))) ||
                        // right direction 
                          (((this.player.getPlayerCollisionBox().getRight().get(playerIndex).x == this.gameMap.get(index).getTileLocForEnd().x ) 
                                && ((this.player.getPlayerCollisionBox().getRight().get(playerIndex).y) == this.gameMap.get(index).getTileLocForEnd().y))) ||
                        // left direction
                          (((this.player.getPlayerCollisionBox().getLeft().get(playerIndex).x == this.gameMap.get(index).getTileLocForEnd().x ) 
                                && ((this.player.getPlayerCollisionBox().getLeft().get(playerIndex).y) == this.gameMap.get(index).getTileLocForEnd().y)))) 
                        {
                            //System.out.println("End of Level");

                            // Verify LV completion by Shawn  	
                        	controller.handleLevelsRemaining();
                        	
                        	if(controller.verifyComplete == false)
                        	{
                        		this.reInit();
                        	}
                        	else
                        	{
                        		Main.stage.close();
                        		handleLevelCompletion();
                        	}
                        	//
                        }
                    }
					
					
					if(this.gameMap.get(index).isCollidable()) 
					{
						
						//then checks to see if the block is near the player and sets it to collidable. 
						if(this.gameMap.get(index).getxPos() < this.player.getPlayerArea().getRight().get(playerIndex).x && this.gameMap.get(index).getxPos() > this.player.getPlayerArea().getLeft().get(playerIndex).x
							 && this.gameMap.get(index).getyPos() > this.player.getPlayerArea().getTop().get(playerIndex).y && this.gameMap.get(index).getyPos() < this.player.getPlayerArea().getBottom().get(playerIndex).y) 
						{	
							
							g.setStroke(Color.RED);
							
							g.strokeLine(this.gameMap.get(index).getcBox().getTop().get(0).x, this.gameMap.get(index).getcBox().getTop().get(0).y, this.gameMap.get(index).getcBox().getTop().get(this.gameMap.get(index).getcBox().getTop().size()-1).x , this.gameMap.get(index).getcBox().getTop().get(this.gameMap.get(index).getcBox().getTop().size()-1).y);
							g.strokeLine(this.gameMap.get(index).getcBox().getRight().get(0).x, this.gameMap.get(index).getcBox().getRight().get(0).y, this.gameMap.get(index).getcBox().getRight().get(this.gameMap.get(index).getcBox().getRight().size()-1).x , this.gameMap.get(index).getcBox().getRight().get(this.gameMap.get(index).getcBox().getRight().size()-1).y);
							g.strokeLine(this.gameMap.get(index).getcBox().getLeft().get(0).x, this.gameMap.get(index).getcBox().getLeft().get(0).y, this.gameMap.get(index).getcBox().getLeft().get(this.gameMap.get(index).getcBox().getLeft().size()-1).x , this.gameMap.get(index).getcBox().getLeft().get(this.gameMap.get(index).getcBox().getLeft().size()-1).y);
							g.strokeLine(this.gameMap.get(index).getcBox().getBottom().get(0).x, this.gameMap.get(index).getcBox().getBottom().get(0).y,this.gameMap.get(index).getcBox().getBottom().get(this.gameMap.get(index).getcBox().getBottom().size()-1).x , this.gameMap.get(index).getcBox().getBottom().get(this.gameMap.get(index).getcBox().getBottom().size()-1).y);
							
							for(int pointIndex = 0; pointIndex < this.gameMap.get(index).getcBox().getBottom().size();pointIndex++){
								// up
								if((this.player.getPlayerCollisionBox().getTop().get(playerIndex).x == this.gameMap.get(index).getcBox().getBottom().get(pointIndex).x ) && 
									(this.player.getPlayerCollisionBox().getTop().get(playerIndex).y == this.gameMap.get(index).getcBox().getBottom().get(pointIndex).y) && this.player.getCurrentState() == dir.UP) {
										if(this.isCollisionSet == false) {
											this.player.setPlayerPosition(0, 1); // bounces the player from outside the matched bounds. 
										}
										this.isCollisionSet = true;
										this.player.setStop(true);
									
									//bottom direction
								}else if((this.player.getPlayerCollisionBox().getBottom().get(playerIndex).x == this.gameMap.get(index).getcBox().getTop().get(pointIndex).x ) && 
										((this.player.getPlayerCollisionBox().getBottom().get(playerIndex).y) == this.gameMap.get(index).getcBox().getTop().get(pointIndex).y) && this.player.getCurrentState() == dir.DOWN) {
										//System.out.println("Top Collision: ");
										if(this.isCollisionSet == false) {
											this.player.setPlayerPosition(0, -1); // bounces the player from outside the matched bounds. 
										}
										//System.out.println("In down dir");
										this.isCollisionSet = true;
										this.player.setStop(true);
									
									// right direction 
								}else if((this.player.getPlayerCollisionBox().getRight().get(playerIndex).x == this.gameMap.get(index).getcBox().getLeft().get(pointIndex).x ) && 
										((this.player.getPlayerCollisionBox().getRight().get(playerIndex).y) == this.gameMap.get(index).getcBox().getLeft().get(pointIndex).y) && this.player.getCurrentState() == dir.RIGHT) {
										//System.out.println("Left Collision: ");
										
										if(this.isCollisionSet == false) {
											this.player.setPlayerPosition(-1, 0); // bounces the player from outside the matched bounds. 
										}
										//System.out.println("In right dir");
										this.isCollisionSet = true;
										this.player.setStop(true);
										
									// left direction
								}else if((this.player.getPlayerCollisionBox().getLeft().get(playerIndex).x == this.gameMap.get(index).getcBox().getRight().get(pointIndex).x ) && 
										((this.player.getPlayerCollisionBox().getLeft().get(playerIndex).y) == this.gameMap.get(index).getcBox().getRight().get(pointIndex).y) && this.player.getCurrentState() == dir.LEFT) {
										//System.out.println("Right Collision: ");
										
										if(this.isCollisionSet == false) {
											this.player.setPlayerPosition(1, 0); // bounces the player from outside the matched bounds. 
										}
										//System.out.println("In left dir");				
										this.isCollisionSet = true;
										this.player.setStop(true);
								}else {
									if(this.player.getCurrentState() != this.player.getlastState() && this.isCollisionSet == true) {
										this.player.setStop(false);
										this.isCollisionSet = false;
										//System.out.println("Resetting everything");
										
									}
						}
					}
				}	
			}
		}	
	}
			/**
			 * seems to work,, need further testing but great progress so far...
			 * till need to implement what happens to both entities.
			 */
			// collision for the enemies*//
				if(this.enemyCount > 0) { // check to see if there are any enemies.
					
					for(int enemyCount = 0; enemyCount < this.enemies.size() ;enemyCount++) {
						for(int playerIndex = 0; playerIndex < (this.player.playerSize()-playerRedu); playerIndex++) {					
							for(int pointIndex = 0; pointIndex < this.enemies.get(0).getcBox().getBottom().size(); pointIndex++) {
								
								if((this.player.getPlayerCollisionBox().getTop().get(playerIndex).x == this.enemies.get(enemyCount).getcBox().getBottom().get(pointIndex).x ) && 
										(this.player.getPlayerCollisionBox().getTop().get(playerIndex).y == this.enemies.get(enemyCount).getcBox().getBottom().get(pointIndex).y) ) {
											if(this.isCollisionSet == false) {
												this.player.setPlayerPosition(0, 1); // bounces the player from outside the matched bounds.
												this.enemies.get(enemyCount).setEnemyLocation(0, -1);
											}
											this.isCollisionSet = true;
											this.player.setStop(true);
											System.out.println("Attack Collision Action is REQUIRED!!!");
											
											//*************::TESTING::***********************************************************
											if(this.player.getIsAttacking()) {	
											
												this.enemies.remove(enemyCount);
												this.enemyCount--;
											}
											
											//************************************************************************
											
										
										//bottom direction
									}else if((this.player.getPlayerCollisionBox().getBottom().get(playerIndex).x == this.enemies.get(enemyCount).getcBox().getTop().get(pointIndex).x ) && 
											((this.player.getPlayerCollisionBox().getBottom().get(playerIndex).y) == this.enemies.get(enemyCount).getcBox().getTop().get(pointIndex).y) ) {
											//System.out.println("Top Collision: ");
											if(this.isCollisionSet == false) {
												this.player.setPlayerPosition(0, -1); // bounces the player from outside the matched bounds.
												this.enemies.get(enemyCount).setEnemyLocation(0, +1);
											}
											//System.out.println("In down dir");*/
											this.isCollisionSet = true;
											this.player.setStop(true);
											
											System.out.println("Attack Collision Action is REQUIRED!!!");
											if(this.player.getIsAttacking()) {	
												
												this.enemies.remove(enemyCount);
												this.enemyCount--;
											}
											
										
										// right direction 
									}else if((this.player.getPlayerCollisionBox().getRight().get(playerIndex).x == this.enemies.get(enemyCount).getcBox().getLeft().get(pointIndex).x ) && 
											((this.player.getPlayerCollisionBox().getRight().get(playerIndex).y) == this.enemies.get(enemyCount).getcBox().getLeft().get(pointIndex).y) ) {
											//System.out.println("Left Collision: ");
											
											if(this.isCollisionSet == false) {
												this.player.setPlayerPosition(-1, 0); // bounces the player from outside the matched bounds. 
												this.enemies.get(enemyCount).setEnemyLocation(+1, 0);
											}
											//System.out.println("In right dir");*/
											this.isCollisionSet = true;
											this.player.setStop(true);
											
											System.out.println("Attack Collision Action is REQUIRED!!!");
											if(this.player.getIsAttacking()) {	
												
												this.enemies.remove(enemyCount);
												this.enemyCount--;
											}
										// left direction
									}else if((this.player.getPlayerCollisionBox().getLeft().get(playerIndex).x == this.enemies.get(enemyCount).getcBox().getRight().get(pointIndex).x ) && 
											((this.player.getPlayerCollisionBox().getLeft().get(playerIndex).y) == this.enemies.get(enemyCount).getcBox().getRight().get(pointIndex).y) ) {
											//System.out.println("Right Collision: ");
											
											if(this.isCollisionSet == false) {
												this.player.setPlayerPosition(1, 0); // bounces the player from outside the matched bounds.
												this.enemies.get(enemyCount).setEnemyLocation(-1, 0);
											}
											//System.out.println("In left dir");	*/			
											this.isCollisionSet = true;
											this.player.setStop(true);
											System.out.println("Attack Collision Action is REQUIRED!!!");
											if(this.player.getIsAttacking()) {	
												
												this.enemies.remove(enemyCount);
												this.enemyCount--;
											}
										
									}else {
										if(this.player.getCurrentState() != this.player.getlastState() && this.isCollisionSet == true) {
											
											this.isCollisionSet = false;
											this.player.setStop(false);
											//System.out.println("Resetting everything");
											
										}
								
								
								
							}
						
						}
					}
				}
			}
				
				
				
				
			
		g.setStroke(Color.BLACK);
		
		g.strokeLine(this.player.getPlayerCollisionBox().getTop().get(0).x, this.player.getPlayerCollisionBox().getTop().get(0).y, this.player.getPlayerCollisionBox().getTop().get(this.player.getPlayerCollisionBox().getTop().size()-1).x , this.player.getPlayerCollisionBox().getTop().get(this.player.getPlayerCollisionBox().getTop().size()-1).y);
		g.strokeLine(this.player.getPlayerCollisionBox().getRight().get(0).x, this.player.getPlayerCollisionBox().getRight().get(0).y, this.player.getPlayerCollisionBox().getRight().get(this.player.getPlayerCollisionBox().getRight().size()-1).x , this.player.getPlayerCollisionBox().getRight().get(this.player.getPlayerCollisionBox().getRight().size()-1).y);
		g.strokeLine(this.player.getPlayerCollisionBox().getLeft().get(0).x, this.player.getPlayerCollisionBox().getLeft().get(0).y, this.player.getPlayerCollisionBox().getLeft().get(this.player.getPlayerCollisionBox().getLeft().size()-1).x , this.player.getPlayerCollisionBox().getLeft().get(this.player.getPlayerCollisionBox().getLeft().size()-1).y);
		g.strokeLine(this.player.getPlayerCollisionBox().getBottom().get(0).x, this.player.getPlayerCollisionBox().getBottom().get(0).y, this.player.getPlayerCollisionBox().getBottom().get(this.player.getPlayerCollisionBox().getBottom().size()-1).x , this.player.getPlayerCollisionBox().getBottom().get(this.player.getPlayerCollisionBox().getBottom().size()-1).y);
		
	}
	
}

