package application.model.TileManager;

import application.Main;
import application.model.GameManager.Load;
import application.model.GameManager.SpriteParser;

/**
 * The Class StoneTile creates a Non Damaging Tile object that looks like stone
 * 
 * @author Katherine Powell
 *
 */
public class StoneTile extends NonDamagingTile{

	/**
	 * Constructor for StoneTile
	 * 
	 * @param xPos X Position for the tile on the map
	 * @param yPos Y Position for the tile on the map
	 * @param collidable Boolean for determining if the player goes on to or collides with tile
	 */
	public StoneTile(int xPos, int yPos, boolean collidable) {
		
		super(3, xPos, yPos, Main.getLoad().getGameList().get(3), 32, collidable,false);
		
	}
}
