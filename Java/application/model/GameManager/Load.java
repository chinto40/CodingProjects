package application.model.GameManager;

import java.util.ArrayList;

import javafx.scene.image.Image;

/**
 * Loads the game Images for the tiles.
 * @author Jacinto Molina
 *
 */
public class Load {
	
	public  Image brick = SpriteParser.getImage(2,0,"Outside_A4");
	public  Image grass = SpriteParser.getImage(13,0,"Outside_A4");
	public  Image lava = SpriteParser.getImage(0,8,"Dungeon_A2");
	public  Image stone = SpriteParser.getImage(0,1,"Outside_A4");
	public  Image water = SpriteParser.getImage(3,6,"Outside_A5");
	public  Image End_Start = SpriteParser.getImage(6,6,"Dungeon_A2");
	public  Image nullImage = null;
	
	private ArrayList<Image> getImages;
	
	/**
	 * Constructs a new Load object.
	 */
	public Load(){
		brick = SpriteParser.getImage(2,0,"Outside_A4");
		grass = SpriteParser.getImage(13,0,"Outside_A4");
		lava = SpriteParser.getImage(0,8,"Dungeon_A2");
		stone = SpriteParser.getImage(0,1,"Outside_A4");
		water = SpriteParser.getImage(3,6,"Outside_A5");
		End_Start = SpriteParser.getImage(6,6,"Dungeon_A2");
		nullImage = null;
		
		this.getImages = new ArrayList<Image>();
		this.getImages.add(this.brick); // 0
		this.getImages.add(this.grass);//1
		this.getImages.add(this.lava);//2
		this.getImages.add(this.stone);//3
		this.getImages.add(this.water);//4
		this.getImages.add(this.End_Start);//5
		this.getImages.add(this.nullImage);//6 Null
		
	}
	
	/**
	 * Returns an Image list.
	 * @return a list of images.
	 */
	public ArrayList<Image> getGameList(){
		return this.getImages;
	}
	
}
