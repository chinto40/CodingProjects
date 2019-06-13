package application.model.GameManager;

import javafx.scene.image.Image;

/**
 * Holds all the values for the animations.
 * @author Jacinto Molina
 */
public class Animation {
	
	private Image[] upAnim;
	private Image[] downAnim;
	private Image[] leftAnim;
	private Image[] RightAnim;
	
	
	private Image[] swimUp;
	private Image[] swimDown;
	private Image[] swimLeft;
	private Image[] swimRight;
	
	private Image[] attackUpAnim;
	private Image[] attackDownAnim;
	private Image[] attackLeftAnim;
	private Image[] attackRightAnim;
	
	private Image[] threeImageAnimation;
	
	//make it static to make sure there is only one. 
	private static long startTime;
	private static long endTime;
	
	private static long starter;
	private static long endTimer;
	private int currentFrame;
	private int attackFrame;
	private static boolean isStart;
	
	// i need to add attack animation
	
	
	// full player animaitons using the spriteSheet.
	/**
	 * Constructs a new Animation Object.
	 * @param fileName is the name of the Movement sprite sheet.
	 * @param fileAttack is the name of the Attack sprite sheet.
	 * @param swimFileName is the name of the Swim sprite sheet.
	 * @param row integer row value of the spritesheet.
	 * @param col integer col value of the spritesheet.
	 * @param player determines if its for a player/ enemy object.
	 * @param animationType determines the type of animation.
	 * @param isTile determines of its a tile object.
	 */
	public Animation(String fileName, String  fileAttack,String swimFileName ,  int row, int col,int player, int animationType, boolean isTile) { // int player, int animiationYtpe == 3 for tile and powerups.
	
		this.downAnim = getAnimation(fileName,row,col,player);
		this.leftAnim = getAnimation(fileName,(row),(col+1),player);
		this.RightAnim = getAnimation(fileName,(row),(col+2),player);
		this.upAnim = getAnimation(fileName,(row),(col+3),player);
		
		/*this.swimDown = getAnimation(swimFileName,row,col,player);
		this.swimLeft = getAnimation(swimFileName,(row),(col+1),player);
		this.swimRight = getAnimation(swimFileName,(row),(col+2),player);
		this.swimUp = getAnimation(swimFileName,(row),(col+3),player);
		*/
		
		this.attackDownAnim = getAnimation(fileAttack,0,0,animationType);
		this.attackLeftAnim = getAnimation(fileAttack,0,1,animationType);
		this.attackRightAnim = getAnimation(fileAttack,0,2,animationType);
		this.attackUpAnim = getAnimation(fileAttack,0,3,animationType);		
		this.startTime = System.currentTimeMillis();
		this.currentFrame = 0;
		this.attackFrame = 0;
		this.isStart = true;
		this.starter = System.currentTimeMillis();
		this.threeImageAnimation = null;
		
		if(isTile) {
			this.threeImageAnimation = getAnimation(fileName,row,col,0);
		}
		
	}
	
	/**
	 * Return The three images that make up the animation.
	 * @return An Image list for animation.
	 */
	public Image[] getThreeImageAnimation() {
		return this.threeImageAnimation;
	}
	
