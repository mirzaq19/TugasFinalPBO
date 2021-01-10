package Develop;

import java.awt.Color;

public class PowerBrick extends Brick{
	
	public PowerBrick(int x, int y, int width, int height, int powerUps) {
		super(x,y,width,height);
		this.color = Color.cyan;
		this.value = 3 + powerUps;
	}
	
	public void defaultValue() {
		this.value = 4;
		this.color = Color.cyan;
	}
}