package Develop;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements MouseListener, MouseMotionListener,KeyListener, ActionListener{

	private static final long serialVersionUID = 1L;
	private boolean play = false;
	private Timer timer;
	private int score = 0;
	private int delay=8;
	private int playerX = 310;
	private int ballposX = 120;
	private int ballposY = 350;
	private int COLS=7;
	private int ROWS= 3;
	private Ball ball;
	private Paddle paddle;
	private List<Brick> bricks;
	private int brickWidth;
	private int brickHeight;
	private int totalBricks = COLS*ROWS;
	
	public Board() {
        addMouseListener(this);
        addMouseMotionListener((MouseMotionListener) this);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
        ball = new Ball(ballposX,ballposY,20,20,Color.yellow);
        paddle = new Paddle(playerX,550,100,8,Color.green);
        initBricks(ROWS,COLS);
        timer=new Timer(delay,this);
		timer.start();
	}
	
	public void initBricks(int row,int col) {
		bricks = new ArrayList<>();
		brickWidth = 540/col;
		brickHeight = 150/row;
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				bricks.add(new Brick(j*brickWidth+80,i*brickHeight+50,brickWidth,brickHeight,1,Color.white));
			}
		}
	}
	
	public void paint(Graphics g) {
		// background
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);
		
		// borders
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(683, 0, 3, 592);
		
		// the scores 		
		g.setColor(Color.white);
		g.setFont(new Font("serif",Font.BOLD, 25));
		g.drawString(""+score, 590,30);
		
		//brick
		drawBricks((Graphics2D)g);
		
		// the paddle
		paddle.draw(g);
		
		//ball
		ball.draw(g);
		
		if(!play && score == 0) {
            g.setColor(Color.RED);
            g.setFont(new Font("serif",Font.BOLD, 30));
            g.drawString("Welcome to TapTapBall Game", 160,300);
            
            g.setColor(Color.RED);
            g.setFont(new Font("serif",Font.BOLD, 20));           
            g.drawString("Press (Space) to Play This Game", 200,350);  
		}
		
		if(totalBricks <= 0){
			 play = false;
			 ball.setBallXdir(0);
			 ball.setBallYdir(0);
             g.setColor(Color.RED);
             g.setFont(new Font("serif",Font.BOLD, 30));
             g.drawString("You Won", 260,300);
             
             g.setColor(Color.RED);
             g.setFont(new Font("serif",Font.BOLD, 20));           
             g.drawString("Press (Enter) to Restart", 230,350);  
		}
		
		if(ball.getY() > 570){
			 play = false;
			 ball.setBallXdir(0);
			 ball.setBallYdir(0);
             g.setColor(Color.RED);
             g.setFont(new Font("serif",Font.BOLD, 30));
             g.drawString("Game Over, Scores: "+score, 190,300);
             
             g.setColor(Color.RED);
             g.setFont(new Font("serif",Font.BOLD, 20));           
             g.drawString("Press (Enter) to Restart", 230,350);        
        }
		g.dispose();
		
	}
	
	public void drawBricks(Graphics2D g) {
		for(Brick brick : bricks) {
			if(brick.getValue()>0) {
				brick.draw(g);				
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(play) {
			if(new Rectangle(ball.getX(),ball.getY(),20,20).intersects(new Rectangle(paddle.getX(),550,100,8))) {
				ball.inverseDirY();
			}
			
			A: for(Brick brick:bricks) {
				if(brick.getValue()>0) {
					Rectangle rect = new Rectangle(brick.getX(),brick.getY(),brick.getWidth(),brick.getHeight());
					Rectangle ballRect = new Rectangle(ball.getX(),ball.getY(),ball.getWidth(),ball.getHeight());
					Rectangle brickRect = rect;
					
					if(ballRect.intersects(brickRect)) {
						brick.setValue(0);
						totalBricks--;
						score+=5;
						if( ball.getX()+ball.getWidth()-1 <= brickRect.x || ball.getX()+1 >= brickRect.x+brickRect.width) {
							ball.inverseDirX();
						}else{
							ball.inverseDirY();
						}
						break A;
					}
				}
			}
			ball.move();
		}
		
		repaint();
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(play)
			paddle.move(e);		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE){
			if(!play)
				play = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER){         
			if(!play) {				
				play = true;
				ball.setX(ballposX);
				ball.setY(ballposY);
				ball.defaultSpeed();
				score = 0;
				totalBricks = COLS*ROWS;
				for(Brick brick:bricks) {
					brick.setValue(1);
				}
				repaint();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {	
	}

}
