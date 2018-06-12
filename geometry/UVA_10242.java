package geometry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVA_10242 {

	static final double EPS = 1e-9;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		Point a, b, c, d;
		
		while(sc.ready())
		{
			try
			{
				a = new Point(sc.nextDouble(), sc.nextDouble());
				b = new Point(sc.nextDouble(), sc.nextDouble());
				c = new Point(sc.nextDouble(), sc.nextDouble());
				d = new Point(sc.nextDouble(), sc.nextDouble());
			}
			catch(Exception e)
			{
				break;
			}
			
			if(c.equals(a))
			{
				Point e = toVect(a, b);
				out.printf("%.3f %.3f\n", (d.a + e.a), (d.b + e.b));
			}
			else if(d.equals(a))
			{
				Point e = toVect(a, b);
				out.printf("%.3f %.3f\n", (c.a + e.a), (c.b + e.b));
			}
			else if(c.equals(b))
			{
				Point e = toVect(b, a);
				out.printf("%.3f %.3f\n", (d.a + e.a), (d.b + e.b));
			}
			else
			{
				Point e = toVect(b, a);
				out.printf("%.3f %.3f\n", (c.a + e.a), (c.b + e.b));
			}
		}
		
		out.flush();
		out.close();
	}
	
	static Point toVect(Point p0, Point p1)
	{
		return new Point(p1.a - p0.a, p1.b - p0.b);
	}
	
	static class Point
	{
		double a, b;
		
		public Point(double x, double y) {
			a = x;
			b = y;
		}
		
		public boolean equals(Point p)
		{
			return Math.abs(a - p.a) < EPS && Math.abs(b - p.b) < EPS;
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
