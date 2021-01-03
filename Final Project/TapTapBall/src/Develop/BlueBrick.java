package Develop;

import java.awt.Color;

public class BlueBrick extends Brick{
	public BlueBrick(int x, int y, int width, int height) {
		super(x,y,width,height);
		this.color = Color.blue;
		this.value = 3;
	}
	
	public void defaultValue() {
		this.value = 3;
		this.color = Color.blue;
	}
}
