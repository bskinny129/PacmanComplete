import acm.graphics.GImage;


public class Pacman extends GridCharacter {

	private String[] imagesRight =  {"pacman1.png", "pacman2-right.png", "pacman3-right.png", "pacman2-right.png"};
	private String[] imagesLeft =  {"pacman1.png", "pacman2-left.png", "pacman3-left.png", "pacman2-left.png"};
	private String[] imagesUp =  {"pacman1.png", "pacman2-up.png", "pacman3-up.png", "pacman2-up.png"};
	private String[] imagesDown =  {"pacman1.png", "pacman2-down.png", "pacman3-down.png", "pacman2-down.png"};
	private int currentImage;
	private int turnTolerance = 4;
	private boolean moving = true;
	private int speed = 22;
	
	public Pacman(PacmanGame game){
		
		super(game, "pacman2-left.png", 16 + 22*13, 81+22*22);
		currentImage = 1;
	}
	
	public void nextImage(){
		currentImage ++;
		if (currentImage == 4){
			currentImage = 0;
		}
		
		if(getDirection() == "right"){
			setImage(imagesRight[currentImage]);
		}else if(getDirection() == "left"){
			setImage(imagesLeft[currentImage]);
		}else if(getDirection() == "up"){
			setImage(imagesUp[currentImage]);
		}else if(getDirection() == "down"){
			setImage(imagesDown[currentImage]);
		}
		
	}
	
	
	public void move(){
		
		warpIfNecessary();
		
		if(frontIsClear() ){
			if(getDirection() == "right"){
				move(speed,0);
			}else if(getDirection() == "left"){
				move(-speed,0);
			}else if(getDirection() == "down"){
				move(0,speed);
			}else if(getDirection() == "up"){
				move(0,-speed);
			}
			
		}
	}
	
	
	
	//PacMan is only allowed to change which way he is facing when he is
	//within a few pixels of the center of the square he is on
	public boolean isChangeDirectionAllowed(){
		
		int squareX = getSquareX();
		int squareY = getSquareY();
		
		int squareCenterPixelX = squareX * 22 +11; //11 is for half of the square
		int squareCenterPixelY = squareY * 22 +11 + 20; //20 is for the file menu offset
		
		//System.out.println("Square x center: " + squareCenterPixelX);
		//System.out.println("Square y center: " + squareCenterPixelY);
		
		double locationX = getX() + getWidth()/2;
		double locationY = getY() + getHeight()/2;
		
		//System.out.println("location x: " + locationX);
		//System.out.println("location y: " + locationY);
		
		if(Math.abs(locationX - squareCenterPixelX) < 4 && Math.abs(locationY - squareCenterPixelY) < 4){
			return true;
		}else{
			return false;
		}
		
	}
	

	
	
//-------------------------------------------------------------------------------------------------------------
	
	

	

	
	
}
