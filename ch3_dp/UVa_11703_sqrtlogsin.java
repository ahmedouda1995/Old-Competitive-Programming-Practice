package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_11703_sqrtlogsin {

	static long x[] = new long[1000001];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		x[0] = 1;
		int n;
		
		while((n = sc.nextInt()) != -1) {
			long res = solve(n);
			out.println(res);
		}
		
		out.flush();
		out.close();
	}
	
	private static long solve(int n) {
		if(x[n] != 0) return x[n];
		
		return x[n] = (solve((int) (n - Math.sqrt(n * 1.0))) +
					  solve((int) Math.log(n * 1.0)) +
					  solve((int) (n * Math.sin(n) * Math.sin(n)))) % 1000000;
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