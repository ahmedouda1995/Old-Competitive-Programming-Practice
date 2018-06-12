package geometry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVA_476 {
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		String in[];
		
		ArrayList<Rect> arr = new ArrayList<Rect>();
		
		String s;
		
		while(!(s = sc.nextLine()).equals("*"))
		{
			in = s.split(" ");
			arr.add(new Rect(Double.parseDouble(in[1]),
					Double.parseDouble(in[2]),
					Double.parseDouble(in[3]),
					Double.parseDouble(in[4])));
		}
		
		double x, y;
		boolean found;
		int p = 1;
		
		while((x = sc.nextDouble()) != 9999.9 | (y = sc.nextDouble()) != 9999.9)
		{
			found = false;
			for(int i = 0; i < arr.size(); ++i)
			{
				if(arr.get(i).inside(x, y))
				{
					found = true;
					out.printf("Point %d is contained in figure %d\n", p, (i + 1));
				}
			}
			
			if(!found)
				out.printf("Point %d is not contained in any figure\n", p);
			p++;
		}
		
		out.flush();
		out.close();
	}
	
	static class Rect
	{
		double x1, y1, x2, y2;
		
		public Rect(double a, double b, double c, double d) {
			x1 = a;
			y1 = b;
			x2 = c;
			y2 = d;
		}

		public boolean inside(double x, double y) {
			return x > x1 && x < x2 && y < y1 && y > y2;
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