package Develop;

import java.awt.Color;
import java.awt.Graphics;

public class Ball extends GameObject{
	private int ballXdir;
	private int ballYdir;
	

	public Ball(int x, int y, int width, int height, Color color) {
		super(x, y, width, height, color);
	}

	public void move() {
		x+=ballXdir;
		y+=ballYdir;
		
		if(x<0 || x > 678) {
			ballXdir = -ballXdir;
		}
		if(y<0) {
			ballYdir = -ballYdir;
		}
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(x,y, width, height);
	}
	
	public void inverseDirX() {
		ballXdir = -ballXdir;
	}
	
	public void inverseDirY() {
		ballYdir = -ballYdir;
	}

	public int getBallXdir() {
		return ballXdir;
	}

	public void setBallXdir(int ballXdir) {
		this.ballXdir = ballXdir;
	}

	public int getBallYdir() {
		return ballYdir;
	}

	public void setBallYdir(int ballYdir) {
		this.ballYdir = ballYdir;
	}
	
	public void easySpeed() {
		this.ballXdir = -2;
		this.ballYdir = -4;
	}
	public void mediumSpeed() {
		this.ballXdir = -3;
		this.ballYdir = -5;
	}
	public void hardSpeed() {
		this.ballXdir = -4;
		this.ballYdir = -6;
	}
	
}
