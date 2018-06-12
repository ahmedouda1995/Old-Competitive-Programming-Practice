package geometry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class UVA_833 {

	static int np;
	static ArrayList<Point> high = new ArrayList<Point>();
	static ArrayList<Point> low = new ArrayList<Point>();
	static TreeMap<Point, Integer> memo = new TreeMap<Point, Integer>();
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt(), ns;
		
		while(t-- > 0)
		{
			memo.clear();
			high.clear();
			low.clear();
			np = sc.nextInt();
			
			for(int i = 0; i < np; ++i)
			{
				Point a = new Point(sc.nextInt(), sc.nextInt());
				Point b = new Point(sc.nextInt(), sc.nextInt());
				
				if(a.x != b.x)
				{
					if(a.y > b.y)
					{
						high.add(a);
						low.add(b);
					}
					else
					{
						high.add(b);
						low.add(a);
					}
				}
			}
			
			ns = sc.nextInt();
			
			while(ns-- > 0)
			{
				Point p = new Point(sc.nextInt(), sc.nextInt());
				
				out.println(solve(p));
			}
			
			if(t > 0)
				out.println();
			
		}
		
		out.flush();
		out.close();
	}
	
	private static int solve(Point p) {
		if(memo.containsKey(p)) return memo.get(p);
		if(p.y == 0)
		{
			memo.put(p, p.x);
			return p.x;
		}
		
		double min = p.y;
		Point ans = new Point(p.x, 0);
		
		for(int i = 0; i < high.size(); ++i)
		{
			if(p.x > low.get(i).x && p.x < high.get(i).x
					|| p.x > high.get(i).x && p.x < low.get(i).x)
			{
				double m = 1.0 * (high.get(i).y - low.get(i).y) / (high.get(i).x - low.get(i).x);
				double y = m * (p.x - low.get(i).x) + (low.get(i).y);
				
				if(y < p.y)
				{
					if(p.y - y < min)
					{
						min = p.y - y;
						ans = low.get(i);
					}
				}
			}
		}
		
		int ret = solve(ans);
		memo.put(ans, ret);
		return ret;
	}

	static class Point implements Comparable<Point>
	{
		int x, y;
		
		public Point(int a, int b) {
			x = a;
			y = b;
		}
		
		@Override
		public int compareTo(Point p) {
			if(x == p.x)
				return y - p.y;
			return x - p.x;
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
