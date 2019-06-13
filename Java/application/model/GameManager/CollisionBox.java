package application.model.GameManager;

import java.awt.Point;
import java.util.ArrayList;

import application.model.GameManager.Player.dir;

/**
 * Creates a custom collision box for a square game object.
 * 
 * @author Jacinto Molina
 *
 */
public class CollisionBox {
	
	private ArrayList<Point> bottom;
	private ArrayList<Point> top;
	private ArrayList<Point> left;
	private ArrayList<Point> right;
	private int collisionSize=0;
	private final int DIF = 1;
	private ArrayList<Point> edges;
	
	private int attackRange = 5;
	
	/**
	 * Constructs a new CollisionBox object.
	 * @param boxSize the size of the image.
	 * @param posX the starting x position.
	 * @param posY the starting y position.
	 */
	public CollisionBox(int boxSize, int posX, int posY) {
		
		this.bottom = new ArrayList<Point>();
		this.top = new ArrayList<Point>();
		this.left = new  ArrayList<Point>();
		this.right = new ArrayList<Point>();
		this.collisionSize = boxSize;
		
		for(int index = 0; index < (boxSize+this.DIF); index++) {
			this.bottom.add(new Point());
			this.top.add(new Point());
			this.left.add(new Point());
			this.right.add(new Point());
		}

		/*
		 * Collision box point system.
		 * P0---------------P1
		 * |                |
		 * |                |
		 * |                |
		 * |                |
		 * |                |
		 * p2---------------p3
		 */
		
		this.edges = new ArrayList<Point>();
		
		//changing it to arraylist
		
		for(int index = 0; index < 4;index++) {
			this.edges.add(new Point());
				switch(index) {
				case 0: // original one(p0)
					this.edges.get(index).x = posX;
					this.edges.get(index).y = posY;
					break;
				case 1:// p1(x +size, Y)
					this.edges.get(index).x = (posX+boxSize);
					this.edges.get(index).y = posY;
					break;
				case 2:// p2(X, y+size)
					this.edges.get(index).x = posX;
					this.edges.get(index).y = (posY+boxSize);
					break;
				case 3://p3(x+size, y+size)
					this.edges.get(index).x = (posX+boxSize);
					this.edges.get(index).y = (posY+boxSize);
					break;
			}
		}
		
		initSides();
	}
	
	/**
	 * reInits the sides of the collision box.
	 */
	public void initSides() {
		//TopSide
		for(int index = 0; index < (this.collisionSize+this.DIF); index++) {
			this.top.get(index).y = this.edges.get(0).y;
			this.top.get(index).x = (this.edges.get(0).x + index);
			
			this.bottom.get(index).y = this.edges.get(2).y;
			this.bottom.get(index).x = (this.edges.get(2).x + index);
			
			this.left.get(index).x = this.edges.get(0).x;
			this.left.get(index).y = (this.edges.get(0).y + index);
			
			if(this.right.get(index).x != this.edges.get(3).x && this.right.get(index).y != this.edges.get(3).y) {
				this.right.get(index).x = this.edges.get(1).x;
				this.right.get(index).y = (this.edges.get(1).y + index);
			}
		}
	}
	
	/**
	 * Updates the player collsiioni box based on if the player is attacking/ moving.
	 * @param posX starting x position.
	 * @param posY starting y position.
	 * @param isAttack determines if the player isAttack.
	 * @param lastDir determines on which side of the box to affect.
	 */
	public void updatePlayerCollisionBox(int posX, int posY,boolean isAttack, dir lastDir) {
		
		for(int index = 0; index < 4;index++) {
			this.edges.add(new Point());
				switch(index) {
				case 0: // original one(p0)
					this.edges.get(index).x = posX;
					this.edges.get(index).y = posY;
					break;
				case 1:// p1(x +size, Y)
					this.edges.get(index).x = (posX+this.collisionSize+this.DIF);
					this.edges.get(index).y = posY;
					break;
				case 2:// p2(X, y+size)
					this.edges.get(index).x = posX;
					this.edges.get(index).y = (posY+this.collisionSize+this.DIF);
					break;
				case 3://p3(x+size, y+size)
					this.edges.get(index).x = (posX+this.collisionSize + this.DIF);
					this.edges.get(index).y = (posY+this.collisionSize + this.DIF);
					break;
			}
		}
		
		if(isAttack == false) {
			for(int index = 0; index < (this.collisionSize+this.DIF); index++) {
				this.top.get(index).y = this.edges.get(0).y;
				this.top.get(index).x = (this.edges.get(0).x + index);
				
				this.bottom.get(index).y = this.edges.get(2).y;
				this.bottom.get(index).x = (this.edges.get(2).x + index);
				
				this.left.get(index).x = this.edges.get(0).x;
				this.left.get(index).y = (this.edges.get(0).y + index);
				
				this.right.get(index).x = this.edges.get(1).x;
				this.right.get(index).y = (this.edges.get(1).y + index);
			}
		}else {
			int up = 0, left = 0, down = 0, right = 0;
			
			switch(lastDir) {
			case UP:
				up = this.attackRange;
				break;
			case DOWN:
				down = this.attackRange;
				break;
			case RIGHT:
				right = this.attackRange;
				break;
			case LEFT:
				left = this.attackRange;
				break;
			}
			
			for(int index = 0; index < (this.collisionSize+this.DIF); index++) {
				
				this.top.get(index).y = this.edges.get(0).y - up;
				this.top.get(index).x = (this.edges.get(0).x + index);
				
				this.bottom.get(index).y = this.edges.get(2).y+ down;
				this.bottom.get(index).x = (this.edges.get(2).x + index);
				
				this.left.get(index).x = this.edges.get(0).x-left;
				this.left.get(index).y = (this.edges.get(0).y + index);
				
				this.right.get(index).x = this.edges.get(1).x+right;
				this.right.get(index).y = (this.edges.get(1).y + index);
			}
		}
		
		
	}
	
	/**
	 * Gets the left side of the collision box.
	 * @return a list of Point DataType object.
	 */
	public ArrayList<Point> getLeft(){
		return this.left;
	}
	
	/**
	 * Gets the right side of the collision box.
	 * @return a list of Point DataType object.
	 */
	public ArrayList<Point> getRight(){
		return this.right;
	}
	
	/**
	 * Gets the bottom side of the collision box.
	 * @return a list of Point DataType object.
	 */
	public ArrayList<Point> getBottom() {
		return this.bottom;
	}
	
	/**
	 * Gets the top side of the collision box.
	 * @return a list of Point DataType object.
	 */
	public ArrayList<Point> getTop(){
		return this.top;
	}

}
