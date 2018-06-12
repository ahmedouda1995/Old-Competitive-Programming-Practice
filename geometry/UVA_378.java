package geometry;

import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import geometry.GeometricFunctions.Line;
import geometry.GeometricFunctions.Point;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVA_378 {

	static final double EPS = 1e-9;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int n = sc.nextInt();
		Point a, b, c, d;
		
		out.println("INTERSECTING LINES OUTPUT");
		
		while(n-- > 0)
		{
			a = new Point(sc.nextDouble(), sc.nextDouble());
			b = new Point(sc.nextDouble(), sc.nextDouble());
			c = new Point(sc.nextDouble(), sc.nextDouble());
			d = new Point(sc.nextDouble(), sc.nextDouble());
			
			Line one = new Line(a, b);
			Line two = new Line(c, d);
			
			if(one.areSame(two))
				out.println("LINE");
			else if(one.areParallel(two))
				out.println("NONE");
			else
			{
				Point res = one.areIntersect(one, two);
				out.printf("POINT %.2f %.2f\n", res.x, res.y);
			}
		}
		
		out.println("END OF OUTPUT");
		
		out.flush();
		out.close();
	}
	
	// ---------------------- Line class ----------------------------

	static class Line
	{
		// this linear equation has b = 1 for non vertical lines and b = 0
		// for vertical lines unless otherwise stated
		
		double a, b, c;
		
		public Line(int x, int y, int z) {
			a = x; b = y; c = z;
		}
		
		boolean areParallel(Line l) { // check coefficients a & b
			return (abs(a - l.a) < EPS) && (abs(b - l.b) < EPS); }
		
		boolean areSame(Line l) { // also check coefficient c
			return this.areParallel(l) && (abs(c - l.c) < EPS); }
		
		Point areIntersect(Line l1, Line l2)
		{
			if(l1.areParallel(l2)) return null;
			// solve system of 2 linear algebraic equations with 2 unknowns
			
			Point p = new Point(0.0, 0.0);
			
			p.x = (l2.b * l1.c - l1.b * l2.c) / (l2.a * l1.b - l1.a * l2.b);
			
			// special case: test for vertical line to avoid division by zero
			
			if (abs(l1.b) > EPS) p.y = -(l1.a * p.x + l1.c);
			else p.y = -(l2.a * p.x + l2.c);
			
			return p;
		}
		
		public Line(Point p1, Point p2)
		{
			if(abs(p1.x - p2.x) < EPS)
			{
				a = 1.0; b = 0.0; c = -p1.x; // vertical line
			}
			else
			{
				a = -(p1.y - p2.y) / (p1.x - p2.x);
				b = 1.0;				// IMPORTANT: we fix the value of b to 1.0
				c = -(a * p1.x) - p1.y;
			}
		}
	}
	
	// ---------------------- End of Line class ----------------------------
	
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
