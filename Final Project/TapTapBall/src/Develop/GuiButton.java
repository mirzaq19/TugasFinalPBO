package Develop;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class GuiButton {
	private State currentState = State.RELEASED;
	private Rectangle clickBox;
	private ArrayList<ActionListener> actionListeners;
	private String text = "";
	
	private Color main;
	private Color hover;
	private Color pressed;
	private Font font = new Font("serif",Font.BOLD, 30);
	public GuiButton(int x, int y, int width, int height){
		clickBox = new Rectangle(x, y, width, height);
		actionListeners = new ArrayList<ActionListener>();
		main = new Color(66,99,140);
		hover = new Color(56,86,119);
		pressed = new Color(42,67,86);
	}
	
	public void update() {
	}
	
	public void render(Graphics2D g){
		if(currentState == State.RELEASED){
			g.setColor(main);
			g.fill(clickBox);
		}
		else if(currentState == State.PRESSED){
			g.setColor(pressed);
			g.fill(clickBox);
		}
		else{
			g.setColor(hover);
			g.fill(clickBox);
		}
		g.setColor(Color.white);
		g.setFont(font);
		g.drawString(text, clickBox.x + clickBox.width / 2  - getMessageWidth(text, font, g) / 2, clickBox.y + clickBox.height / 2  + getMessageHeight(text, font, g) / 2);
	}
	
	public void addActionListener(ActionListener listener){
		actionListeners.add(listener);
	}
	
	public void mousePressed(MouseEvent e) {
		if(clickBox.contains(e.getPoint())){
			currentState = State.PRESSED;
		}
	}

	public void mouseReleased(MouseEvent e) {
		if(clickBox.contains(e.getPoint())){
			for(ActionListener al : actionListeners){
				al.actionPerformed(null);
			}

                }
		currentState = State.RELEASED;
	}

	public void mouseDragged(MouseEvent e) {
		if(clickBox.contains(e.getPoint())){
			currentState = State.PRESSED;
		}
		else{
			currentState = State.RELEASED;
		}
	}

	public void mouseMoved(MouseEvent e) {
		if(clickBox.contains(e.getPoint())){
			currentState = State.HOVER;
		}
		else{
			currentState = State.RELEASED;
		}
	}
	
	public int getX(){
		return clickBox.x;
	}
	
	public int getY(){
		return clickBox.y;
	}
	
	public int getWidth(){
		return clickBox.width;
	}
	
	public int getHeight(){
		return clickBox.height;
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public static int getMessageWidth(String message, Font font, Graphics2D g) {
		g.setFont(font);
		Rectangle2D bounds = g.getFontMetrics().getStringBounds(message, g);
		return (int) bounds.getWidth();
	}

	public static int getMessageHeight(String message, Font font, Graphics2D g) {
		g.setFont(font);
		if(message.length() == 0) return 0;
		TextLayout tl = new TextLayout(message, font, g.getFontRenderContext());
		return (int) tl.getBounds().getHeight();
	}
	
	private enum State{
		HOVER, RELEASED, PRESSED
	}

}

