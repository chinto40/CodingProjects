package application.model.TileManager;

import application.model.GameManager.CollisionBox;
import javafx.scene.image.Image;

/**
 * 
 * @author Katherine Powell
 *
 */
public class NonDamagingTile extends Tile{
	
	/**
	 * Constructor for DamagingTile
	 * 
	 * @param ID Number id for the text file to generate maps
	 * @param xPos X position for tile location on map
	 * @param yPos Y position for tile location on map
	 * @param tilePic Tile image
	 * @param size Pixel size of the tile
	 * @param collide Boolean to check if tile is collidable
	 * @param endpoint Boolean to check of the tile is the end of the maze
	 */
	public NonDamagingTile(int ID, int xPos, int yPos, Image tilePic, int size, boolean collide, boolean endPoint) {
		super.setID(ID);
		super.setxPos(xPos);
		super.setyPos(yPos);
		super.setTilePic(tilePic);
		super.setcBox(new CollisionBox(size, xPos, yPos));
		super.setCollidable(collide);
		super.setEndPoint(endPoint);
		if(endPoint == true) {
			//System.out.println(2);
			super.setPointForEnd();
		}
	}
	
	/**
	 * Checks to see if it is a damaging tile
	 * 
	 * @return false
	 */
	public boolean isDamaging() {
		return false;
	}
}
