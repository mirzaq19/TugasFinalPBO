package id.ac.its.mirzaq065.Project1;

import javax.swing.JOptionPane;

public class MainApp {

	public static void main(String[] args) {
		String FormAlas = JOptionPane.showInputDialog("Masukkan alas segitiga : ");
		String FormTinggi = JOptionPane.showInputDialog("Masukkan tinggi segitiga : ");
		
		double alas = Double.parseDouble(FormAlas);
		double tinggi = Double.parseDouble(FormTinggi);
		Triangle segitiga = new Triangle(alas,tinggi);
		
		JOptionPane.showMessageDialog(null,"Luas segitiga : "+segitiga.getArea()+"\nKeliling segitiga : "
				+segitiga.getKeliling(),"Hasil Perhitungan",JOptionPane.PLAIN_MESSAGE);
	}

}
