package Develop;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements MouseListener, MouseMotionListener, ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean play = false;
	private Timer timer;
	private int delay=8;
	private int playerX = 310;
	private int ballposX = 120;
	private int ballposY = 350;
	private Balls ball;
	private Paddle paddle;
	

	public Board() {
        addMouseListener(this);
        addMouseMotionListener((MouseMotionListener) this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
        ball = new Balls(ballposX,ballposY,20,20,Color.yellow);
        paddle = new Paddle(playerX,550,100,8,Color.green);
        timer=new Timer(delay,this);
		timer.start();
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
		
		// the paddle
		paddle.draw(g);
		
		//ball
		ball.draw(g);
		
		g.dispose();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(play) {
			if(new Rectangle(ball.getX(),ball.getY(),20,20).intersects(new Rectangle(paddle.getX(),550,100,8))) {
				ball.inverseDirY();
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
		play = true;
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



}
