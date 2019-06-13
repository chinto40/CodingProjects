package application.model.GameManager;

import java.awt.Point;
import java.awt.image.BufferedImage;


import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

/**
 * Creates the Player Object of this game.
 * @author Jacinto Molina
 *
 */
public class Player extends Entity{
	private BufferedImage pI;
	GameTag tag;
	private double lifeForce;
	private boolean isDead;
	
	private boolean stop;
	private boolean isCollidable;
	private CollisionBox playerCollisionBox;
	private CollisionBox playerAreaRange;
	
	private Point playerLocation;
	public enum dir{UP,DOWN,LEFT,RIGHT,Idle};
	private dir lastMovement;
	private dir currentMovement;
	private Image currentPlayerImgge;
	
	// movement
	private boolean isWalkingUp;
	private boolean isWalkingDown;
	private boolean isWalkingRight;
	private boolean isWalkingLeft;
	
	private boolean isAttacking;
	
	private boolean isStart;
	private Animation playerAnim;
	private boolean pressedAgain;
	public static BufferedImage sprite;
	private int playerSize = 27;
	
	/**
	 * Constructs a new Player object of this game.
	 * @param fileName the name of the movement sprite-sheet.
	 * @param fileAttack the name of the attack sprite-sheet.
	 * @param swimFileName the name of the swim sprite-sheet.
	 * @param row the starting row location of the first image.
	 * @param col the starting col loaction of the first image.
	 * @param locX the player x location on the game.
	 * @param locY the palyer y location on the game.
	 */
	public Player(String fileName,String fileAttack,String swimFileName, int row, int col, int locX, int locY) {
		this.lifeForce = 100.0;
		this.tag = GameTag.Player;
		this.isDead = false;
		this.playerAnim = new Animation(fileName,fileAttack,swimFileName,row,col,1,2,false);// 1 for is player // 0 for is enemy to use regular 32x32 parser
		this.isWalkingUp = false;
		this.isWalkingDown = false;
		this.isWalkingLeft = false;
		this.isWalkingRight = false;
		this.lastMovement = dir.Idle;
		this.currentMovement = dir.Idle;
		this.playerLocation = new Point(locX,locY);
		this.currentPlayerImgge = null;
		this.isCollidable = true;
		this.playerCollisionBox = new CollisionBox((this.playerSize -5), this.playerLocation.x, this.playerLocation.y);
		this.playerAreaRange = new CollisionBox(this.playerSize+(this.playerSize*4), (this.playerLocation.x-(this.playerSize*2)),(this.playerLocation.y-(this.playerSize*2)));
		this.stop = false;
		this.isStart = true;
		this.isAttacking = false;
	}
	
	/**
	 * Returns the Last direction for the player.
	 * @return enum dir data type.
	 */
	public dir getlastState() {
		return this.lastMovement;
	}
	
	/**
	 * Returns the player personal bubble.
	 * @return the player Area squre of the game.
	 */
	public CollisionBox getPlayerRange() {
		return this.playerAreaRange;
	}
	
	/**
	 * Returns the player size of this object.
	 * @return an integer value that states the player size.
	 */
	public int playerSize() {
		return this.playerSize;
	}
	
	/**
	 * Returns the player's animation.
	 * @return a Animation type member variable.
	 */
	public Animation getAnimation() {
		return this.playerAnim;
	}
	
	/**
	 * changes the format style to an Image type.
	 * @return an Image type variable.
	 */
	public Image getImage() {
		return SwingFXUtils.toFXImage(this.pI, null);
	}
	
	/**
	 * Sets if the player is attacking..
	 */
	public void setIsAttacking() {
		if(this.pressedAgain == false) { // purpose is to run once..
			this.isAttacking = true;
			this.pressedAgain = true;
			this.toggleMovement(dir.Idle);
		}
	}
	
	/**
	 * Returns if the player is attacking.
	 * @return a boolean member variable.
	 */
	public boolean getIsAttacking() {
		return this.isAttacking;
	}
	
	/**
	 * Reduces the player life force.
	 */
	public void drainLife() {
		if(!(this.lifeForce <= 0)) {
			this.lifeForce -= 10;
		}else {
			this.isDead = true;
		}
	}
	
	/**
	 * Returns the player area of the player.
	 * @return collision box of the player.
	 */
	public CollisionBox getPlayerArea() {
		return this.playerAreaRange;
	}
	/**
	 * Returns the current state of direction player.
	 * @return the current direction.
	 */
	public dir getCurrentState() {
		return this.currentMovement;
	}
	
	/**
	 * Returns a value that determines if the player is dead.
	 * @return A boolean is dead variable.
	 */
	public boolean getIsDead() {
		return this.isDead;
	}
	
	/**
	 * Returns the collision box of this object.
	 * @return the CollisionBox data type.
	 */
	public CollisionBox getPlayerCollisionBox() {
		return this.playerCollisionBox;
	}
	
	/**
	 * returns a value that determines if the player is collidable.
	 * @return a boolean is collidable variable.
	 */
	public boolean getIsCollidable() {
		return this.isCollidable;
	}
	
	/*
	 * Completely stops the player from moving.
	 */
	public void setStop(boolean stopped) {
			this.stop = stopped;
	}
	
