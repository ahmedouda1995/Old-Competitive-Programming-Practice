package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_10130_SuperSale {

	static int weights[] = new int[1000];
	static int val[] = new int[1000];
	static int W;
	static int [][] dp = new int[1000][31];
	//static int [][] memo = new int[1000][31];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt();
		
		while(t-- > 0) {
			
			int n = sc.nextInt();
			for(int i = 0; i < n; ++i) { val[i] = sc.nextInt(); weights[i] = sc.nextInt(); }
			
			int p = sc.nextInt();
			
			int res = 0;
			
			//for(int i = 0; i < n; ++i) Arrays.fill(memo[i], -1);
			W = 30;
			solve_BU(n);
			while(p-- > 0) {
				W = sc.nextInt();
				res += dp[n - 1][W];
				//res += solve_TD(0, W, n);
			}
			out.println(res);
		}
		
		out.flush();
		out.close();
	}
	
//	private static int solve_TD(int i, int w, int n) {
//		if(w <= 0 || i >= n)
//			return 0;
//		if(memo[i][w] != -1)
//			return memo[i][w];
//		if(weights[i] <= w)
//			return memo[i][w] = Math.max(val[i] + solve_TD(i + 1, w - weights[i], n),
//					solve_TD(i + 1, w, n));
//		else
//			return memo[i][w] = solve_TD(i + 1, w, n);
//	}

	private static int solve_BU(int n) {
		for(int i = 0; i <= W; ++i)
			if(i >= weights[0])
				dp[0][i] = val[0];
			else
				dp[0][i] = 0;
		
		for(int i = 1; i < n; ++i) {
			for(int j = 0; j <= W; ++j) {
				if(j == 0)
					dp[i][j] = 0;
				else if(j >= weights[i])
					dp[i][j]= Math.max(dp[i - 1][j], dp[i - 1][j - weights[i]] + val[i]);
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