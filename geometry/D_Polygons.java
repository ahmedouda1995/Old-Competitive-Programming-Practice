package geometry;

import static java.lang.Math.abs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class D_Polygons {

	static final double EPS = 1e-15;
	static Point pivot;
	static final int MAX = (int) 2e5;
	static Point P[] = new Point[MAX];
	static int N;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		//Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int n, m;
		
		n = sc.nextInt();
		
		TreeSet<Pair> poly1 = new TreeSet<Pair>();
		TreeSet<Pair> poly2 = new TreeSet<Pair>();
		
		int k = 0;
		long x, y;
		for(int i = 0; i < n; ++i)
		{
			x = sc.nextLong();
			y = sc.nextLong();
			poly1.add(new Pair(x, y));
			P[k++] = new Point(x, y);
		}
		
		m = sc.nextInt();
		N = n + m;
		
		for(int i = 0; i < m; ++i)
		{
			x = sc.nextLong();
			y = sc.nextLong();
			poly2.add(new Pair(x, y));
			P[k++] = new Point(x, y);
		}
		
		ArrayList<Point> ret = CH();
		
		boolean sec;
		sec = false;
		
		for(Point p : ret)
		{
			System.out.println(p);
			if(poly2.contains(new Pair(p.x, p.y)))
				sec = true;
			
			if(sec) break;
		}
		
		out.println(sec?"NO":"YES");
		
		out.flush();
		out.close();
	}
	
	static class Pair implements Comparable<Pair>
	{
		long x, y;
		
		public Pair(long a, long b) {
			x = a;
			y = b;
		}
		
		@Override
		public int compareTo(Pair p) {
			if(Long.compare(x, p.x) == 0) return Long.compare(y, p.y);
			return Long.compare(x, p.x);
		}
	}
	
	static ArrayList<Point> CH()
	{
		int i, j;
		
		if (N <= 3)
		{
			ArrayList<Point> ret = new ArrayList<Point>();
			for(Point p : P) ret.add(p);
			if (!(P[0] == P[N - 1])) ret.add(P[0]);
			
			return ret;
		}
		// safeguard from corner case, special case, the CH is P itself
		
		// first, find P0 = point with lowest Y and if tie: rightmost X
		int P0 = 0;
		for (i = 1; i < N; ++i)
			if (P[i].y < P[P0].y || (P[i].y == P[P0].y && P[i].x < P[P0].x))
				P0 = i;
		Point temp = P[0]; P[0] = P[P0]; P[P0] = temp; // swap P[P0] with P[0]
		// second, sort points by angle w.r.t. pivot P0
		pivot = P[0]; // use this global variable as reference
		Arrays.sort(P, 1, N); // we do not sort P[0]
		// third, the ccw tests
		for(int s = 0; s < N; ++s) System.out.println(P[s]);
		System.out.println("_____________________________");
		
		ArrayList<Point> S = new ArrayList<Point>();
		
		S.add(P[N - 1]); S.add(P[0]); S.add(P[1]); // initial S
		// then, we check the rest
		i = 2;
		// note: N must be >= 3 for this method to work
		while (i < N)
		{
			j = S.size() - 1;
			if (ccw(S.get(j - 1), S.get(j), P[i])) S.add(P[i++]); // left turn, accept
			else S.remove(S.size() - 1);
		}
		
		return S;
	}
	
	static boolean ccw(Point p, Point q, Point r)
	{
		System.out.println(p + " " + q + " " + r);
		System.out.println(">> " + (new Vect(p, q)).cross(new Vect(p, r)));
		return (new Vect(p, q)).cross(new Vect(p, r)) > -EPS;
	}
	
	static boolean areCollinear(Point p, Point q, Point r)
	{
		return abs((new Vect(p, q)).cross(new Vect(p, r))) < EPS;
	}
	
	static class Vect
	{
		long x, y;
		
		public Vect(int a, int b) {
			x = a;
			y = b;
		}
		
		public Vect(Point a, Point b) {
			x = b.x - a.x;
			y = b.y - a.y;
		}
		
		public double cross(Vect v) { return x * v.y - y * v.x; }
	}
	
	static class Point implements Comparable<Point>
	{
		long x, y;
		
		public Point(long a, long b) {
			x = a;
			y = b;
		}
		
		@Override
		public int compareTo(Point p) { // angle-sorting function
			if (areCollinear(pivot, this, p)) // special case
				return Long.compare(dist(pivot, this), dist(pivot, p));
			
			double d1x = x - pivot.x, d1y = y - pivot.y;
			double d2x = p.x - pivot.x, d2y = p.y - pivot.y;
			
			return Double.compare(Math.atan2(d1y, d1x), Math.atan2(d2y, d2x));
		}
		
		static long dist(Point a, Point b)
		{
			return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
		}
		
		@Override
		public String toString() {
			return "(" + x + ", " + y + ")";
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