	/**
	 * Determines which direction the player should move. 
	 * @param isMoving the next current direction the player should move.
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
	}
	
	/**
	 * Sets the player location to new ones.
	 * @param x new loc x for the player.
	 * @param y new loc y for the player.
	 */
	public void setPlayerPosition(int x, int y) {
		this.playerLocation.x += x;
		this.playerLocation.y += y;
	}
	
	/**
	 * Resets the player position for testing purpose.
	 */
	public void resetPlayersPosition() {
		this.playerLocation.x = 300;
		this.playerLocation.y = 300;
	}
	
	/**
	 * Returns the current image based on the direction the player will move.
	 * @return An image data type.
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
				if(this.lastMovement == dir.Idle && isStart == true) {
					this.currentPlayerImgge = this.playerAnim.getDownAnim()[1];
					this.lastMovement = dir.DOWN;
					this.isStart = false;
				}
				if(isAttacking == true) {
					this.currentPlayerImgge = this.setSwordAnimation();
				}
				break;
		}
		
		this.playerCollisionBox.updatePlayerCollisionBox(this.playerLocation.x, this.playerLocation.y,this.isAttacking,this.lastMovement);
		this.playerAreaRange.updatePlayerCollisionBox(this.playerLocation.x-(this.playerSize*2), this.playerLocation.y-(this.playerSize*2), false, dir.Idle);
		
		return this.currentPlayerImgge;
	}
	
	/**
	 * Returns the location of the player.
	 * @return The Point data type.
	 */
	public Point getLocatioon() {
		return this.playerLocation;
	}
	
	/**
	 * Returns the sword attack animation based on the direction the player is facing.
	 * @param mov the current direction the player is going.
	 * @return an image data type.
	 */
	public Image getSwordAttackAnim(dir mov) {
		int swordIndex = this.playerAnim.playAttackAnimation(100);
		if(swordIndex < 100) {
				switch(mov) {
				case UP:
					return this.getAnimation().getAttackUpAnim()[swordIndex];
				case DOWN:
					return this.getAnimation().getAttackDownAnim()[swordIndex];
				case LEFT:
					return this.getAnimation().getAttackLeftAnim()[swordIndex];
				case RIGHT:
					return this.getAnimation().getAttackRightAnim()[swordIndex];
				}
		}else {
				this.isAttacking = false;
				this.pressedAgain = false;
			}
		return null;
	}
	
	/**
	 * Sets the sword animation based on the direction the player is facing.
	 * @return an image data type.
	 */
	public Image setSwordAnimation() {
		Image attackPlaceHolder = null;
		switch(this.lastMovement) {
		case UP:
			attackPlaceHolder = getSwordAttackAnim(dir.UP);
			if(attackPlaceHolder == null) {
				System.out.println("ITs null so making it not null;");
				attackPlaceHolder = this.playerAnim.getUpAnim()[1];
			}
			break;
		case DOWN:
			attackPlaceHolder = getSwordAttackAnim(dir.DOWN);
			if(attackPlaceHolder == null) {
				attackPlaceHolder = this.playerAnim.getDownAnim()[1];
			}
			break;
		case LEFT:
			attackPlaceHolder = getSwordAttackAnim(dir.LEFT);
			if(attackPlaceHolder == null) {
				attackPlaceHolder = this.playerAnim.getLeftAnim()[1];
			}
			break;
		case RIGHT:
			attackPlaceHolder = getSwordAttackAnim(dir.RIGHT);
			if(attackPlaceHolder == null) {
				attackPlaceHolder = this.playerAnim.getRightAnim()[1];
			}
			break;
		}
		return attackPlaceHolder;
	}
	
	/**
	 * Returns the walking up image of this class.
	 * @return Image data type.
	 */
	public Image walkUp() {
		
		if(this.isWalkingUp == true && this.isAttacking == false) {
			if(this.stop == false) {
				this.playerLocation.y -=1;
			}
			return this.playerAnim.getUpAnim()[this.playerAnim.playAnimation(100)];
		}
		if(this.lastMovement == dir.UP) {
			return this.playerAnim.getUpAnim()[1];
		}
		return null;
	}
	
	/**
	 * Returns the walking down image of this class.
	 * @return Image data type.
	 */
	public Image walkDown() {
		if(this.isWalkingDown == true) {
			if(this.stop == false) {
				this.playerLocation.y += 1;
			}
			return this.playerAnim.getDownAnim()[this.playerAnim.playAnimation(100)];
		}
		if(this.lastMovement == dir.DOWN) {
			return this.playerAnim.getDownAnim()[1];
		}
		return null;
	}
	
	/**
	 * Returns the walking left image of this class.
	 * @return Image data type.
	 */
	public Image walkLeft() {
		if(this.isWalkingLeft == true) {
			if(this.stop == false) {
				this.playerLocation.x -= 1;
			}			
			return this.playerAnim.getLeftAnim()[this.playerAnim.playAnimation(100)];
		}
		if(this.lastMovement == dir.LEFT) {
			return this.playerAnim.getLeftAnim()[1];
		}
		return null;
	}
	
	/**
	 * Returns the walking right image of this class.
	 * @return Image data type.
	 */
	public Image walkRight() {
		if(this.isWalkingRight == true) {
			if(this.stop == false) {
				this.playerLocation.x += 1;
			}
			return this.playerAnim.getRightAnim()[this.playerAnim.playAnimation(100)];
		}
		if(this.lastMovement == dir.RIGHT) {
			return this.playerAnim.getRightAnim()[1];
		}
		return null;
		
	}
	
}
