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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVA_273 {

	static int n;
	static final double EPS = 1e-9;
	static Line lines[];
	static ArrayList<Integer> adj[];
	static boolean vis[];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt();
		Point a, b, c;
		
		while(t-- > 0)
		{
			n = sc.nextInt();
			
			lines = new Line[n];
			for(int i = 0; i < n; ++i)
			{
				a =  new Point(sc.nextDouble(), sc.nextDouble());
				b =  new Point(sc.nextDouble(), sc.nextDouble());
				
				lines[i] = new Line(a, b);
			}
			
			adj = new ArrayList[n];
			for(int i = 0; i < n; ++i) adj[i] = new ArrayList<Integer>();
			vis = new boolean[n];
			
			for(int i = 0; i < n; ++i)
				for(int j = i + 1; j < n; ++j)
				{
					boolean bool = lines[i].areSame(lines[j]);
					double mx = Math.max(lines[j].p0.x, lines[j].p1.x);
					double mn = Math.min(lines[j].p0.x, lines[j].p1.x);
					bool &= (lines[i].p0.x >= mn && lines[i].p0.x <= mx
							|| lines[i].p1.x >= mn && lines[i].p1.x <= mx);
					c = areIntersect(lines[i], lines[j]);
					if(bool || c != null 
					&& isPointOnSegment2(lines[i].p0, lines[i].p1, c)
					&& isPointOnSegment2(lines[j].p0, lines[j].p1, c))
					{
						adj[i].add(j);
						adj[j].add(i);
					}
				}
			
			int x, y;
			
			while((x = sc.nextInt()) != 0 | (y = sc.nextInt()) != 0)
			{
				x--; y--;
				Arrays.fill(vis, false);
				
				if(dfs(x, y)) out.println("CONNECTED");
				else out.println("NOT CONNECTED");
			}
			
			if(t > 0)
				out.println();
		}
		
		out.flush();
		out.close();
	}
	
	private static boolean dfs(int u, int dest) {
		if(u == dest) return true;
		vis[u] = true;
		
		boolean res = false;
		
		for(int v : adj[u])
		{
			if(!vis[v])
				res |= dfs(v, dest);
		}
		return res;
	}

	static double len(Point p0, Point p1) {
		return Math.sqrt((p0.x - p1.x) * (p0.x - p1.x) + (p0.y - p1.y) * (p0.y - p1.y));
	}
	
	static boolean isPointOnSegment2(Point a, Point b, Point p)
	{
		return abs(len(a, b) - len(a, p) - len(b, p)) < EPS;
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
	
	// ---------------------- Line class ----------------------------

		static class Line
		{
			// this linear equation has b = 1 for non vertical lines and b = 0
			// for vertical lines unless otherwise stated
			
			double a, b, c;
			Point p0, p1;
			
			public Line(int x, int y, int z) {
				a = x; b = y; c = z;
			}
			
			boolean areParallel(Line l) { // check coefficients a & b
				return (abs(a - l.a) < EPS) && (abs(b - l.b) < EPS); }
			
			boolean areSame(Line l) { // also check coefficient c
				return this.areParallel(l) && (abs(c - l.c) < EPS); }
			
			public Line(Point p1, Point p2)
			{
				this.p0 = p1;
				this.p1 = p2;
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
