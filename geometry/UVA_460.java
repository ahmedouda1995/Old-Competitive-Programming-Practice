package geometry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVA_460 {
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt();
		
		int [] a, b;
		a = new int[4];
		b = new int[4];
		int x1, y1, x2, y2;
		
		while(t-- > 0)
		{
			for(int i = 0; i < 4; ++i) a[i] = sc.nextInt();
			for(int i = 0; i < 4; ++i) b[i] = sc.nextInt();
			
			x1 = Math.max(a[0], b[0]);
			x2 = Math.min(a[2], b[2]);
			y1 = Math.max(a[1], b[1]);
			y2 = Math.min(a[3], b[3]);
			
			if(x1 < x2 && y1 < y2)
				out.println(x1 + " " + y1 + " " + x2 + " " + y2);
			else
				out.println("No Overlap");
			
			if(t > 0)
				out.println();
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