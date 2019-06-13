package application.model.TileManager;

import application.Main;
import application.model.GameManager.Load;
import application.model.GameManager.SpriteParser;

/**
 * The Class GrassTile crates a Non Damaging Tile that looks like grass
 * 
 * @author Katherine Powell
 *
 */
public class GrassTile extends NonDamagingTile{

	/**
	 * Constructor for GrassTile
	 * 
	 * @param xPos X Position for the tile on the map
	 * @param yPos Y Position for the tile on the map
	 * @param collidable Boolean for determining if the player goes on to or collides with tile
	 */
	public GrassTile(int xPos, int yPos, boolean collidable) {
		
		super(1, xPos, yPos, Main.getLoad().getGameList().get(1), 32, collidable,false);
		
	}
}

