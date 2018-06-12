package geometry;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVA_737 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int n;
		
		while((n = sc.nextInt()) != 0)
		{
			boolean zero = false;
			Cuboid prev, cur;
			prev = new Cuboid(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
			
			for(int i = 1; i < n; ++i)
			{
				cur = new Cuboid(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
				
				if(!zero)
					prev = prev.intersect(cur);
				
				if(prev == null)
					zero = true;
			}
			
			if(zero)
				out.println(0);
			else
				out.println(prev.volume());
		}
		
		out.flush();
		out.close();
	}
	
	static class Cuboid
	{
		
		Point rect[] = new Point[4];
		int z0, z1;
		int sideLen;
		
		public Cuboid() {}
		
		public Cuboid(int x, int y, int z, int len)
		{
			z0 = z;
			z1 = z + len;
			rect[0] = new Point(x, y);
			rect[1] = new Point(x + len, y);
			rect[2] = new Point(x + len, y + len);
			rect[3] = new Point(x, y + len);
			sideLen = len;
		}
		
		Cuboid intersect(Cuboid c)
		{
			Cuboid ret = new Cuboid();
			
			int zLow = max(z0, c.z0);
			int zHigh = min(z1, c.z1);
			
			int xLow = max(rect[0].x, c.rect[0].x);
			int xHigh = min(rect[1].x, c.rect[1].x);
			int yLow = max(rect[0].y, c.rect[0].y);
			int yHigh = min(rect[2].y, c.rect[2].y);
			
			if(xLow >= xHigh || yLow >= yHigh || zLow >= zHigh) return null;
			
			ret.z0 = zLow;
			ret.z1 = zHigh;
			
			ret.rect[0] = new Point(xLow, yLow);
			ret.rect[1] = new Point(xHigh, yLow);
			ret.rect[2] = new Point(xHigh, yHigh);
			ret.rect[3] = new Point(xLow, yHigh);
			
			return ret;
		}
		
		int volume()
		{
			int vol = (rect[1].x - rect[0].x);
			vol *= (rect[3].y - rect[0].y);
			vol *= (z1 - z0);
			return vol;
		}
	}
	
	static class Point
	{
		int x, y;
		
		public Point(int a, int b) {
			x = a;
			y = b;
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
