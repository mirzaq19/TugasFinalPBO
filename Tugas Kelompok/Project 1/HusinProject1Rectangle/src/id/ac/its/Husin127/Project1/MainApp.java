package id.ac.its.Husin127.Project1;

import javax.swing.JOptionPane;

public class MainApp {

	public static void main(String[] args) {
		String Formlength = JOptionPane.showInputDialog("Enter the length of rectangle : ");
		String Formwidth = JOptionPane.showInputDialog("enter the width of rectangle : ");
		
		double length = Double.parseDouble(Formlength);
		double width = Double.parseDouble(Formwidth);
		Rectangle rectangle = new Rectangle(length,width);
		
		JOptionPane.showMessageDialog(null,"The area of rectangle is : "+rectangle.getArea()+"\nThe circumference of rectangle is : "
				+rectangle.getKeliling(),"Rectangle",JOptionPane.PLAIN_MESSAGE);
	}

}
