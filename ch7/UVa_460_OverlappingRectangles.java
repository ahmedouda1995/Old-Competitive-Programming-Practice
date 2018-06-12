package ch7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class UVa_460_OverlappingRectangles {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int t = sc.nextInt();
		int xl1, yl1, xu1, yu1;
		int xl2, yl2, xu2, yu2;
		
		while(t-- > 0) {
			xl1 = sc.nextInt(); yl1 = sc.nextInt();
			xu1 = sc.nextInt(); yu1 = sc.nextInt();
			
			xl2 = sc.nextInt(); yl2 = sc.nextInt();
			xu2 = sc.nextInt(); yu2 = sc.nextInt();
			
			int a = max(xl1, xl2), c = min(xu1, xu2);
			int b = max(yl1, yl2), d = min(yu1, yu2);
			
			if(a < c && b < d)
				out.printf("%d %d %d %d\n", a, b, c, d);
			else
				out.println("No Overlap");
			if(t > 0) out.println();
		}
		
		out.flush();
		out.close();
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