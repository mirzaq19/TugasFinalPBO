package id.ac.its.putra020.Project1;

public class Circle {
	private double radius;
	final double pi = 3.1416;
	
	public Circle() {
		this.radius = 0;
	}
	public Circle (double radius) {
		this.radius = radius;
	}
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	public double getArea() {
		return pi*radius*radius;
	}
	public double getKeliling() {
		return 2*pi*radius;
	}
}