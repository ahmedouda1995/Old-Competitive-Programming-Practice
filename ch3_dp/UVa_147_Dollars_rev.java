package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_147_Dollars_rev {

	static int den[] = {10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000};
	static long dp[] = new long[30001];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		dp[0] = 1;
		solve();
		
		double d;
		
		while((d = sc.nextDouble()) != 0) {
			int n = (int)((d + 0.001) * 100);
			out.printf("%6.2f%17d\n", d, dp[n]);
		}
		
		out.flush();
		out.close();
	}
	
	private static void solve() {
		for(int i = 1; i <= 30000; ++i)
			dp[i] = (i % 5 == 0)?1:0;
		
		for(int i = 0; i < 10; ++i) {
			for(int j = 1; j <= 30000; ++j) {
				if(j >= den[i]) dp[j] += dp[j - den[i]];
			}
		}
	}

	static class Scanner {
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