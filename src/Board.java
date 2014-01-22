import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import acm.graphics.GRect;



public class Board extends GRect{

	private int[][] state;
	static int[][] starterBoard = {
		{0, 0, 0, 0, 0 , 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
		{0, 0, 0, 0, 0 , 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0 , 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{6, 4, 4, 4, 4 , 4, 4, 4, 4, 4, 4, 4, 4, 5, 6, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5},
		{3, 1, 1, 1, 1 , 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3}, 
		{3, 1, 6, 4, 4 , 5, 1, 6, 4, 4, 4, 5, 1, 3, 3, 1, 6, 4, 4, 4, 5, 1, 6, 4, 4, 5, 1, 3}, 
		{3, 2, 3, 0, 0 , 3, 1, 3, 0, 0, 0, 3, 1, 3, 3, 1, 3, 0, 0, 0, 3, 1, 3, 0, 0, 3, 2, 3},
		{3, 1, 8, 4, 4 , 7, 1, 8, 4, 4, 4, 7, 1, 8, 7, 1, 8, 4, 4, 4, 7, 1, 8, 4, 4, 7, 1, 3},
		{3, 1, 1, 1, 1 , 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3},
		{3, 1, 6, 4, 4 , 5, 1, 6, 5, 1, 6, 4, 4, 4, 4, 4, 4, 5, 1, 6, 5, 1, 6, 4, 4, 5, 1, 3},
		{3, 1, 8, 4, 4 , 7, 1, 3, 3, 1, 8, 4, 4, 5, 6, 4, 4, 7, 1, 3, 3, 1, 8, 4, 4, 7, 1, 3}, 
		{3, 1, 1, 1, 1 , 1, 1, 3, 3, 1, 1, 1, 1, 3, 3, 1, 1, 1, 1, 3, 3, 1, 1, 1, 1, 1, 1, 3},
		{8, 4, 4, 4, 4 , 5, 1, 3, 8, 4, 4, 5, 0, 3, 3, 0, 6, 4, 4, 7, 3, 1, 6, 4, 4, 4, 4, 7},
		{0, 0, 0, 0, 0 , 3, 1, 3, 6, 4, 4, 7, 0, 8, 7, 0, 8, 4, 4, 5, 3, 1, 3, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0 , 3, 1, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 1, 3, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0 , 3, 1, 3, 3, 0, 6, 4, 4, 4, 4, 4, 4, 5, 0, 3, 3, 1, 3, 0, 0, 0, 0, 0}, 
		{4, 4, 4, 4, 4 , 7, 1, 8, 7, 0, 3, 0, 0, 0, 0, 0, 0, 3, 0, 8, 7, 1, 8, 4, 4, 4, 4, 4},
		{0, 0, 0, 0, 0 , 0, 1, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
		{4, 4, 4, 4, 4 , 5, 1, 6, 5, 0, 3, 0, 0, 0, 0, 0, 0, 3, 0, 6, 5, 1, 6, 4, 4, 4, 4, 4},
		{0, 0, 0, 0, 0 , 3, 1, 3, 3, 0, 8, 4, 4, 4, 4, 4, 4, 7, 0, 3, 3, 1, 3, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0 , 3, 1, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 1, 3, 0, 0, 0, 0, 0}, 
		{0, 0, 0, 0, 0 , 3, 1, 3, 3, 0, 6, 4, 4, 4, 4, 4, 4, 5, 0, 3, 3, 1, 3, 0, 0, 0, 0, 0},
		{6, 4, 4, 4, 4 , 7, 1, 8, 7, 0, 8, 4, 4, 5, 6, 4, 4, 7, 0, 8, 7, 1, 8, 4, 4, 4, 4, 5},
		{3, 1, 1, 1, 1 , 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3},
		{3, 1, 6, 4, 4 , 5, 1, 6, 4, 4, 4, 5, 1, 3, 3, 1, 6, 4, 4, 4, 5, 1, 6, 4, 4, 5, 1, 3},
		{3, 1, 8, 4, 5 , 3, 1, 8, 4, 4, 4, 7, 1, 8, 7, 1, 8, 4, 4, 4, 7, 1, 3, 6, 4, 7, 1, 3}, 
		{3, 2, 1, 1, 3 , 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 1, 1, 2, 3},
		{8, 4, 5, 1, 3 , 3, 1, 6, 5, 1, 6, 4, 4, 4, 4, 4, 4, 5, 1, 6, 5, 1, 3, 3, 1, 6, 4, 7},
		{6, 4, 7, 1, 8 , 7, 1, 3, 3, 1, 8, 4, 4, 5, 6, 4, 4, 7, 1, 3, 3, 1, 8, 7, 1, 8, 4, 5},
		{3, 1, 1, 1, 1 , 1, 1, 3, 3, 1, 1, 1, 1, 3, 3, 1, 1, 1, 1, 3, 3, 1, 1, 1, 1, 1, 1, 3},
		{3, 1, 6, 4, 4 , 4, 4, 7, 8, 4, 4, 5, 1, 3, 3, 1, 6, 4, 4, 7, 8, 4, 4, 4, 4, 5, 1, 3},
		{3, 1, 8, 4, 4 , 4, 4, 4, 4, 4, 4, 7, 1, 8, 7, 1, 8, 4, 4, 4, 4, 4, 4, 4, 4, 7, 1, 3},
		{3, 1, 1, 1, 1 , 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3},
		{8, 4, 4, 4, 4 , 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 7},
		{0, 0, 0, 0, 0 , 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0 , 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
	};

	private BufferedImage[] boardTileImages = new BufferedImage[9];


	public Board(){
		super(0,0, PacmanGame.APPLICATION_WIDTH, PacmanGame.APPLICATION_HEIGHT);

		state  = Board.starterBoard;

		try {
			boardTileImages[0] = ImageIO.read(new File("blank.png"));
			boardTileImages[1] = ImageIO.read(new File("small-dot.png"));
			boardTileImages[2] = ImageIO.read(new File("big-dot.png"));
			boardTileImages[3] = ImageIO.read(new File("vertical-line.png"));
			boardTileImages[4] = ImageIO.read(new File("horizontal-line.png"));
			boardTileImages[5] = ImageIO.read(new File("corner-DL.png"));
			boardTileImages[6] = ImageIO.read(new File("corner-DR.png"));
			boardTileImages[7] = ImageIO.read(new File("corner-UL.png"));
			boardTileImages[8] = ImageIO.read(new File("corner-UR.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	
	public int getSquareValue(int x, int y){
		//have to do y then x because board is inversed
		return state[y][x];
	}

	public void updateSquare(int x, int y, int newValue){
		//have to do y then x because board is inversed
		state[y][x] = newValue;
	}


	public void paint(Graphics g) {
		super.paint(g);
		

		for(int x=0; x < state.length; x++){
			for(int y=0; y < state[0].length; y++){
				//have to do y then x because board is inversed
				g.drawImage(boardTileImages[state[x][y]], y*22, x*22, null);
			}
		}
	}

//-----------------------------------------------------------------------------------------------------------
	
	public boolean isWallSpot(int x, int y){
		
		int value = getSquareValue(x, y);
		if(value >= 3){
			return true;
		}else{
			return false;
		}
		
	}
	
	public boolean isDotSpot(int x, int y){
		int value = getSquareValue(x, y);
		if(value == 1 || value == 2){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isBigDotSpot(int x, int y){
		int value = getSquareValue(x, y);
		if(value == 2){
			return true;
		}else{
			return false;
		}
	}

}