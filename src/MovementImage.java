import acm.graphics.GImage;


public class MovementImage extends GridCharacter {
	
	private int numMoves = 0;
	private int speed = 22;

	public MovementImage(PacmanGame game, String image, int x, int y) {
		
		super(game,image, x, y);
		faceDirection("up");
	}

	public void move(){
		
		warpIfNecessary();
		
		//ghost first has to first move out of the box
		if(numMoves < 3){
			move(0,-speed);
		}else{
			if(getDirection() == "up"){
				move(0,-speed);
			}else if(getDirection() == "down"){
				move(0,speed);
			}else if(getDirection() == "left"){
				move(-speed,0);
			}else if(getDirection() == "right"){
				move(speed,0);
			}
			
			turnDecisionPoint(frontIsClear(), leftIsClear(), rightIsClear());
		}
		numMoves++;
	}
	
	public void turnDecisionPoint(boolean frontClear, boolean leftClear, boolean rightClear){
		System.out.println("This code shouldn't be reached");
	}
	
}
