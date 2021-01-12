package Develop;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class Board extends GuiPanel {
	private SecureRandom randomNumbers = new SecureRandom();
	private boolean play = false;
	private boolean NewGame = true;
	private boolean over = false;
	private boolean added=false;
	private boolean saved = false;
	private Font overTitleFont = new Font("serif", Font.BOLD, 35);
	private Font overDesFont = new Font("serif", Font.BOLD, 25);
	public static String diff;
	public static String tempDiff;
	private int prevHighscore=0;
	private int currentHighscore=0;
	private int ballposX;
	private int ballposY = 350;
	private int playerX = 31;
	private int score = 0;
	private int COLS;
	private int ROWS;
	public Ball ball;
	public Paddle paddle;
	public Paddle paddle2;
	public int longPaddle = 100;
	private ScoreManager scoreManager;
	private List<Brick> bricks;
	private List<Wall> walls;
	private int brickWidth;
	private int brickHeight;
	private int totalBricks;
	private int buttonWidth=220;
	private GuiButton backMenuButton;
	private int alpha=0;
	public int powerUps;

	public Board() {
		backMenuButton = new GuiButton(Game.BWIDTH/2-buttonWidth/2,330,buttonWidth,60);
		backMenuButton.setText("Back to Menu");
		backMenuButton.addActionListener((ActionEvent e)-> {
			play = false;
			resetBoard();
			GuiScreen.getInstance().setCurrentPanel("Menu");
		});
		bricks = new ArrayList<>();
		walls = new ArrayList<>();
		ballposX = 150 + randomNumbers.nextInt(100);
		ball = new Ball(ballposX, ballposY, 20, 20, Color.yellow);
		
		paddle = new Paddle(playerX, 580, longPaddle, 8, Color.green);
		scoreManager = new ScoreManager();
		scoreManager.loadScore();
	}

	public void initBricks(int row, int col) {
		int randomBrick=0;
		if (diff == "easy") {
			brickWidth = 540 / col;
			brickHeight = 150 / row;
			int countPower = 2;
			powerUps = 3;
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					randomBrick = randomNumbers.nextInt(5);
					if(powerUps == 0) {
						if(countPower > 1) {
							powerUps = 3;
							countPower--;
						}
					}
					if(randomBrick == 2 && powerUps > 0) {
						bricks.add(new PowerBrick(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight, powerUps));
						powerUps--;
					}
					else{
						bricks.add(new WhiteBrick(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight));
					}
						
				}
			}
		} else if (diff == "medium") {
			brickWidth = 560 / col;
			brickHeight = 180 / row;
			int countPower = 3;
			powerUps = 5;
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					randomBrick = randomNumbers.nextInt(8);
					if(powerUps == 0) {
						if(countPower > 1) {
							powerUps = 5;
							countPower--;
						}
					}
					if(randomBrick == 2 && powerUps > 0) {
						bricks.add(new PowerBrick(j * brickWidth + 70, i * brickHeight + 50, brickWidth, brickHeight, powerUps));
						powerUps--;
					}
					else {
						randomBrick = randomNumbers.nextInt(2);
						if(randomBrick == 0)
							bricks.add(new WhiteBrick(j * brickWidth + 70, i * brickHeight + 50, brickWidth, brickHeight));
						else if(randomBrick == 1)
							bricks.add(new RedBrick(j * brickWidth + 70, i * brickHeight + 50, brickWidth, brickHeight));
					}
					
				}
			}
		} else if (diff == "hard") {
			brickWidth = 560 / col;
			brickHeight = 200 / row;
			int countPower = 4;
			powerUps = 5;
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					randomBrick = randomNumbers.nextInt(7);
					if(powerUps == 0) {
						if(countPower > 1) {
							powerUps = 5;
							countPower--;
						}
					}
					if(randomBrick == 2 && powerUps > 0) {
						bricks.add(new PowerBrick(j * brickWidth + 70, i * brickHeight + 50, brickWidth, brickHeight, powerUps));
						powerUps--;
					}
					else {
					randomBrick = randomNumbers.nextInt(3);
						if(randomBrick == 0)
							bricks.add(new WhiteBrick(j * brickWidth + 70, i * brickHeight + 50, brickWidth, brickHeight));
						else if(randomBrick == 1)
							bricks.add(new RedBrick(j * brickWidth + 70, i * brickHeight + 50, brickWidth, brickHeight));
						else if(randomBrick == 2)
							bricks.add(new BlueBrick(j * brickWidth + 70, i * brickHeight + 50, brickWidth, brickHeight));
					}
				}
			}
		}
	}

	@Override
	public void render(Graphics2D g) {
		// background
		g.setColor(Color.black);
		g.fillRect(0, 0, 700, 600);

		// borders
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 600);
		g.fillRect(0, 0, 700, 3);
		g.fillRect(697, 0, 3, 600);

		// the scores
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 25));
		g.drawString("Score: " + score, 575, 30);
		
		//highscore
		g.setColor(Color.green);
		g.setFont(new Font("serif", Font.BOLD, 20));
		g.drawString("Highscore: "+currentHighscore, 20,30);

		// brick
		drawBricks((Graphics2D) g);

		//wall
		drawWall(g);

		// the paddle
		paddle.draw(g);

		// ball
		ball.draw(g);

		if (!play && score == 0) {
			g.setColor(Color.RED);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Welcome to TapTapBall Game", 160, 300);

			g.setColor(Color.GRAY);
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press (Space) to Play This Game", 200, 350);
		}
		if(totalBricks<=0||ball.getY()>600){
			longPaddle = 100;
			paddle = new Paddle(paddle.getX(), 580, longPaddle, 8, Color.green);

			play = false;
			over = true;
			ball.setBallXdir(0);
			ball.setBallYdir(0);
			g.setColor(new Color(30, 30, 30,alpha));
			g.fillRect(0, 0, Game.BWIDTH, Game.BHEIGHT);
			if (totalBricks <= 0) {
				g.setColor(Color.GREEN);
				g.setFont(overTitleFont);
				g.drawString("You Won, Score: "+score, Game.BWIDTH/2-GuiButton.getMessageWidth("You Won, Score: "+score, overTitleFont, g)/2, 150);
			}
			if (ball.getY() > 600) {
				g.setColor(Color.RED);
				g.setFont(overTitleFont);
				g.drawString("Game Over, Score: " + score,Game.BWIDTH/2-GuiButton.getMessageWidth("Game Over, Score: " + score, overTitleFont, g)/2, 150);
			}
			drawNewHighscore(g);
			g.setColor(Color.GRAY);
			g.setFont(overDesFont);
			g.drawString("Press (Enter) to Restart", Game.BWIDTH/2-GuiButton.getMessageWidth("Press (Enter) to Restart"+ score, overDesFont, g)/2, 250);
			
			if(!added){
				added = true;
				add(backMenuButton);
			}

			if(!saved){
				saveData();
				saved = true;
			}
		}
		super.render(g);
	}
	public void initWall(){
		if(diff == "medium" || diff == "hard"){
			walls.add(new Wall(70,330,90,15));
			walls.add(new Wall(540,330,90,15));
			if(diff == "hard"){
				walls.add(new Wall(Game.BWIDTH/2-45,330,90,15));
			}
		}
	}

	public void drawWall(Graphics2D g){
		for (Wall wall:walls){
			wall.draw(g);
		}
	}

	public void drawNewHighscore(Graphics2D g){
		if(score>prevHighscore){
			g.setColor(Color.GREEN);
			g.setFont(overDesFont);
			g.drawString("New Highcore!!",Game.BWIDTH/2-GuiButton.getMessageWidth("New Highcore!!", overDesFont, g)/2,190);
		}
	}

	public void saveData(){
		if(diff == "easy" && score>ScoreManager.easyScore) scoreManager.setCurrentEasy(score);
		else if(diff == "medium" && score>ScoreManager.mediumScore) scoreManager.setCurrentMedium(score);
		else if(diff == "hard" && score>ScoreManager.hardScore) scoreManager.setCurrentHard(score);
		scoreManager.SaveScore();
	}

	public void drawBricks(Graphics2D g) {
		for (Brick brick : bricks) {
			if (brick.getValue() > 0) {
				brick.draw(g);
			}
		}
	}
	public static String getDiff() {
		return diff;
	}

	public static void setDiff(String diff) {
		Board.diff = diff;
	}

	public void newGame(){
		if (diff == "easy") {
			prevHighscore = ScoreManager.easyScore;
			currentHighscore = ScoreManager.easyScore;
			COLS = DifficultLevel.eCOLS;
			ROWS = DifficultLevel.eROWS;
		} else if (diff == "medium") {
			prevHighscore = ScoreManager.mediumScore;
			currentHighscore = ScoreManager.mediumScore;
			COLS = DifficultLevel.mCOLS;
			ROWS = DifficultLevel.mROWS;
		} else if (diff == "hard") {
			prevHighscore = ScoreManager.hardScore;
			currentHighscore = ScoreManager.hardScore;
			COLS = DifficultLevel.hCOLS;
			ROWS = DifficultLevel.hROWS;
		}
		walls.clear();
		initWall();
		remove(backMenuButton);
		ballposX = 150 + randomNumbers.nextInt(100);
		ball.setX(ballposX);
		ball.setY(ballposY);
		ball.defaultSpeed(diff);
		score=0;
		totalBricks = COLS * ROWS;
		bricks.clear();
		initBricks(ROWS, COLS);
		NewGame = false;
	}

	public void resetBoard(){
		over = false;
		NewGame = true;
		alpha = 0;
		added = false;
		saved = false;
	}

	@Override
	public void update() {
		diff = getDiff();
		
		if(NewGame) {
			newGame();
		}
		if(currentHighscore<score){
			currentHighscore = score;
		}
		if (play) {

			for(Wall wall:walls){
				Rectangle wallRect = new Rectangle(wall.getX(),wall.getY(),wall.getWidth(),wall.getHeight());
				Rectangle ballRect = new Rectangle(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());

				if(ballRect.intersects(wallRect)){
					if (ball.getX() + ball.getWidth() - 2 <= wallRect.x
							|| ball.getX() + 2 >= wallRect.x + wallRect.width) {
						ball.inverseDirX();
					} else {
						ball.inverseDirY();
					}
				}
			}

			if (new Rectangle(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight()).intersects(new Rectangle(paddle.getX(), paddle.getY(), longPaddle/3, 8))) {
				ball.defaultSpeed(diff);
			}
			else if(new Rectangle(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight()).intersects(new Rectangle(paddle.getX()+(1*longPaddle/3), paddle.getY(), longPaddle/3, 8))) {
				ball.inverseDirY();
				if(ball.getBallXdir() < 0) ball.setBallXdir(ball.getBallXdir() + 1);
				else ball.setBallXdir(ball.getBallXdir()-1);
			}
			else if(new Rectangle(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight()).intersects(new Rectangle(paddle.getX()+(2*longPaddle/3), paddle.getY(), longPaddle/3, 8))) {
				ball.defaultSpeed(diff);
				ball.inverseDirX();
			}

			A: for (Brick brick : bricks) {
				if (brick.getValue() > 0) {
					Rectangle brickRect = new Rectangle(brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
					Rectangle ballRect = new Rectangle(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());
						
					
					if (ballRect.intersects(brickRect)) {
						if(brick.getValue() >= 4 && brick.getValue() <= 8) {
							if(brick.getValue() == 4) {
								ball.boostSpeed(diff);
							}else if(brick.getValue() == 5) {
								ball.defaultSpeed(diff);
							}else if(brick.getValue() == 6 || brick.getValue() == 7) {
								longPaddle = longPaddle + 50;
								paddle = new Paddle(paddle.getX(), 580, longPaddle, 8, Color.green);
							}else if(brick.getValue() == 8) {		
								longPaddle = longPaddle/2;
								paddle = new Paddle(paddle.getX(), 580, longPaddle, 8, Color.green);
							}
							brick.setValue(0);
						}
						else
							brick.setValue(brick.getValue()-1);
						
						if(brick.getValue() == 0) {							
							totalBricks--;
						}
						score += 5;
						if (ball.getX() + ball.getWidth() - 2<= brickRect.x
								|| ball.getX() + 2 >= brickRect.x + brickRect.width) {
							ball.inverseDirX();
						} else {
							ball.inverseDirY();								
						}
						break A;
					}
				}
			}
			ball.move();
		}
		if(alpha<200 && over) alpha+=2;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (play)
			paddle.move(e);
		super.mouseMoved(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (!play)
				play = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (!play && over) {
				play = true;
				resetBoard();
				remove(backMenuButton);
			}
		}
	}
}