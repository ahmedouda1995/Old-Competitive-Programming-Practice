package geometry;

import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static java.lang.Math.acos;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class UVA_478 {

	static final double EPS = 1e-9;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		String s;
		String in[];
		
		ArrayList<Circle> circles = new ArrayList<Circle>();
		ArrayList<Square> squares = new ArrayList<Square>();
		ArrayList<Triangle> triangles = new ArrayList<Triangle>();
		
		int shapes = 1;
		
		while(!(s = sc.nextLine()).equals("*"))
		{
			in = s.split(" ");
			
			if(in[0].equals("r"))
			{
				double a, b, c, d;
				a = Double.parseDouble(in[1]);
				b = Double.parseDouble(in[2]);
				c = Double.parseDouble(in[3]);
				d = Double.parseDouble(in[4]);
				squares.add(new Square(a, b, c, d, shapes++));
			}
			else if(in[0].equals("c"))
			{
				double a, b, c;
				a = Double.parseDouble(in[1]);
				b = Double.parseDouble(in[2]);
				c = Double.parseDouble(in[3]);
				circles.add(new Circle(a, b, c, shapes++));
			}
			else
			{
				Point arr[] = new Point[4];
				arr[0] = new Point(Double.parseDouble(in[1]), Double.parseDouble(in[2]));
				arr[1] = new Point(Double.parseDouble(in[3]), Double.parseDouble(in[4]));
				arr[2] = new Point(Double.parseDouble(in[5]), Double.parseDouble(in[6]));
				arr[3] = arr[0];
				Triangle t = new Triangle(shapes++);
				t.P = arr;
				triangles.add(t);
			}
		}
		
		double x, y;
		int points = 1;
		
		while((x = sc.nextDouble()) != 9999.9 | (y = sc.nextDouble()) != 9999.9)
		{
			Point p = new Point(x, y);
			ArrayList<Integer> res = new ArrayList<Integer>();
			
			for(Circle c : circles)
				if(c.inside(p)) res.add(c.idx);
			for(Square c : squares)
				if(c.inside(p)) res.add(c.idx);
			for(Triangle c : triangles)
				if(c.inside(p)) res.add(c.idx);
			
			Collections.sort(res);
			
			for(int i : res)
				out.printf("Point %d is contained in figure %d\n", points, i);
			
			if(res.size() == 0)
				out.printf("Point %d is not contained in any figure\n", points);
			points++;
		}
		
		out.flush();
		out.close();
	}
	
	static class Triangle
	{
		Point [] P;
		int idx;
		
		public Triangle(int a) {
			idx = a;
		}
		
		static boolean ccw(Point p, Point q, Point r)
		{
			return (new Vect(p, q)).cross(new Vect(p, r)) > 0;
		}
		
		static double angle(Point a, Point o, Point b)
		{ // returns angle aob in rad
			Vect oa = new Vect(o, a), ob = new Vect(o, b);
			return acos(fixAngle(oa.dot(ob) / (sqrt(oa.norm_sq()) * sqrt(ob.norm_sq()))));
		}
		
		static double fixAngle(double A)
		{
			return A > 1?1:(A < -1?-1:A);
		}
		
		boolean inside(Point pt)
		{
			double sum = 0.0; // assume the first vertex is equal to the last vertex
			
			for(int i = 0; i < P.length - 1; ++i)
			{
				if (ccw(pt, P[i], P[i+1]))
					sum += angle(P[i], pt, P[i+1]); // left turn/ccw
				else
					sum -= angle(P[i], pt, P[i+1]);
			}
			return abs(abs(sum) - 2*PI) < EPS;
		}
	}
	
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
			
			public double cross(Vect v) { return x * v.y - y * v.x; }
			
			public double norm_sq()
			{
				return x * x + y * y;
			}
		}
		
		// ------------------------- End ofVector class --------------------------
	
	static class Square
	{
		double x0, y0, x1, y1;
		int idx;
		
		public Square(double a, double b, double c, double d, int e) {
			x0 = a; y0 = b; x1 = c; y1 = d;
			idx = e;
		}
		
		boolean inside(Point p)
		{
			return p.x > x0 && p.x < x1 && p.y < y0 && p.y > y1;
		}
	}
	
	static class Circle
	{
		double x, y, r;
		int idx;
		
		public Circle(double a, double b, double c, int d) {
			x = a; y = b; r = c; idx = d;
		}
		
		boolean inside(Point p)
		{
			return (x - p.x) * (x - p.x) + (y - p.y) * (y - p.y) < r * r;
		}
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
