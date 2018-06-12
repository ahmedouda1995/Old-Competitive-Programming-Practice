package geometry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVA_10347 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		double m1, m2, m3;
		
		while(sc.ready())
		{
			m1 = sc.nextDouble();
			m2 = sc.nextDouble();
			m3 = sc.nextDouble();
			
			out.printf("%.3f\n", triangleArea(m1, m2, m3));
		}
		
		out.flush();
		out.close();
	}
	
	static double triangleArea(double m1, double m2, double m3)
	{
		// Area of triangle using medians as sides = 3/4 * (area of original triangle)
		if(m1 <= 0 || m2 <= 0 || m3 <= 0) return -1; // impossible
		double s = 0.5 * (m1 + m2 + m3);
		double mediansArea = (s * (s - m1) * (s - m2) * (s - m3));
		double area = 4.0 / 3.0 * Math.sqrt(mediansArea);
		if(mediansArea <= 0 || area <= 0) return -1; // impossible
		return area;
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
