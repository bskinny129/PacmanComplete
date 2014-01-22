import acm.graphics.GImage;


public class GridCharacter extends GImage {

	private String facing = "left";
	private PacmanGame pacmanGame;
	private String originalImage;
	
	public GridCharacter(PacmanGame game, String name, double x, double y) {
		super(name, x, y);
		pacmanGame = game;
		originalImage = name;
	}

	public void setToOriginalImage(){
		this.setImage(originalImage);
	}
	
	public String getDirection(){
		return facing;
	}
	
	public void faceDirection(String newDir){
		facing = newDir;
	}
	
	private double getCenterX(){
		return (getX() + getWidth()/2 );
	}
	private double getCenterY(){
		return (getY() + getHeight()/2);
	}
	public int getSquareX(){
		return (int) ( (getCenterX()-2) / 22);
	}
	public int getSquareY(){
		return (int) ((getCenterY() ) / 22); 
	}
	
	private int getNextX(){
		if(getDirection() == "right"){
			return getSquareX() + 1;
		}else if(getDirection() == "left"){
			return getSquareX() - 1;
		}else{
			return getSquareX();
		}
	}
	
	private int getNextY(){
		if(getDirection() == "up"){
			return getSquareY() - 1;
		}else if(getDirection() == "down"){
			return getSquareY() + 1;
		}else{
			return getSquareY();
		}
	}
	
	public boolean frontIsClear(){
		int nextX = getNextX();
		int nextY = getNextY();
		
		if(pacmanGame.getBoard().isWallSpot(nextX, nextY) == true){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean leftIsClear(){
		turnLeft();
		boolean clear = frontIsClear();
		turnRight();
		return clear;
	}
	
	public boolean rightIsClear(){
		turnRight();
		boolean clear = frontIsClear();
		turnLeft();
		return clear;
	}
	public void turnLeft(){
		if(facing == "up"){
			facing = "left";
		}else if(facing == "right"){
			facing = "up";
		}else if(facing == "left"){
			facing = "down";
		}else if(facing == "down"){
			facing = "right";
		}
	}
	public void turnRight(){
		if(facing == "up"){
			facing = "right";
		}else if(facing == "right"){
			facing = "down";
		}else if(facing == "left"){
			facing = "up";
		}else if(facing == "down"){
			facing = "left";
		}
	}
	
	public void warpIfNecessary(){

		if(getSquareX() == 1 && getSquareY() == 17 && getDirection()=="left" ){
			this.setLocation(26*22 + 16,getY());
		}
		if(getSquareX() == 26 && getSquareY() == 17 && getDirection()=="right"){
			this.setLocation(-6,getY());
		}
	}
	
	
}
