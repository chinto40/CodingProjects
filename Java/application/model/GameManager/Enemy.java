package application.model.GameManager;

import java.awt.Point;

import application.model.GameManager.Player.dir;
import javafx.scene.image.Image;

/**
 * Enemy game object that walks.
 * @author Jacinto Molina
 *
 */
public class Enemy {

	private Animation enemyAnim;
	private Point enemyLoc;
	private dir lastMovement;
	private dir currentMovement;
	
	private boolean isWalkingUp;
	private boolean isWalkingDown;
	private boolean isWalkingRight;
	private boolean isWalkingLeft;
	
	private Image currentPlayerImgge;
	private CollisionBox enemyCollisionBox;
	
	private boolean enemyWalkPatternIsUp;
	private boolean stop;
	private boolean isStart;
	private Point motion1;
	private Point motion2;
	
	private dir switchDir;
	private int enemyRange = 96;
	
	/**
	 * Constructs a new Enemy Object.
	 * @param fileName the name of the spritesheet.
	 * @param fileAttack the name of the spritesheet.
	 * @param row the starting row number.
	 * @param col the starting col number.
	 * @param locX the enemy x location.
	 * @param locY the enemy y location.
	 * @param isUp determins if the object is up-down or left-right.
	 * @param startFirst which way to start first.
	 */
	public Enemy(String fileName,String fileAttack, int row, int col, int locX, int locY, boolean isUp, dir startFirst) {
		
		this.enemyAnim = new Animation(fileName,fileAttack,null,row,col,0,3,false);
		this.enemyLoc = new Point(locX,locY);
		this.currentMovement = startFirst;
		this.lastMovement = dir.Idle;
		
		this.isWalkingDown = false;
		this.isWalkingLeft = false;
		this.isWalkingRight = false;
		this.isWalkingUp = false;
		this.enemyWalkPatternIsUp = isUp;
		this.switchDir = startFirst;
		
		this.currentPlayerImgge = null;
		
		this.enemyCollisionBox = new CollisionBox(32,this.enemyLoc.x,this.enemyLoc.y);
		
		
		if(isUp) {
			this.motion1 = new Point(this.enemyLoc.x, this.enemyLoc.y+enemyRange);
			this.motion2 = new Point(this.enemyLoc.x, this.enemyLoc.y-enemyRange);
		}else {
			this.motion1 = new Point(this.enemyLoc.x+enemyRange, this.enemyLoc.y);
			this.motion2 = new Point(this.enemyLoc.x-enemyRange, this.enemyLoc.y);
		}
		
		this.stop = false;
		this.isStart = true;
		
		this.moveEnemy();
				
	}
	
	/**
	 * Sets the enemy location.
	 * @param x location of the enemy.
	 * @param y location of the enemy.
	 */
	public void setEnemyLocation(int x, int y) {
		this.enemyLoc.x += x;
		this.enemyLoc.y += y;
	}
	
	/**
	 * Returns the enemy location Point.
	 * @return a Point Location.
	 */
	public Point getEnemyLoc() {
		return this.enemyLoc;
	}

	/**
	 * Moves the enemy based on the current circumstance.
	 * @param isMoving the current direction of the enemy.
	 */
	public void toggleMovement(dir isMoving) {
		switch(isMoving) {
		case UP:
			if(this.isWalkingUp == false) {
				this.isWalkingUp = true;
				this.lastMovement = dir.UP;
				this.currentMovement = dir.UP;
			}
			break;
		case DOWN:
			if(this.isWalkingDown == false) {
				this.isWalkingDown = true;
				this.lastMovement = dir.DOWN;
				this.currentMovement = dir.DOWN;
			}
			break;
		case LEFT:
			if(this.isWalkingLeft == false) {
				this.isWalkingLeft = true;
				this.lastMovement = dir.LEFT;
				this.currentMovement = dir.LEFT;
			}
			break;
		case RIGHT:
			if(this.isWalkingRight == false) {
				this.isWalkingRight = true;
				this.lastMovement = dir.RIGHT;
				this.currentMovement = dir.RIGHT;
			}
			break;
		case Idle:
			this.isWalkingUp = false;
			this.isWalkingDown = false;
			this.isWalkingRight = false;
			this.isWalkingLeft = false;
			this.currentMovement = dir.Idle;
			break;
		}
		this.enemyCollisionBox.updatePlayerCollisionBox(this.enemyLoc.x, this.enemyLoc.y, false, dir.Idle);
	}
	
