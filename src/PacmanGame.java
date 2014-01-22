import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import acm.graphics.GLabel;
import acm.program.GraphicsProgram;
import acm.util.MediaTools;


public class PacmanGame extends GraphicsProgram {

	//A good pacman reference guide:
	//http://home.comcast.net/~jpittman2/pacman/pacmandossier.html

	public static final int APPLICATION_WIDTH = 22*28;
	public static final int APPLICATION_HEIGHT = 22*36 + 20; // 20 is for the file menu
	private Board board;
	public Pacman pacman;
	private int dotsEaten = 0;
	private GLabel dotsEatenLabel;
	private Ghost ghost, ghost2;
	private SmartGhost smartGhost, smartGhost2;
	public static PacmanGame game;
	private int ghostScaredCounter = 0;
	private AudioClip startMusic, eatingSound, deathSound, eatGhostSound;
	private GLabel clickToStartLabel;
	private boolean playing = false;
	private boolean ghostScared = false;

	public void run(){

		setup();
		play();

	}

	private void setup(){

		game = this;

		board = new Board();
		add(board);

		pacman = new Pacman(this);
		add(pacman);

		addKeyListeners();
		addMouseListeners();

		dotsEatenLabel = new GLabel("Dots: 0", 5, 15);
		dotsEatenLabel.setColor(Color.white);
		add(dotsEatenLabel);

		ghost = new Ghost(this, "Blue");
		ghost.move(22, 0);
		add(ghost);

		ghost2 = new Ghost(this, "Red");
		ghost2.move(-22, 0);
		add(ghost2);

		smartGhost = new SmartGhost(this, "Pink");
		smartGhost.move(22, 0);
		add(smartGhost);

		smartGhost2 = new SmartGhost(this, "Orange");
		smartGhost2.move(-22, 0);
		add(smartGhost2);

		startMusic = MediaTools.loadAudioClip("pacman_beginning.wav");
		eatingSound = MediaTools.loadAudioClip("pacman_chomp.wav");
		deathSound = MediaTools.loadAudioClip("pacman_death.wav");
		eatGhostSound = MediaTools.loadAudioClip("pacman_eatghost.wav");

		clickToStartLabel = new GLabel("Click to Start", 218,330);
		clickToStartLabel.setFont(new Font("Arial",0,32));
		clickToStartLabel.setColor(Color.WHITE);
		add(clickToStartLabel);
	}

	private void play(){
		while(true){

			if(playing){
				pause(150);

				pacman.nextImage();
				pacman.move();

				processSpot();

				updateLabels();

				ghost.move();
				ghost2.move();
				smartGhost.move();
				smartGhost2.move();

				ghostScaredCounter--;
				if(ghostScaredCounter == 0){
					ghostScared = false;
					setGhostsScared(ghostScared);
				}
			}
		}
	}


	//----------------------------------------------------------------------------------------


	private void processSpot(){
		if(board.isDotSpot(pacman.getSquareX(), pacman.getSquareY())){
			if(board.isBigDotSpot(pacman.getSquareX(), pacman.getSquareY())){
				//set ghost to scared for a while
				ghostScaredCounter = 55;
				ghostScared = true;
				setGhostsScared(ghostScared);
			}

			board.updateSquare(pacman.getSquareX(), pacman.getSquareY(), 0);
			dotsEaten++;
		}
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP){
			pacman.faceDirection("up");
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			pacman.faceDirection("down");
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			pacman.faceDirection("left");
		}else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			pacman.faceDirection("right");
		}
	}

	public void mousePressed(MouseEvent e) {
		if(playing == false){
			remove(clickToStartLabel);
			startMusic.play();
			pause(4000);
			playing = true;
		}
	}

	public Board getBoard(){
		return board;
	}

	private void updateLabels(){
		dotsEatenLabel.setLabel("Dots: " + dotsEaten);
	}

	private void setGhostsScared(boolean scared){
		if(scared){
			ghost.setImage("scared-ghost.png");
			ghost2.setImage("scared-ghost.png");
			smartGhost.setImage("scared-ghost.png");
			smartGhost2.setImage("scared-ghost.png");
		}else{
			ghost.setToOriginalImage();
			ghost2.setToOriginalImage();
			smartGhost.setToOriginalImage();
			smartGhost2.setToOriginalImage();
		}

	}
	
	public boolean isGhostScaredMode(){
		return ghostScared;
	
	}

}
