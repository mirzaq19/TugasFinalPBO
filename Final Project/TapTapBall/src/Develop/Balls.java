package Develop;

import java.awt.Color;
import java.awt.Graphics;

public class Balls extends GameObject{
	private int ballXdir = -6;
	private int ballYdir = -6;
	

	public Balls(int x, int y, int width, int height, Color color) {
		super(x, y, width, height, color);
	}

	public void move() {
		x+=ballXdir;
		y+=ballYdir;
		
		if(x<0 || x > 665) {
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
}
