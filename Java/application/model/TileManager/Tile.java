package application.model.TileManager;

import java.awt.Point;

import application.model.GameManager.CollisionBox;
import javafx.scene.image.Image;

/**
 * The class Tile Stores all of the variables for tile objects and creates the getters and setters
 * 
 * @author Katherine Powell
 * @author Jacinto Molina
 * @author Shawn Roberts
 */
public class Tile {
	private int ID, xPos, yPos;
	private Image tilePic;
	private CollisionBox cBox;
	private boolean collidable;
	private Point tileLocForEndPoint = new Point();
	private boolean isEndPoint = false;  // Shawn
	
	/**
	 * S
	 * 
	 * @return
	 */
	public boolean isCollidable()
	{
		return collidable;
	}
	
	public void setCollidable(boolean collide)
	{
		this.collidable = collide;
	}
	
	// Jacinto
	public void setPointForEnd() {
		this.tileLocForEndPoint.x = this.xPos+ 16;
		this.tileLocForEndPoint.y = this.yPos + 16;
		
	}
	
	public Point getTileLocForEnd() {
		return this.tileLocForEndPoint;
	}
	//
	
	// Shawn
	public boolean isEnd()
	{	
		return isEndPoint;
	}

	public boolean getEndPoint() 
	{
		// TODO Auto-generated method stub
		return isEndPoint;
	}

	public void setEndPoint(boolean isEndPoint) 
	{
		//System.out.println("going in here1");
		this.isEndPoint = isEndPoint;
	}
	
	// Katherine
	/**
	 * Gets the collision box for the tile
	 * 
	 * @return cBox CollisionBox
	 */
	public CollisionBox getcBox() {
		return cBox;
	}

	/**
	 * Sets the collision box for the tile
	 * 
	 * @param cBox CollisionBox
	 */
	public void setcBox(CollisionBox cBox) {
		this.cBox = cBox;
	}

	/**
	 * Gets the tiles image
	 * 
	 * @return tilePic
	 */
	public Image getTilePic() {
		return tilePic;
	}

	/**
	 * Sets the tiles image
	 * 
	 * @param tilePic
	 */
	public void setTilePic(Image tilePic) {
		this.tilePic = tilePic;
	}
	
	/**
	 * Gets the tile id for text files
	 * 
	 * @return ID
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Sets the tile id for text files
	 * 
	 * @param iD
	 */
	public void setID(int iD) {
		ID = iD;
	}

	/**
	 * Gets the X position for he tile on the map
	 * 
	 * @return
	 */
	public int getxPos() {
		return xPos;
	}

	/**
	 * Sets the X position for the tile on the map
	 * 
	 * @param xPos
	 */
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	/**
	 * Gets the Y position for the tile on the map
	 * 
	 * @return
	 */
	public int getyPos() {
		return yPos;
	}

	/**
	 * Sets the Y position for the tile on the map
	 * 
	 * @param yPos
	 */
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}	
}
