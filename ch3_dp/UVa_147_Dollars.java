package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_147_Dollars {

	static int [] coin_den = {5, 10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000};
	static long dp[][] = new long[11][30001];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		double d;
		
		solve();
		
		while((d = sc.nextDouble()) != 0) {
			int amount = (int) (d * 100 + 0.001);
			out.printf("%6.2f%17d\n", d, dp[10][amount]);
		}
		
		out.flush();
		out.close();
	}
	
	private static void solve() {
		for(int i = 0; i <= 30000; ++i) {
			if(i >= coin_den[0])
				dp[0][i] = 1;
			else
				dp[0][i] = 0;
		}
		
		for(int i = 1; i < 11; ++i) {
			for(int j = 0; j <= 30000; ++j) {
				if(j == 0)
					dp[i][j] = 1;
				else if(j >= coin_den[i])
					dp[i][j] = dp[i - 1][j] + dp[i][j - coin_den[i]];
				else
					dp[i][j] = dp[i - 1][j];
			}
		}
	}

	static class Pair {
		char c;
		int n;
		
		public Pair(char c, int n) {
			this.c = c;
			this.n = n;
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