/**
 * @author Shawn & Jacinto collaboration
 */

package application.model.TileManager;

import java.awt.Point;

import application.Main;
import application.model.GameManager.Load;
import application.model.GameManager.SpriteParser;

public class EndTeleportTile  extends NonDamagingTile
{
	
	public EndTeleportTile(int xPos, int yPos, boolean collidable) 
	{	
		super(9, xPos, yPos, Main.getLoad().getGameList().get(5), 32, collidable,true);	
	}
	
	
}
