package Develop;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class Paddle extends GameObject{

	public Paddle(int x, int y, int width, int height, Color color) {
		super(x, y, width, height, color);
	}

	public void move(MouseEvent e) {
		x = e.getX()-width/2;
		if(x<10) {
			x=10;
		}
		
		if(x>575) {
			x=575;
		}
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}
}
