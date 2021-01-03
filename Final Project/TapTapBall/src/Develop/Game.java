package Develop;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;
	public static final int BWIDTH = 700;
	public static final int BHEIGHT = 600;
	private Image BoardBackground;
	private int DELAY = 8;
	private Timer timer;
	public static final Font mfont = new Font("serif", Font.BOLD, 35);
	private GuiScreen screen;

	public Game() {
		setFocusable(true);
		setPreferredSize(new Dimension(BWIDTH, BHEIGHT));
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		setBackground(Color.black);
		screen = GuiScreen.getInstance();
		screen.add("Menu", new MainMenuPanel());
        screen.add("Difficulty", new DifficultyPanel());
        screen.add("Credit", new CreditPanel());
		screen.setCurrentPanel("Menu");
		timer = new Timer(DELAY, this);
		timer.start();
	}

	public void paint(Graphics g) {

		drawBackground((Graphics2D) g);
		screen.render((Graphics2D) g);
		g.dispose();

	}

	public void drawBackground(Graphics2D g) {
		ImageIcon bg = new ImageIcon("src/resources/GameBoard.png");
		BoardBackground = bg.getImage();
		g.drawImage(BoardBackground, 0, 0, this);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Ok");
		screen.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		screen.update();
		repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		screen.mouseDragged(e);

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		screen.mouseMoved(e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		screen.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		screen.mouseReleased(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
