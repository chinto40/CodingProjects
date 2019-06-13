package application.model.TileManager;

import application.Main;
import application.model.GameManager.Load;
import application.model.GameManager.SpriteParser;


/**
 * The Class LavaTile crates a Damaging Tile that looks like lava
 * 
 * @author Katherine Powell
 *
 */
public class LavaTile extends DamagingTile{
	
	/**
	 * Constructor for LavaTile
	 * 
	 * @param xPos X Position for the tile on the map
	 * @param yPos Y Position for the tile on the map
	 * @param collidable Boolean for determining if the player goes on to or collides with tile
	 */
	public LavaTile(int xPos, int yPos, boolean collidable) {
		
		super(4, xPos, yPos, Main.getLoad().getGameList().get(2), 32, collidable, 1);
		
	}
}
