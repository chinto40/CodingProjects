package application.model.MapManager;
import java.util.ArrayList;

import application.model.GameManager.Enemy;
import application.model.TileManager.Tile;

/**
 * 
 * @author Wayne D. Garman
 * This class contains the logic for a Map. A map contains a level name (basically the text file), an arraylist of tile objects, a number of enemies 
 * (this needs to be updated with difficulty logic... in development), the starting positions for enemies and players, an arraylist of enemies,
 * and the enemy direction. 
 */

public class Map {
	
	String levelName;
	ArrayList<Tile> gameMap = new ArrayList<Tile>();
	int numOfEnemies;
	int playerStartingX, playerStartingY, enemyStartingX, enemyStartingY;
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	boolean enemyDir;
	
	/**
	 * CONSTRUCTOR
	 * @param levelName
	 */
	public Map(String levelName) {
		this.levelName = "Resource/level" + levelName + ".txt";
	}

	/**
	 * Getter for gameMap
	 * @return gameMap
	 */
	public ArrayList<Tile> getGameMap() {
		return gameMap;
	}

	/**
	 * Setter for gameMap
	 * @param gameMap
	 */
	public void setGameMap(ArrayList<Tile> gameMap) {
		this.gameMap = gameMap;
	}

	/**
	 * getter for levelName
	 * @return levelName
	 */
	public String getLevelName() {
		return levelName;
	}
	
	/**
	 * setter for levelName
	 * @param levelName
	 */
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	/**
	 * getter for numOfEnemies
	 * @return numOfEnemies
	 */
	public int getNumOfEnemies() {
		return numOfEnemies;
	}

	/**
	 * setter for numOfEnemies
	 * @param numOfEnemies
	 */
	public void setNumOfEnemies(int numOfEnemies) {
		this.numOfEnemies = numOfEnemies;
	}

	/**
	 * getter for playerStartingX
	 * @return playerStartingX
	 */
	public int getPlayerStartingX() {
		return playerStartingX;
	}

	/**
	 * getter for playerStartingX
	 * @param playerStartingX
	 */
	public void setPlayerStartingX(int playerStartingX) {
		this.playerStartingX = playerStartingX;
	}

	/**
	 * getter for playerStartingY
	 * @return playerStartingY
	 */
	public int getPlayerStartingY() {
		return playerStartingY;
	}

	/**
	 * getter for playerStartingY
	 * @return playerStartingY
	 */
	public void setPlayerStartingY(int playerStartingY) {
		this.playerStartingY = playerStartingY;
	}

	/**
	 * getter for enemyStartingX
	 * @return enemyStartingX
	 */
	public int getEnemyStartingX() {
		return enemyStartingX;
	}

	/**
	 * getter for enemyStartingX
	 * @param enemyStartingX
	 */
	public void setEnemyStartingX(int enemyStartingX) {
		this.enemyStartingX = enemyStartingX;
	}

	/**
	 * getter for enemyStartingY
	 * @return enemyStartingY
	 */
	public int getEnemyStartingY() {
		return enemyStartingY;
	}

	/**
	 * getter for enemyStartingY
	 * @param enemyStartingY
	 */
	public void setEnemyStartingY(int enemyStartingY) {
		this.enemyStartingY = enemyStartingY;
	}

	/**
	 * confirm for enemyDir
	 * @return enemyDir
	 */
	public boolean isEnemyDir() {
		return enemyDir;
	}

	/**
	 * setter for enemyDir
	 * @param enemyDir
	 */
	void setEnemyDir(boolean enemyDir) {
		this.enemyDir = enemyDir;
	}

	/**
	 * getter for ArrayList of Enemies
	 * @return enemies
	 */
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	/**
	 * setter for arraylist of enemies
	 * @param enemies
	 */
	public void setEnemies(ArrayList<Enemy> enemies) {
		this.enemies = enemies;
	}
	
	

}