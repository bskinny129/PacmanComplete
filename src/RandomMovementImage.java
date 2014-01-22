import acm.util.RandomGenerator;


public class RandomMovementImage extends MovementImage {
	
	private RandomGenerator rgen = RandomGenerator.getInstance();

	public RandomMovementImage(PacmanGame game, String image, int x, int y) {
		
		super(game, image, x, y);
		
	}
	
	@Override
	public void turnDecisionPoint(boolean frontClear, boolean leftClear, boolean rightClear){
		
		//should randomly pick a new direction
		
		//System.out.println("random movement image");
		
		boolean isDirValid = false;
		String directionToTurn = "none";
		
		//keep picking a new random direction until you get one that isn't facing a wall
		while(isDirValid == false){
			int newDir = rgen.nextInt(3); //picks 0, 1, or 2
			if(newDir == 0){
				isDirValid = frontClear;
				directionToTurn = "none";
			}else if(newDir == 1){
				isDirValid = leftClear;
				directionToTurn = "left";
			}else{
				isDirValid = rightClear;
				directionToTurn = "right";
			}
		}
		
		if(directionToTurn == "left"){
			turnLeft();
		}else if(directionToTurn == "right"){
			turnRight();
		}
		
	}
	
	

}
