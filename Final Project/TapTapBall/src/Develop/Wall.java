package Develop;

import java.awt.Color;
import java.awt.Graphics2D;

public class Wall extends GameObject {

    public Wall(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.color = new Color(210, 235, 190);
    }
    
    public void draw(Graphics2D g){
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
}
