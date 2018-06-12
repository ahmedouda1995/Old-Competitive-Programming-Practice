package ch6_5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_11151_LongestPalindrome {

	static char s[];
	static int dp[][] = new int[1000][1000];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int t = sc.nextInt();
		
		while(t-- > 0) {
			s = sc.nextLine().toCharArray();
			if(s.length == 0)
				out.println(0);
			else
				out.println(solve());
		}
		
		out.flush();
		out.close();
	}
	
	private static int solve() {
		int n = s.length;
		for(int i = 0; i < n; ++i) dp[i][i] = 1;
		for(int l = 2; l <= n; ++l) {
			for(int i = 0; i < n - l + 1; ++i) {
				int j = i + l - 1;
				if(s[i] == s[j]) dp[i][j] = dp[i + 1][j - 1] + 2;
				else dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
			}
		}
		return dp[0][n - 1];
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