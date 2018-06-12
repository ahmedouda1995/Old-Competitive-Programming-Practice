package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_562_Dividingcoins {

	static int [] a = new int[100];
	static int dp[][] = new int[100][25001];
	static int W;
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt();
		
		while(t-- > 0) {
			int n = sc.nextInt();
			int sum = 0;
			for(int i = 0; i < n; ++i) {
				sum += (a[i] = sc.nextInt());
			}
			W = sum / 2;
			
			out.println(sum - solve(n) * 2);
		}
		
		out.flush();
		out.close();
	}
	
	private static int solve(int n) {
		
		if(n == 0)
			return 0;
		
		for(int i = 0; i <= W; ++i) {
			if(a[0] <= i)
				dp[0][i] = a[0];
			else
				dp[0][i] = 0;
		}
		
		for(int i = 1; i < n; ++i) {
			for(int j = 0; j <= W; ++j) {
				if(j >= a[i])
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - a[i]] + a[i]);
				else
					dp[i][j] = dp[i - 1][j];
			}
		}
		
		return dp[n - 1][W];
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