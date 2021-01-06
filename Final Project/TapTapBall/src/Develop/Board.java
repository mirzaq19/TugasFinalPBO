package Develop;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class Board extends GuiPanel {
	private SecureRandom randomNumbers = new SecureRandom();
	private boolean play = false;
	private boolean NewGame = true;
	private static String diff;
	private int ballposX;
	private int ballposY = 350;
	private int playerX = 310;
	private int score = 0;
	private int COLS;
	private int ROWS;
	private Ball ball;
	private Paddle paddle;
	private List<Brick> bricks;
	private int brickWidth;
	private int brickHeight;
	private int totalBricks;

	public Board() {
		ballposX = 150 + randomNumbers.nextInt(100);
		ball = new Ball(ballposX, ballposY, 20, 20, Color.yellow);
		paddle = new Paddle(playerX, 580, 100, 8, Color.green);

	}

	public void initBricks(int row, int col) {
		bricks = new ArrayList<>();
		int randomBrick=0;
		if (diff == "easy") {
			brickWidth = 540 / col;
			brickHeight = 150 / row;
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					bricks.add(new WhiteBrick(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight));
				}
			}
		} else if (diff == "medium") {
			brickWidth = 560 / col;
			brickHeight = 180 / row;
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					randomBrick = randomNumbers.nextInt(2);
					if(randomBrick == 0)
						bricks.add(new WhiteBrick(j * brickWidth + 70, i * brickHeight + 50, brickWidth, brickHeight));
					else if(randomBrick == 1)
						bricks.add(new RedBrick(j * brickWidth + 70, i * brickHeight + 50, brickWidth, brickHeight));
				}
			}
		} else if (diff == "hard") {
			brickWidth = 560 / col;
			brickHeight = 200 / row;
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
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
		g.drawString("" + score, 590, 30);

		// brick
		drawBricks((Graphics2D) g);

		// the paddle
		paddle.draw(g);

		// ball
		ball.draw(g);

		if (!play && score == 0) {
			g.setColor(Color.RED);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Welcome to TapTapBall Game", 160, 300);

			g.setColor(Color.RED);
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press (Space) to Play This Game", 200, 350);
		}

		if (totalBricks <= 0) {
			play = false;
			ball.setBallXdir(0);
			ball.setBallYdir(0);
			g.setColor(Color.RED);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("You Won", 260, 300);

			g.setColor(Color.RED);
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press (Enter) to Restart", 230, 350);
		}

		if (ball.getY() > 600) {
			play = false;
			ball.setBallXdir(0);
			ball.setBallYdir(0);
			g.setColor(new Color(222, 222, 222,0));
			g.fillRect(0, 0, Game.BWIDTH, Game.BHEIGHT);
			g.setColor(Color.RED);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Game Over, Scores: " + score, 190, 300);

			g.setColor(Color.RED);
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press (Enter) to Restart", 230, 350);
		}
		g.dispose();
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
	@Override
	public void update() {
		diff = getDiff();
		if(NewGame) {
			if (diff == "easy") {
				COLS = DifficultLevel.eCOLS;
				ROWS = DifficultLevel.eROWS;
				ball.setBallXdir(-3);
				ball.setBallYdir(-5);
			} else if (diff == "medium") {
				COLS = DifficultLevel.mCOLS;
				ROWS = DifficultLevel.mROWS;
				ball.setBallXdir(-3);
				ball.setBallYdir(-5);
			} else if (diff == "hard") {
				COLS = DifficultLevel.hCOLS;
				ROWS = DifficultLevel.hROWS;
				ball.setBallXdir(-4);
				ball.setBallYdir(-6);
			}
			totalBricks = COLS * ROWS;
			initBricks(ROWS, COLS);
			NewGame = false;
		}
		if (play) {
			if (new Rectangle(ballposX, ballposY, 20, 20)
					.intersects(new Rectangle(paddle.getX(), 550, 30, 8))) {
				ball.inverseDirY();
				ball.setBallXdir(ball.getBallXdir() + 1);
			}
			else if (new Rectangle(ballposX, ballposY, 20, 20)
					.intersects(new Rectangle(paddle.getX() + 70, 550, 30, 8))) {
				ball.inverseDirY();
				ball.setBallXdir(ball.getBallXdir() + 1);
			}
			else if(new Rectangle(ball.getX(), ball.getY(), 20, 20)
					.intersects(new Rectangle(paddle.getX(), paddle.getY(), 100, 8))) {
				ball.inverseDirY();
			}

			A: for (Brick brick : bricks) {
				if (brick.getValue() > 0) {
					Rectangle brickRect = new Rectangle(brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
					Rectangle ballRect = new Rectangle(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());

					if (ballRect.intersects(brickRect)) {
						brick.setValue(brick.getValue()-1);
						if(brick.getValue() == 0) {							
							totalBricks--;
						}
						score += 5;
						if (ball.getX() + ball.getWidth() - 1 <= brickRect.x
								|| ball.getX() + 1 >= brickRect.x + brickRect.width) {
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
	}

	public void mouseDragged(MouseEvent e) {
		
	}

	public void mouseMoved(MouseEvent e) {
		if (play)
			paddle.move(e);
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (!play)
				play = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (!play) {
				play = true;
				ballposX = 150 + randomNumbers.nextInt(100);
				ball.setX(ballposX);
				ball.setY(ballposY);
				if(diff == "easy") ball.easySpeed();
				else if (diff == "medium") ball.mediumSpeed();
				else if (diff == "hard") ball.hardSpeed();
				score = 0;
				totalBricks = COLS * ROWS;
				for (Brick brick : bricks) {
					if(brick instanceof WhiteBrick) ((WhiteBrick) brick).defaultValue();
					else if(brick instanceof RedBrick) ((RedBrick) brick).defaultValue();
					else if(brick instanceof BlueBrick) ((BlueBrick) brick).defaultValue();
				}
			}
		}
	}

}