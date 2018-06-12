package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_10304_OptimalBinarySearchTree {

	static final int INF = (int) 1e9;
	static int N;
	static int freq[] = new int[250];
	static int dp[][] = new int[250][250];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		while(sc.ready()) {
			N = sc.nextInt();
			freq[0] = sc.nextInt();
			for(int i = 1; i < N; ++i) {
				freq[i] = freq[i - 1] + sc.nextInt();
			}
			out.println(solve());
		}
		
		out.flush();
		out.close();
	}
	
	private static int solve() {
		for(int i = 0; i < N; ++i) dp[i][i] = 0;
		
		for(int l = 2; l <= N; ++l) {
			for(int i = 0; i < N - l + 1; ++i) {
				int j = i + l - 1;
				int min = INF;
				
				int left, right, sum;
				
				for(int k = i; k <= j; ++k) {
					left = 0; right = 0;
					if(k > i) {
						sum = freq[k - 1];
						sum -= (i == 0)?0:freq[i - 1];
						left = dp[i][k - 1] + sum;
					}
					if(k < j) {
						sum = freq[j];
						sum -= freq[k];
						right = dp[k + 1][j] + sum;
					}
					min = Math.min(min, left + right);
				}
				dp[i][j] = min;
			}
		}
		
		return dp[0][N - 1];
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