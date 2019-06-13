package application.model.TileManager;

import application.model.GameManager.CollisionBox;
import javafx.scene.image.Image;

/**
 * The class DamagingTile sets the variables for damaging tiles and related methods.
 * 
 * @author Katherine Powell
 *
 */
public class DamagingTile extends Tile{

	private int damage;
	
	/**
	 * Constructor for DamagingTile
	 * 
	 * @param ID Number id for the text file to generate maps
	 * @param xPos X position for tile location on map
	 * @param yPos Y position for tile location on map
	 * @param tilePic Tile image
	 * @param size Pixel size of the tile
	 * @param collide Boolean to check if tile is collidable
	 * @param damage Amount of damage the tile gives to the player when touched
	 */
	public DamagingTile(int ID, int xPos, int yPos, Image tilePic, int size, boolean collide, int damage){
		super.setID(ID);
		super.setxPos(xPos);
		super.setyPos(yPos);
		super.setTilePic(tilePic);
		super.setcBox(new CollisionBox(size, xPos, yPos));
		super.setCollidable(collide);
		setDamage(damage);
	}
	
	/**
	 * Checks to see if it is a damaging tile
	 * 
	 * @return true
	 */
	public boolean isDamaging() {
		return true;
	}
	
	/**
	 * Gets the amount of damage the tile inflicts
	 * 
	 * @return damage
	 */
	public int getDamage() {
		return damage;
	}
	
	/**
	 * Sets the amount of damage the tile inflicts
	 * @param damage
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}
}
