package geometry;

import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVA_10263 {

	static final double EPS = 1e-9;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int m;
		
		while(sc.ready())
		{
			Point p = new Point(sc.nextDouble(), sc.nextDouble());
			m = sc.nextInt();
			
			double min = 1e18;
			
			double resX = -1;
			double resY = -1;
			
			if(m > 0)
			{
				Point a = new Point(sc.nextDouble(), sc.nextDouble());
				while(m-- > 0)
				{
					Point b = new Point(sc.nextDouble(), sc.nextDouble());
					Point c = distToLineSegment(p, a, b);
					
					if(len(p, c) < min)
					{
						min = len(p, c);
						resX = c.x;
						resY = c.y;
					}
					
					a = b;
				}
				
				out.printf("%.4f\n", resX);
				out.printf("%.4f\n", resY);
			}
			
		}
		
		out.flush();
		out.close();
	}
	
	static double len(Point p0, Point p1) {
		return Math.sqrt((p0.x - p1.x) * (p0.x - p1.x) + (p0.y - p1.y) * (p0.y - p1.y));
	}
	
	// returns the distance from p to the line defined by
	// two points a and b (a and b must be different)
	// the closest point is stored in the 4th parameter (byref)
	
	static Point distToLine(Point p, Point a, Point b)
	{
		// formula: c = a + u * ab
		Vect ap = new Vect(a, p);
		Vect ab = new Vect(a, b);
		
		double u = ap.dot(ab) / ab.norm_sq();
		ab.scale(u);							// translate a to c
		Point c = Vect.translate(a, ab);
		return c;						// Euclidean distance between p and c
	}
	
	// returns the distance from p to the line segment ab defined by
	// two points a and b (still OK if a == b)
	// the closest point is stored in the 4th parameter (byref)
	
	static Point distToLineSegment(Point p, Point a, Point b)
	{
		Vect ap = new Vect(a, p);
		Vect ab = new Vect(a, b);
		
		double u = ap.dot(ab) / ab.norm_sq();
		
		Point c;
		
		if (u < 0.0)
		{
			c = new Point(a.x, a.y); // closer to a
			return c;		// Euclidean distance between p and a
		}
		if (u > 1.0) // Euclidean distance between p and b
		{
			c = new Point(b.x, b.y); // closer to b
			return c;
		}
		return distToLine(p, a, b);
	}
	
	// ----------------------  Point class  -------------------------
	
	static class Point implements Comparable<Point>
	{
		double x, y;
		
		public Point(double a, double b) {
			x = a;
			y = b;
		}

		boolean equals(Point p)
		{
			return abs(x - p.x) < EPS && abs(y - p.y) < EPS;
		}
		
		double dist(Point p)
		{
			return sqrt((x - p.x) * (x - p.x) + (y - p.y) * (y - p.y));
		}
		
		// theta counter-clockwise
		
		Point rotate(double theta)
		{
			double rad = theta * PI / 180.0;
			
			return new Point(x * cos(rad) - y * sin(rad),
							 x * sin(rad) + y * cos(rad));
		}
		
		@Override
		public int compareTo(Point p) {
			if(abs(x - p.x) > EPS)
				return Double.compare(x, p.x);
			return Double.compare(y, p.y);
		}
		
		@Override
		public String toString() {
			return "(" + x + ", " + y + ")";
		}
	}
	
	// ------------------------- End of Point class -------------------------
	
	// ------------------------- Vector class -------------------------------
	
	static class Vect
	{
		double x, y;
		
		public Vect(double a, double b) {
			x = a;
			y = b;
		}
		
		public Vect(Point a, Point b) {
			x = b.x - a.x;
			y = b.y - a.y;
		}
		
		public void scale(double s)
		{
			x *= s;
			y *= s;
		}
		
		public static Point translate(Point p,  Vect v)
		{
			return new Point(p.x + v.x, p.y + v.y);
		}
		
		public double dot(Vect v)
		{
			return x * v.x + y * v.y;
		}
		
		public double norm_sq()
		{
			return x * x + y * y;
		}
	}
	
	// ------------------------- End ofVector class --------------------------
		
	static class Scanner{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public Scanner(FileReader r){	br = new BufferedReader(r);}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public double nextDouble() throws IOException { return Double.parseDouble(next()); }

		public boolean ready() throws IOException {return br.ready();}
	}
}
