package application.model.MapManager;
/**
 * @author Wayne D. Garman
 * Map Generation
 * @author Shawn 
 * Random Map Selection/Tile Gen for Start/Teleport
 *
 * This class manages the creation of a map object. More specifically, this takes a map object and populates the tiles, enemies, and levelname
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;

//import java.util.Random; commented until use of random feature
import java.util.Scanner;
import application.Controller.controller;
import application.model.GameManager.Enemy;
import application.model.GameManager.Player.dir;
import application.model.TileManager.EndTeleportTile;
import application.model.TileManager.GrassTile;
import application.model.TileManager.LavaTile;
import application.model.TileManager.StartTeleportTile;
import application.model.TileManager.StoneTile;
import application.model.TileManager.WaterTile;

public class MapManager {

	static Random random = new Random(); //random generation not yet implemented. no effect
	static int[] repeat = new int[18]; //meant for checking random generation repeat. no effect as of yet
	static int max = 18; //based off of num of levels
	static int min = 0;
	static int levelMode; //level number of current level
	static String txtLVMode; //converted for string filename
	static int toggle = 0; //toggle for enemy position

	/**
	 * @author Wayne D. Garman
	 * populates gameMap with tiles and creates enemy position
	 * @param gameMap
	 * @return gameMap
	 */
	public static Map createGameMap(Map gameMap) 
	{
		String filename = gameMap.levelName; //filename
		Scanner scanner = null;
		try {
			scanner = new Scanner(new FileReader(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		int[][] idArray = new int[26][26]; // 832/32 = 26 >>> basis for array map

		/*
		 * These loops populate the game map. Based off of level##.txt files
		 */
		for (int i = 0; i < 26; i++) {
			for (int j = 0; j < 26; j++) {
				idArray[i][j] = scanner.nextInt(); // captures from L to R >>> fills array with numbers
				if (idArray[i][j] == 0) {
					gameMap.getGameMap().add(new StoneTile(j * 32, i * 32, true)); 
				} else if (idArray[i][j] == 1) {
					gameMap.getGameMap().add(new GrassTile(j * 32, i * 32, false));
				} else if (idArray[i][j] == 2) {
					gameMap.getGameMap().add(new LavaTile(j * 32, i * 32, false));
				} else if (idArray[i][j] == 3) {
					gameMap.getGameMap().add(new WaterTile(j * 32, i * 32, false));
				} else if (idArray[i][j] == 4) {
					gameMap.getGameMap().add(new GrassTile(j * 32, i * 32, false));
					if(toggle == 0) {
						toggle++; //to differentiate enemy position
					gameMap.enemies.add(new Enemy("Monster1",null, 3, 0, j*32, i*32, false, dir.LEFT));
					}else {
						toggle--;
						gameMap.enemies.add(new Enemy("Monster1",null, 3, 0, j*32, i*32, false, dir.RIGHT));
					}
				} else if (idArray[i][j] == 5) {
					gameMap.getGameMap().add(new GrassTile(j * 32, i * 32, false));
					if(toggle == 0) {
						toggle++;
					gameMap.enemies.add(new Enemy("Monster1",null, 3, 0, j*32, i*32, true, dir.UP));
					}else {
						toggle--;
						gameMap.enemies.add(new Enemy("Monster1",null, 3, 0, j*32, i*32, true, dir.DOWN));
					}
				} else if (idArray[i][j] == 8) {											
					gameMap.getGameMap().add(new StartTeleportTile(j * 32, i * 32, false));
					gameMap.setPlayerStartingX(j*32);//player starts at Start Teleport Tile
					gameMap.setPlayerStartingY(i*32);
				} else if (idArray[i][j] == 9) {
					gameMap.getGameMap().add(new EndTeleportTile(j * 32, i * 32, false));
				}																				//
			}

		}
		scanner.close();
		
		return gameMap;
	}
	
	/**
	 * @author Shawn 
	 * meant to handle level selection
	 * difficulty not yet fully implemented. Only for number of completions 
	 * controller.mode is difficulty setting
	 * difficulty >>> numberOfLevels
	 * @return txtLVMode
	 */
	public static String handleLevel()
	{		
		if(controller.mode == 0 || controller.mode == 1)
		{
			//System.out.println("Inside Easy Level");
			
			int levelMode = controller.numberOfLevels;
			
			txtLVMode = Integer.toString(levelMode);
		}
		if(controller.mode == 2)
		{
			//System.out.println("Inside Intermediate Level");
			
			int levelMode = controller.numberOfLevels;
			
			txtLVMode = Integer.toString(levelMode);
		}
		if(controller.mode == 3)
		{
			//System.out.println("Inside Veteran Level");
			int levelMode = controller.numberOfLevels;
			
			txtLVMode = Integer.toString(levelMode);
		}
		else
		{
			//System.out.println("Inside Default/Special Level, " + levelMode);
			int levelMode = controller.numberOfLevels;
			
			txtLVMode = Integer.toString(levelMode);
		}
		
		return txtLVMode;
	}
	//
}
