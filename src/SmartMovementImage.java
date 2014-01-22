import java.util.ArrayList;

import acm.util.RandomGenerator;


public class SmartMovementImage extends MovementImage {

	private RandomGenerator rgen = RandomGenerator.getInstance();

	public SmartMovementImage(PacmanGame game, String image, int x, int y) {

		super(game, image, x, y);

	}

	@Override
	public void turnDecisionPoint(boolean frontClear, boolean leftClear, boolean rightClear){

		//create a list that contains all the valid directions we could end up facing
		ArrayList<String> allowedDirections = new ArrayList<String>();
		if(frontClear){
			allowedDirections.add(getDirection());
		}
		if(leftClear){
			turnLeft();
			allowedDirections.add(getDirection());
			turnRight();
		}
		if(rightClear){
			turnRight();
			allowedDirections.add(getDirection());
			turnLeft();
		}


		int randomNum = rgen.nextInt(5);
		if(randomNum == 0){
			//20% of the time do a random allowed move
			int randomSpot = rgen.nextInt(allowedDirections.size());
			faceDirection(allowedDirections.get(randomSpot));
		}else{
			//80% of the time do the perfect move

			if(PacmanGame.game.isGhostScaredMode()){
				worstMove(allowedDirections);
			}else{
				bestMove(allowedDirections);
			}
		}


	}

	private double calcDistance(double x1, double y1, double x2, double y2){
		return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
	}


	private void bestMove(ArrayList<String> allowedDirections){
		Pacman pacman = PacmanGame.game.pacman;
		double bestScore = 10000;
		String bestDir = "none";
		double score = 0;

		//go through all allowed moves and determine best
		//the best is determined by the move that would make the ghost closest to pacman
		for(int i=0;i<allowedDirections.size();i++){
			String currDir = allowedDirections.get(i);

			if(currDir == "right"){
				score = calcDistance(getX()+22, getY(), pacman.getX(), pacman.getY());
			}else if(currDir == "left"){
				score = calcDistance(getX()-22, getY(), pacman.getX(), pacman.getY());
			}else if(currDir == "up"){
				score = calcDistance(getX(), getY()-22, pacman.getX(), pacman.getY());
			}else if(currDir == "down"){
				score = calcDistance(getX(), getY()+22, pacman.getX(), pacman.getY());
			}

			if(score < bestScore){
				bestScore = score;
				bestDir = currDir;
			}

		}
		faceDirection(bestDir);
	}

	
	private void worstMove(ArrayList<String> allowedDirections){
		Pacman pacman = PacmanGame.game.pacman;
		double worstScore = 0;
		String worstDir = "none";
		double score = 0;

		//go through all allowed directions and determine worst
		//the worst is determined by the move that would make the ghost farthest from pacman
		for(int i=0;i<allowedDirections.size();i++){
			String currDir = allowedDirections.get(i);

			if(currDir == "right"){
				score = calcDistance(getX()+22, getY(), pacman.getX(), pacman.getY());
			}else if(currDir == "left"){
				score = calcDistance(getX()-22, getY(), pacman.getX(), pacman.getY());
			}else if(currDir == "up"){
				score = calcDistance(getX(), getY()-22, pacman.getX(), pacman.getY());
			}else if(currDir == "down"){
				score = calcDistance(getX(), getY()+22, pacman.getX(), pacman.getY());
			}

			if(score > worstScore){
				worstScore = score;
				worstDir = currDir;
			}

		}
		faceDirection(worstDir);
	}

}
