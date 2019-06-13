/**
 * @author Shawn
 */

package application.model.TileManager;

import application.Main;
import application.model.GameManager.Load;
import application.model.GameManager.SpriteParser;
import javafx.scene.image.Image;

public class StartTeleportTile extends NonDamagingTile
{
	public StartTeleportTile(int xPos, int yPos, boolean collidable) 
	{	
		super(8, xPos, yPos, Main.getLoad().getGameList().get(5), 32, collidable,false);	
	}
}

