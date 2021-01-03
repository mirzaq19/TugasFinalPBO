package Develop;

import javax.swing.JFrame;

public class MainApp {
	public static void main(String[] args) {
		JFrame window = new JFrame("Tap Tap Ball !!");
		Game game = new Game();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);
		window.setVisible(true);
		window.add(game);
		window.pack();
		window.setLocationRelativeTo(null);
	}

}
