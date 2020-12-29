package Develop;

import java.awt.Color;
import java.awt.Graphics;

public class Bricks extends GameObject{

	public Bricks(int x, int y, int width, int height, Color color) {
		super(x, y, width, height, color);
	}

	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}
}
