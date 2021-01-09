package Develop;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;

public class PowerBrick extends Brick{
	
	public PowerBrick(int x, int y, int width, int height, int powerUps) {
		super(x,y,width,height);
		this.color = color.cyan;
		this.value = 3 + powerUps;
	}
	
	public void defaultValue() {
		this.value = 4;
		this.color = color.cyan;
	}
}