package Develop;

import java.awt.Color;

public class WhiteBrick extends Brick{
	public WhiteBrick(int x, int y, int width, int height) {
		super(x,y,width,height);
		this.color = Color.white;
		this.value = 1;
	}
	
	public void defaultValue() {
		this.value = 1;
		this.color = Color.white;
	}
}