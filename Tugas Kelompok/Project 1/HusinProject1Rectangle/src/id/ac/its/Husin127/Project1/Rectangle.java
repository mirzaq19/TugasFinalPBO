package id.ac.its.Husin127.Project1;

public class Rectangle {
	private double length;
	private double width;
	
	public Rectangle() {
		this.length = 0;
		this.width = 0;
	}
	public Rectangle(double length, double width) {
		this.length = length;
		this.width = width;
	}

	public double getlength() {
		return length;
	}
	public void setlength(double length) {
		this.length = length;
	}
	public double getwidth() {
		return width;
	}
	public void setwidth(double width) {
		this.width = width;
	}

	public double getArea() {
		return length*width;
	}
	
	public double getKeliling() {
		return 2*(length + width);
	}
}
