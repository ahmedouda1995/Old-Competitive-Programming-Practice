package math;

import java.awt.geom.Area;

public class Geometry {

	static final double EPS = 1e-9;
	
	public static void main(String[] args)
	{
		Point a = new Point(1, 1);
		Point b = new Point(3, 1);
		Point c = new Point(2, 0);
		
		System.out.println(isCollinear(a, b, c));
	}
	
	static boolean isCollinear(Point p0, Point p1, Point p2)
	{
		System.out.println(cProd(new Point(p1.x - p0.x, p1.y - p0.y), new Point(p2.x - p0.x, p2.y - p0.y)));
		return
		  Math.abs(cProd(new Point(p1.x - p0.x, p1.y - p0.y), new Point(p2.x - p0.x, p2.y - p0.y))) <= EPS;
	}
	
	private static double cProd(Point v0, Point v1) {
		return v0.x * v1.y - v0.y * v1.x;
	}

	static double triangleArea1(Point p0, Point p1, Point p2) // using sidelengths
	{
		double a = dist(p0, p1);
		double b = dist(p0, p2);
		double c = dist(p1, p2);
		
		double s = (a + b + c) / 2;
		
		return Math.sqrt((s - a) * (s - b) * (s - c) * s); // Heron's formula
	}
	
	static double triangleArea2(double m1, double m2, double m3) // using medians
	{
		if(m1 <= 0 || m2 <= 0 || m3 <= 0) return -1; // impossible
		
		double s = 0.5 * (m1 + m2 + m3);
		
		double mediansArea = (s * (s - m1) * (s - m2) * (s - m3));
		double area = 4.0 / 3.0 * Math.sqrt(mediansArea);
		if(mediansArea <= 0.0 || area <= 0) return -1; // impossible
		
		return area;
	}
	
	static double dist(Point a, Point b)
	{
		return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
	}
	
	static class Point
	{
		int x, y;
		
		public Point(int a, int b) {
			x = a; y = b;
		}
	}
}
