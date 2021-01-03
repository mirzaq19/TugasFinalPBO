package Develop;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class GuiScreen {
	
	
	private static GuiScreen screen;
	private HashMap<String, GuiPanel> PanelFill;
	private String currentPanel = "";
	
	public GuiScreen() {
		PanelFill = new HashMap<String, GuiPanel>();
	}
	public static GuiScreen getInstance() {
		if (screen == null) {
			screen = new GuiScreen();
		}
		return screen;
	}
	
	public void update() {
		if (PanelFill.get(currentPanel) != null) {
			PanelFill.get(currentPanel).update();
		}
	}

	public void render(Graphics2D g) {
		if (PanelFill.get(currentPanel) != null) {
			PanelFill.get(currentPanel).render(g);
		}
	}
	public void add(String panelName, GuiPanel panel) {
		PanelFill.put(panelName, panel);
	}

	public void setCurrentPanel(String panelName) {
		currentPanel = panelName;
	}

	public void mousePressed(MouseEvent e) {
		if (PanelFill.get(currentPanel) != null) {
			PanelFill.get(currentPanel).mousePressed(e);
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (PanelFill.get(currentPanel) != null) {
			PanelFill.get(currentPanel).mouseReleased(e);
		}
	}
	
	public void mouseDragged(MouseEvent e) {
		if (PanelFill.get(currentPanel) != null) {
			PanelFill.get(currentPanel).mouseDragged(e);
		}
	}

	public void mouseMoved(MouseEvent e) {
		if (PanelFill.get(currentPanel) != null) {
			PanelFill.get(currentPanel).mouseMoved(e);
		}
	}
	
	public void keyPressed(KeyEvent e) {
		if (PanelFill.get(currentPanel) != null) {
			PanelFill.get(currentPanel).keyPressed(e);
		}
	}
}

