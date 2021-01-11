package Develop;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;

public class CreditPanel extends GuiPanel {
    private Font titleFont = Game.mfont.deriveFont(70f);
    private Font creditFont = new Font("serif",Font.PLAIN, 18);
    private String title = "Credit";
    private int a = 280;
    public GuiButton backButton;
    private String[] credit = {"Game TapTapBall merupakan game yang bertujuan untuk menghancurkan ","objek batu bata. Pemain akan bertugas menjaga bola yang digunakan untuk ","menghancurkan batu bata agar tidak jatuh dengan memantulkan bola pada ","paddle yang berada dibawah. Terdapat beberapa variasi brick/batu bata pada","setiap levelnya. Dan juga terdapat beberapa brick spesial yang akan memberi ", "efek pada bola ataupun paddle, seperti merubah kecepatan bola ataupun ","merubah panjang dari paddle untuk menambah tingkat kesulitan"," ","Pembuat : ","1. M. Auliya Mirzaq Romdloni","2. Husin Muhammad Assegaf","3. Nur Putra Khanafi"};
    
    public CreditPanel(){
        super();
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
        g.drawString(title, Game.BWIDTH / 2 - GuiButton.getMessageWidth(title, titleFont, g) / 2, 100);
        g.setFont(creditFont);
        int i=0;
        for(String c:credit){
            g.drawString(c, Game.BWIDTH / 10, 180+i*25);
            i++;
        }

	}
}
