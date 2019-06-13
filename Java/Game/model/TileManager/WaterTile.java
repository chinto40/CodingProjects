package application.model.TileManager;

import application.Main;
import application.model.GameManager.Load;
import application.model.GameManager.SpriteParser;

/**
 * The Class WaterTile crates a Non Damaging Tile that looks like water
 * 
 * @author Katherine Powell
 *
 */
public class WaterTile extends DamagingTile{
	
	/**
	 * Constructor for WaterTile
	 * 
	 * @param xPos X Position for the tile on the map
	 * @param yPos Y Position for the tile on the map
	 * @param collidable Boolean for determining if the player goes on to or collides with tile
	 */
	public WaterTile(int xPos, int yPos, boolean collidable) {
		
		super(5, xPos, yPos, Main.getLoad().getGameList().get(4), 32, collidable, 1);
		
	}
}
