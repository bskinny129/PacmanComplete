import acm.graphics.GImage;


public class Ghost extends RandomMovementImage {
	
	public Ghost(PacmanGame game, String color){
		
		super(game,"Ghost" + color + "Down.png", 16 + (14*22), 81 + (14*22));
		
	}

}

