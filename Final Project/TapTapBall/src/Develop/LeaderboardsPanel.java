package Develop;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;

public class LeaderboardsPanel extends GuiPanel{
	private Font titleFont = Game.mfont.deriveFont(70f);
	private Font ScoreFont = new Font("serif",Font.PLAIN, 36);
	private String title = "Leaderboards";
    private int a = 280;
    public GuiButton backButton;
    public LeaderboardsPanel(){
        backButton = new GuiButton(25, a + 200, 100, 60);
		backButton.setText("Back");
        backButton.addActionListener((ActionEvent e) -> {
			GuiScreen.getInstance().setCurrentPanel("Menu");
		});
		add(backButton);
    }

    @Override
	public void render(Graphics2D g) {
		super.render(g);
		g.setFont(titleFont);
		g.setColor(Color.white);
		g.drawString(title, Game.BWIDTH / 2 - GuiButton.getMessageWidth(title, titleFont, g) / 2, 150);
		g.setFont(ScoreFont);
		g.drawString("Easy Level : "+ScoreManager.easyScore,150 , 250);
		g.drawString("Medium Level : "+ScoreManager.mediumScore,150 , 290);
		g.drawString("Hard Level : "+ScoreManager.hardScore,150 , 330);
	}
}