	//my awesome home made animation loop
	/**
	 * Gives out a number to iterate through the list. 
	 * @param milisec determines how fast we iterate.
	 * @return a number to be used to iterate through a list.
	 */
	public int playAnimation(int milisec) {
		
		this.endTime = System.currentTimeMillis();
		
		long finalTime = endTime - startTime;
		
		if(finalTime >  milisec) { // 100 miilisec
			
			this.startTime = System.currentTimeMillis(); // start a new time
			if(!(this.currentFrame >= 3)) { // if less than 3, add one
				this.currentFrame++;
			}else {
				this.currentFrame = 0; 
			}
			
		}
		
		if(this.currentFrame < 3) {
			return this.currentFrame;
		}else {
			return 0;
		}
		
	}
	
	
	// officially working.
	/**
	 * Gives out a number to iterate through the list for one cycle. 
	 * @param milisec determines how fast we iterate.
	 * @return a number to be used to iterate through a list.
	 */
	public int playAttackAnimation(int milisec) {
		
		if(this.isStart) {
			this.starter = System.currentTimeMillis();
			this.isStart = false;
		}
			
			endTimer = System.currentTimeMillis();
			
			long finalTime = endTimer - starter;
			
			if(finalTime >  milisec) { //100 miilisec
				this.starter = System.currentTimeMillis(); // start a new time
				//System.out.println("Times getting a new Frame.");
				if(this.attackFrame < 3) { // if less than 3, add one
					this.attackFrame++;
					//System.out.println("Incrementing attack animation: " + this.attackFrame);
				}
		}
			//System.out.println("animation: " + this.attackFrame);
		
		if(this.attackFrame < 3) {
			return this.attackFrame;
		}else {
			//System.err.println("Should never get here but it did. Animation Class.");
			//end of animation // idea anyways.
			this.attackFrame = 0;
			this.isStart = true;
			return 100;
		}
	}
	//------------Attacking animation ---------------
	/**
	 * Returns the attack Up list.
	 * @return a list of images.
	 */
	public Image[] getAttackUpAnim() {
		return this.attackUpAnim;
	}
	
	/**
	 * Returns the attack right list.
	 * @return a list of images.
	 */
	public Image[] getAttackRightAnim() {
		return this.attackRightAnim;
	}
	
	/**
	 * Returns the attack left list.
	 * @return a list of images.
	 */
	public Image[] getAttackLeftAnim() {
		return this.attackLeftAnim;
	}
	
	/**
	 * Returns the attack Down list.
	 * @return a list of images.
	 */
	public Image[] getAttackDownAnim() {
		return this.attackDownAnim;
	}
	
	//-----------------------
	
	/**
	 * Returns the Swim Up list.
	 * @return a list of images.
	 */
	public Image[] getSwimUp() {
		return this.swimUp;
	}
	
	/**
	 * Returns the Swim Down list.
	 * @return a list of images.
	 */
	public Image[] getSwimDown() {
		return this.swimDown;
	}
	/**
	 * Returns the Swim left list.
	 * @return a list of images.
	 */
	public Image[] getSwimLeft() {
		return this.swimLeft;
	}
	
	/**
	 * Returns the Swim right list.
	 * @return a list of images.
	 */
	public Image[] getSwimRight() {
		return this.swimRight;
	}
	
	//----------------------
	/**
	 * Returns the walk Up list.
	 * @return a list of images.
	 */
	public Image[] getUpAnim() {
		return this.upAnim;
	}
	
	/**
	 * Returns the walk down list.
	 * @return a list of images.
	 */
	public Image[] getDownAnim() {
		return this.downAnim;
	}
	/**
	 * Returns the walk left list.
	 * @return a list of images.
	 */
	public Image[] getLeftAnim() {
		return this.leftAnim;
	}
	/**
	 * Returns the walk right list.
	 * @return a list of images.
	 */
	public Image[] getRightAnim() {
		return this.RightAnim;
	}

	// going to use an array of images for animations
	/**
	 * Gets the Images it needs for the animation.
	 * @param name the name of the sprite-sheet.
	 * @param row starting row number.
	 * @param col starting col number.
	 * @param isPlayer boolean to determine if its a player.
	 * @return a list of images.
	 */
	public static Image[] getAnimation(String name, int row, int col, int isPlayer){
		Image[] anim = null;
		//System.out.println("is it the player? "+isPlayer);
		if(isPlayer == 0) {
		anim = SpriteParser.setAnimation(row, col, name);
		}else if(isPlayer == 1){
			anim = SpriteParser.setPlayerAnimation(row, col, name);
		}else if(isPlayer == 2){ //2
			anim = SpriteParser.setPlayerAttackAnimation(row, col, name);
		}else {
			return anim;
		}
		return anim;
	}

}
