package geometry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class UVA_477 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		ArrayList<Square> squares = new ArrayList<Square>();
		ArrayList<Circle> circles = new ArrayList<Circle>();
		
		String s;
		String in[];
		int i = 1;
		
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
				
				squares.add(new Square(a, b, c, d, i++));
			}
			else
			{
				double a, b, r;
				a = Double.parseDouble(in[1]);
				b = Double.parseDouble(in[2]);
				r = Double.parseDouble(in[3]);
				
				circles.add(new Circle(a, b, r, i++));
			}
		}
		
		double x, y;
		i = 1;
		ArrayList<Integer> res;
		
		while((x = sc.nextDouble()) != 9999.9 | (y = sc.nextDouble()) != 9999.9)
		{
			res = new ArrayList<Integer>();
			
			for(Square sq : squares)
				if(sq.inside(x, y))
					res.add(sq.idx);
			for(Circle c : circles)
				if(c.inside(x, y))
					res.add(c.idx);
			
			Collections.sort(res);
			
			if(res.size() == 0)
				out.printf("Point %d is not contained in any figure\n", i++);
			else
			{
				for(int e : res)
					out.printf("Point %d is contained in figure %d\n", i, e);
				i++;
			}
		}
		
		out.flush();
		out.close();
	}
	
	static class Square
	{
		double xLow, yHigh, xHigh, yLow;
		int idx;
		
		public Square(double a, double b, double c, double d, int e) {
			xLow = a;
			yHigh = b;
			xHigh = c;
			yLow = d;
			idx = e;
		}
		
		boolean inside(double a, double b)
		{
			return a > xLow && a < xHigh && b > yLow && b < yHigh;
		}
	}
	
	static class Circle
	{
		double x, y, r;
		int idx;
		
		public Circle(double a, double b, double c, int d) {
			x = a; y = b; r = c; idx = d;
		}
		
		boolean inside(double a, double b)
		{
			return (x - a) * (x - a) + (y - b) * (y - b) < r * r;
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
