package geometry;

import static java.lang.Math.abs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Random;
import java.util.StringTokenizer;

public class QCJ4 {

	static int n;
	static Point pnts[];
	static Point boundary[] = new Point[3];
	static int pntr = 0;
	static final double EPS = 1e-9;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		n = sc.nextInt();
		if(n == 0) { System.out.println(0); return; }
		pnts = new Point[n];
		
		for(int i = 0; i < n; ++i)
			pnts[i] = new Point(sc.nextDouble(), sc.nextDouble());
		
		Circle res = MEC();
		out.printf("%.2f", 2 * res.r);
		
		
		out.flush();
		out.close();
	}
	
	static Circle MEC() {
		shuffle();
		return minidisk(0);
	}
	
	static Circle minidisk(int i) {
		if (i == n || pntr == 3) {
			return baseCase();
		}
		else
		{
			Circle D = minidisk(i+1);
			if (len(pnts[i], new Point(D.x, D.y)) > D.r) {
	            boundary[pntr++] = pnts[i];
	            D = minidisk(i+1);
	            pntr--;
	        }
	        return D;
		}
	}

	static Circle baseCase() {
		if(pntr == 0)
			return new Circle(0, 0, -1);
		else if(pntr == 1)
			return new Circle(boundary[0].x, boundary[0].y, 0.0);
		else if(pntr == 2)
			return new Circle((boundary[0].x + boundary[1].x) / 2.0, (boundary[0].y + boundary[1].y) / 2.0, len(boundary[0], boundary[1]) / 2);
		else
		{
			Point p = findCircle(boundary[0], boundary[1], boundary[2]);
			return new Circle(p.x, p.y, len(p, boundary[0]));
		}
	}
	
	static 	Point areIntersect(Line l1, Line l2)
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
	
	static Point findCircle(Point a, Point b, Point c)
	{
		
		if(areCollinear(a, b, c)) return null;
		Point m1 = new Point((a.x + b.x) / 2.0, (a.y + b.y) / 2.0);
		Point m2 = new Point((b.x + c.x) / 2.0, (b.y + c.y) / 2.0);
		
		Vect v1, v2, pv1, pv2;
		v1 = new Vect(a, b); v2 = new Vect(c, b);
		pv1 = new Vect(v1.y, -v1.x); pv2 = new Vect(v2.y, -v2.x);
		
		Point end1 = Vect.translate(m1, pv1);
		Point end2 = Vect.translate(m2, pv2);
		
		return areIntersect(new Line(m1, end1), new Line(m2, end2));
	}
	
	static boolean areCollinear(Point p, Point q, Point r)
	{
		return abs((new Vect(p, q)).cross(new Vect(p, r))) < EPS;
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
		
		public static Point translate(Point p, Vect v)
		{
			return new Point(p.x + v.x, p.y + v.y);
		}
		
		public double dot(Vect v)
		{
			return x * v.x + y * v.y;
		}
		
		public double cross(Vect v) { return x * v.y - y * v.x; }
		
		public double norm_sq()
		{
			return x * x + y * y;
		}
	}
	
	// ------------------------- End ofVector class --------------------------
	
	static double len(Point p0, Point p1) {
		return Math.sqrt((p0.x - p1.x) * (p0.x - p1.x) + (p0.y - p1.y) * (p0.y - p1.y));
	}

	static void shuffle()
	{
		Random rand = new Random();
		Point tmp;
		int idx;
		
		for(int i = 0; i < n - 1; ++i)
		{
			idx = i + rand.nextInt(n - i);
			
			tmp = pnts[idx];
			pnts[idx] = pnts[i];
			pnts[i] = tmp;
		}
	}

	static class Circle
	{
		double x, y, r;
		
		public Circle(double a, double b, double c) {
			x = a;
			y = b;
			r = c;
		}
	}
	
	static class Point
	{
		double x, y;
		
		public Point(double a, double b) {
			x = a;
			y = b;
		}
	}
	
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
