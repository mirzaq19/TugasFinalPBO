package Develop;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;

public class DifficultyPanel extends GuiPanel{
	private Font titleFont = Game.mfont.deriveFont(70f);
	private String title = "Select Difficult";
	private int buttonWidth = 220;
	private int a = 280;
	public static boolean diff = false;
	public GuiButton easyButton;
	public GuiButton mediumButton;
	public GuiButton hardButton;
	public GuiButton backButton;
	
	public DifficultyPanel() {
		super();
		easyButton = new GuiButton(Game.BWIDTH / 2 - buttonWidth / 2, a, buttonWidth, 60);
		mediumButton = new GuiButton(Game.BWIDTH / 2 - buttonWidth / 2, a + 90, buttonWidth, 60);
		hardButton = new GuiButton(Game.BWIDTH / 2 - buttonWidth / 2, a + 180, buttonWidth, 60);
		backButton = new GuiButton(0, a + 200, 100, 60);
		backButton.setText("Back");
		backButton.addActionListener((ActionEvent e) -> {
			GuiScreen.getInstance().setCurrentPanel("Menu");
		});
		add(backButton);
		easyButton.addActionListener((ActionEvent e)->{
			// DifficultLevel.setCurrentLevel("easy");
			// GuiScreen.getInstance().setCurrentPanel("Board");
		});
		easyButton.setText("Easy");
		add(easyButton);
		mediumButton.setText("Medium");
		add(mediumButton);
		hardButton.setText("Hard");
		add(hardButton);
	}
	
	@Override
	public void render(Graphics2D g) {
		super.render(g);
		g.setFont(titleFont);
		g.setColor(Color.white);
		g.drawString(title, Game.BWIDTH / 2 - GuiButton.getMessageWidth(title, titleFont, g) / 2, 150);
	}
}
