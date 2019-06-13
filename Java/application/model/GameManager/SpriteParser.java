package application.model.GameManager;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

/**
 * Parses the Images that will be used for the animation and the game tile.
 * @author Jacinto Molina
 *
 */
public class SpriteParser {
	
	//single image in sprite sheet
	/**
	 * Static method used to get a single Image.
	 * @param row the starting row number.
	 * @param col the starting col number.
	 * @param fileName the file of the sprite-sheet.
	 * @return A specific image of this class.
	 */
	public static Image getImage(int row, int col, String fileName ) {
		try {
			BufferedImage sprite;
			
			sprite = ImageIO.read(new File("Resource/" + fileName + ".png"));
			
			return SwingFXUtils.toFXImage(sprite.getSubimage(row* 32, col*32, 32, 32), null);
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return null;
	}
	
	// 3 images for animation in spritesheet.
	/**
	 * Static method used to get a single Image.
	 * @param row the starting row number.
	 * @param col the starting col number.
	 * @param fileName the file of the spritesheet.
	 * @return A specific image.
	 */
	public static Image[] setAnimation(int row, int col,String fileName) {  //  col,row		
		try {
			BufferedImage sprite;
			Image[] spriteAnimation = new Image[3];
			
			
			sprite = ImageIO.read(new File("Resource/" + fileName + ".png"));
			
			for(int spriteIndex = 0; spriteIndex < 3;spriteIndex++) { //run 3 times
				//sprite.getSubimage(row* 32, col*32, 32, 32).
				spriteAnimation[spriteIndex] = SwingFXUtils.toFXImage(sprite.getSubimage(row* 32, col*32, 32, 32), null);
				row++;
			}
			return spriteAnimation;
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return null;
	}
	
	/**
	 * Static method used to get a list of Images for the animation.
	 * @param row the starting row number.
	 * @param col the starting col number.
	 * @param fileName the file of the spritesheet.
	 * @return A specific image list.
	 */
	public static Image[] setPlayerAttackAnimation(int row, int col,String fileName) {  //  col,row		
		try {
			int playerSize = 25;
			BufferedImage sprite;
			Image[] spriteAnimation = new Image[3];
			
			
			sprite = ImageIO.read(new File("Resource/" + fileName + ".png"));
			
			for(int spriteIndex = 0; spriteIndex < 3;spriteIndex++) { //run 3 times
				//sprite.getSubimage(row* 32, col*32, 32, 32).                                                           // - 
				spriteAnimation[spriteIndex] = SwingFXUtils.toFXImage(sprite.getSubimage(row* playerSize , col*playerSize, playerSize-1, playerSize), null);
				row++;
			}
			return spriteAnimation;
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return null;
	}
	
	
	// 3 images for animation in spritesheet.
	// for the player
	/**
	 * Static method used to get a list of Images for the animation.
	 * @param row the starting row number.
	 * @param col the starting col number.
	 * @param fileName the file of the spritesheet.
	 * @return A specific image list.
	 */
		public static Image[] setPlayerAnimation(int row, int col,String fileName) {  //  col,row
			try {
				int playerSize = 27;
				BufferedImage sprite;
				Image[] spriteAnimation = new Image[3];
				
				
				sprite = ImageIO.read(new File("Resource/" + fileName + ".png"));
				
				for(int spriteIndex = 0; spriteIndex < 3;spriteIndex++) { //run 3 times
					//sprite.getSubimage(row* 32, col*32, 32, 32).
					spriteAnimation[spriteIndex] = SwingFXUtils.toFXImage(sprite.getSubimage(row* playerSize, col*playerSize, playerSize-5, playerSize-2), null);
					row++;
				}
				return spriteAnimation;
			} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			return null;
		}
}
