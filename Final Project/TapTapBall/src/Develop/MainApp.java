package Develop;

import javax.swing.JFrame;

public class MainApp {
	public static void main(String[] args) {
		JFrame obj=new JFrame();
		Board board = new Board();

		obj.setBounds(50, 50, 700, 600);
		obj.setTitle("Tap Tap Ball!");		
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(board);
	}

}
