package Develop;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Brick extends GameObject{
	protected int value;
	
	public Brick(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
		g.setStroke(new BasicStroke(3));
		g.setColor(Color.black);
		g.drawRect(x, y, width, height);
		
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
		if(value == 1) {
			this.color = Color.white;
		}else if(value == 2) {
			this.color = Color.red;
		}
	}
}