	/**
	 * Returns the enemy animation.
	 * @return animation type object.
	 */
	public Animation getAnim() {
		return this.enemyAnim;
	}
	
	/**
	 * Returns the enemy Collision Box.
	 * @return collision box.
	 */
	public CollisionBox getcBox() {
		return this.enemyCollisionBox;
	}
	
	
	/**
	 * Moves the enemies around.
	 */
	public void moveEnemy() {
		if(this.enemyWalkPatternIsUp == true) { // up and down
			//System.out.println("Inside enemy walk");
			if((this.enemyLoc.y <= this.motion1.y) && this.switchDir == dir.DOWN) { // going down
				toggleMovement(dir.Idle);
				toggleMovement(dir.DOWN);
				//System.out.println("direction is down");
				if(this.enemyLoc.y == this.motion1.y) {
					//System.out.println("Switching");
					this.switchDir = dir.UP;
				}
			}
			if((this.enemyLoc.y >= this.motion2.y) && this.switchDir == dir.UP) { // going up 
				toggleMovement(dir.Idle);
				toggleMovement(dir.UP);
				//System.out.println("direction is up");
				if(this.enemyLoc.y == this.motion2.y) {
					// switch
					//System.out.println("Switching");
					this.switchDir = dir.DOWN;
				}
			}
		}else {
			if((this.enemyLoc.x <= this.motion1.x) && this.switchDir ==  dir.RIGHT) { // going
				toggleMovement(dir.Idle);
				toggleMovement(dir.RIGHT);
				//System.out.println("direction is up");
				if(this.enemyLoc.x == this.motion1.x) {
					// switch
					//System.out.println("Switching");
					this.switchDir = dir.LEFT;
				}
			}else if((this.enemyLoc.x >= this.motion2.x) && this.switchDir == dir.LEFT) {// going up 
				toggleMovement(dir.Idle);
				toggleMovement(dir.LEFT);
				//System.out.println("direction is up");
				if(this.enemyLoc.x == this.motion2.x) {
					// switch
					//System.out.println("Switching");
					this.switchDir = dir.RIGHT;
				}
			}
		
		}
		
	}
	
	
	/**
	 * Returns the current Image for this object.
	 * @return the current Image of this object.
	 */
	public Image getMovementSprite() {
		switch(this.currentMovement) {
			case UP:
				this.currentPlayerImgge =	this.walkUp();
				break;
			case DOWN:
				this.currentPlayerImgge = this.walkDown();
				break;
			case LEFT:
				this.currentPlayerImgge = this.walkLeft();
				break;
			case RIGHT:
				this.currentPlayerImgge = this.walkRight();
				break;
			case Idle:
				/*if(this.lastMovement == dir.Idle && isStart == true) {
					this.currentPlayerImgge = this.enemyAnim.getDownAnim()[1];
					this.isStart = false;
				}*/
				break;
		}
		
		this.enemyCollisionBox.updatePlayerCollisionBox(this.enemyLoc.x, this.enemyLoc.y,false, dir.Idle);
		return this.currentPlayerImgge;
	}
	
	/**
	 * walks the enemy up.
	 * @return an Image type.
	 */
	public Image walkUp() {
				this.enemyLoc.y -=2;
			return this.enemyAnim.getUpAnim()[this.enemyAnim.playAnimation(100)];
	}
	
	/**
	 * walks the enemy down.
	 * @return an Image type.
	 */
	public Image walkDown() {
		
		this.enemyLoc.y += 2;
		return this.enemyAnim.getDownAnim()[this.enemyAnim.playAnimation(100)];
	}
	
	/**
	 * walks the enemy left.
	 * @return an Image type.
	 */
	public Image walkLeft() {
		
		this.enemyLoc.x -= 2;
		return this.enemyAnim.getLeftAnim()[this.enemyAnim.playAnimation(100)];
	}
	
	/**
	 * walks the enemy right.
	 * @return an Image type.
	 */
	public Image walkRight() {
		
		this.enemyLoc.x += 2;
		return this.enemyAnim.getRightAnim()[this.enemyAnim.playAnimation(100)];
		
	}
}
