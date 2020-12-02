package id.ac.its.mirzaq065.Project2;

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
		
		Icon gambar = new ImageIcon(getClass().getResource( "mirzaq.png"));
		label1 = new JLabel();
		label1.setText("<html>Nama : M. Auliya Mirzaq Romdloni<br>NRP : 05111940000065</html>");
		label1.setIcon(gambar); 
		label1.setHorizontalTextPosition(SwingConstants.CENTER);
		label1.setVerticalTextPosition(SwingConstants.BOTTOM);
		label1.setToolTipText("This is image of M. Auliya Mirzaq Romdloni");
		add(label1);
	}
}
