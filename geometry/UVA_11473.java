package geometry;

import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import geometry.GeometricFunctions.Point;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVA_11473 {

	static final double EPS = 1e-9;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int roads = sc.nextInt();
		int cases = 1, K, T;
		
		while(roads-- > 0)
		{
			out.printf("Road #%d:\n", cases++);
			K = sc.nextInt();
			T = sc.nextInt();
			
			Point arr[] = new Point[K];
			
			double dist = 0.0;
			
			arr[0] = new Point(sc.nextDouble(), sc.nextDouble());
			
			for(int i = 1; i < K; ++i)
			{
				arr[i] = new Point(sc.nextDouble(), sc.nextDouble());
				dist += len(arr[i], arr[i - 1]);
			}
			
			dist /= (T - 1);
			
			out.printf("%.2f %.2f\n", arr[0].x, arr[0].y);
			double left;
			Point cur = arr[0];
			int j = 1;
			
			for(int i = 1; i < T - 1; ++i)
			{
				left = dist;
				
				while(left > EPS)
				{
					if(left > len(cur, arr[j]))
					{
						left -= len(cur, arr[j]);
						cur = arr[j];
						
						if(left < EPS)
						{
							out.printf("%.2f %.2f\n", cur.x, cur.y);
							left = 0.0;
						}
						
						j++;
					}
					else
					{
						double dx = left / len(cur, arr[j]) * (arr[j].x - cur.x);
						double dy = left / len(cur, arr[j]) * (arr[j].y - cur.y);
						
						cur = new Point(cur.x + dx, cur.y + dy);
						out.printf("%.2f %.2f\n", cur.x, cur.y);
						left = 0.0;
					}
				}
			}
			out.printf("%.2f %.2f\n", arr[K - 1].x, arr[K - 1].y);
			
			out.println();
		}
		
		out.flush();
		out.close();
	}
	
	static double len(Point p0, Point p1) {
		return Math.sqrt((p0.x - p1.x) * (p0.x - p1.x) + (p0.y - p1.y) * (p0.y - p1.y));
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
