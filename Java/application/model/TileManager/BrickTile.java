package application.model.TileManager;

import application.Main;
import application.model.GameManager.Load;
import application.model.GameManager.SpriteParser;

/**
 * The Class BrickTile crates a Non Damaging Tile that looks like brick
 * 
 * @author Katherine Powell
 *
 */
public class BrickTile extends NonDamagingTile{

	/**
	 * Constructor for BrickTile
	 * 
	 * @param xPos X Position for the tile on the map
	 * @param yPos Y Position for the tile on the map
	 * @param collidable Boolean for determining if the player goes on to or collides with tile
	 */
	public BrickTile(int xPos, int yPos, boolean collidable) {
		
		super(2, xPos, yPos, Main.getLoad().getGameList().get(0), 32, collidable,false);
		
	}
}
