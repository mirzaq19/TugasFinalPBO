package Develop;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;

public class MainMenuPanel extends GuiPanel {
	private Font titleFont = Game.mfont.deriveFont(100f);
	private String title = "TapTapBall";
	private int buttonWidth = 220;
	private int a = 240;
	public GuiButton resumeButton;
	public GuiButton playButton;
	public GuiButton creditButton;
	public GuiButton quitButton;

	public MainMenuPanel() {
		super();
		resumeButton = new GuiButton(Game.BWIDTH / 2 - buttonWidth / 2, a, buttonWidth, 60);
		playButton = new GuiButton(Game.BWIDTH / 2 - buttonWidth / 2, a + 90, buttonWidth, 60);
		creditButton = new GuiButton(Game.BWIDTH / 2 - buttonWidth / 2, a + 180, buttonWidth, 60);
		quitButton = new GuiButton(Game.BWIDTH / 2 - buttonWidth / 2, a + 270, buttonWidth, 60);
		resumeButton.setText("Resume");
		add(resumeButton);
		playButton.addActionListener((ActionEvent e) -> {
			GuiScreen.getInstance().setCurrentPanel("Difficulty");
		});
		playButton.setText("Play");
		add(playButton);
		creditButton.addActionListener((ActionEvent e)->{
			GuiScreen.getInstance().setCurrentPanel("Credit");
		});
		creditButton.setText("Credit");
		add(creditButton);
		quitButton.addActionListener((ActionEvent e) -> {
			System.exit(0);
		});
		quitButton.setText("Quit");
		add(quitButton);
	}

	@Override
	public void render(Graphics2D g) {
		super.render(g);
		g.setFont(titleFont);
		g.setColor(Color.white);
		g.drawString(title, Game.BWIDTH / 2 - GuiButton.getMessageWidth(title, titleFont, g) / 2, 150);
	}
}

