package geometry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import static java.lang.Math.sin;
import static java.lang.Math.PI;

public class UVA_10432 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		double r, sides;
		double alpha, theta;
		double sideLen;
		
		while(sc.ready())
		{
			r = sc.nextDouble();
			sides = sc.nextDouble();
			
			theta = 360.0 / sides;
			alpha = (180.0 - theta) / 2.0;
			
			sideLen = r * sin(theta * PI / 180.0) / sin(alpha * PI / 180.0);
			out.printf("%.3f\n", sides * triangleArea(r, r, sideLen));
		}
		
		out.flush();
		out.close();
	}
	
	static double triangleArea(double a, double b, double c)
	{
		double s = (a + b + c) / 2;
		return Math.sqrt(s * (s - a) * (s - b) * (s - c));
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
