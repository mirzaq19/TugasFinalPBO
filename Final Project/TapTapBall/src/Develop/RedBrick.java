package Develop;

import java.awt.Color;

public class RedBrick extends Brick{
	public RedBrick(int x, int y, int width, int height) {
		super(x,y,width,height);
		this.color = Color.red;
		this.value = 2;
	}
	
	public void defaultValue() {
		this.value = 2;
		this.color = Color.red;
	}
}
