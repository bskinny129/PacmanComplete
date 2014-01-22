
public class SmartGhost extends SmartMovementImage {
	
	public SmartGhost(PacmanGame game, String color){
		
		super(game,"Ghost" + color + "Down.png", 16 + (11*22), 81 + (14*22));
		
	}

}
