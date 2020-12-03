package id.ac.its.putra020.Project1;

import javax.swing.JOptionPane;

public class MainApp {

	public static void main(String[] args) {
		String FormRadius = JOptionPane.showInputDialog("Masukkan radius lingkaran : ");

		double radius = Double.parseDouble(FormRadius);

		Circle lingkaran1 = new Circle(radius);

		JOptionPane.showMessageDialog(null,
				"Luas lingkaran : " + lingkaran1.getArea() + "\nKeliling lingkaran : " + lingkaran1.getKeliling(),
				"Hasil Perhitungan", JOptionPane.PLAIN_MESSAGE);
	}

}