package id.ac.its.putra020.Project2;

import java.awt.FlowLayout;
import javax.swing.JFrame; 
import javax.swing.JLabel; 
import javax.swing.SwingConstants;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class LabelFrame extends JFrame{
	private final JLabel label1;

	public LabelFrame() {
		super("Profil Mahasiswa");
		setLayout(new FlowLayout());
		
		Icon gambar = new ImageIcon(getClass().getResource( "PicPutra.png"));
		label1 = new JLabel();
		label1.setText("<html>Nama : Nur Putra Khanafi<br>NRP : 05111940000020</html>");
		label1.setIcon(gambar); 
		label1.setHorizontalTextPosition(SwingConstants.CENTER);
		label1.setVerticalTextPosition(SwingConstants.BOTTOM);
		label1.setToolTipText("This is image of Nur Putra Khanafi");
		add(label1);
	}
}