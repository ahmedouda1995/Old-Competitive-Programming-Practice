package ch6_5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_164_StringComputer {

	static int N, M;
	static char[] s1, s2;
	static int dp[][] = new int[21][21];
	static PrintWriter out = new PrintWriter(System.out);
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		
		String s;
		
		while(!(s = sc.next()).equals("#")) {
			s1 = s.toCharArray();
			s2 = sc.next().toCharArray();
			N = s1.length; M = s2.length;
			solve();
		}
		
		out.flush();
		out.close();
	}
	
	private static void solve() {
		
		for(int i = 0; i <= M; ++i) dp[0][i] = i;
		for(int i = 1; i <= N; ++i) dp[i][0] = i;
		
		for(int i = 1; i <= N; ++i) {
			for(int j = 1; j <= M; ++j) {
				if(s1[i - 1] == s2[j - 1]) dp[i][j] = dp[i - 1][j - 1];
				else dp[i][j] =
						1 + Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
			}
		}
		preprocess(N, M);
		print(0, 0, 1);
		out.println("E");
	}

	private static void preprocess(int n, int m) {
		if(n == 0 && m == 0) return;
		if(n > 0 && m > 0 && s1[n - 1] == s2[m - 1]) {
			dp[n][m] = -1;
			preprocess(n - 1, m - 1);
		}
		else if(m > 0 && dp[n][m] == dp[n][m - 1] + 1) {
			dp[n][m] = -1;
			preprocess(n, m - 1);
		}
		else if(n > 0 && m > 0 && dp[n][m] == dp[n - 1][m - 1] + 1) {
			dp[n][m] = -1;
			preprocess(n - 1, m - 1);
		}
		else {
			dp[n][m] = -1;
			preprocess(n - 1, m);
		}
	}
	
	private static void print(int n, int m, int pos) {
		if(n == N && m == M) return;
		if(n < N && m < M && s1[n] == s2[m] && dp[n + 1][m + 1] == -1)
			print(n + 1, m + 1, pos + 1);
		else if(m < M && dp[n][m + 1] == -1) {
			out.printf("I%c%02d", s2[m], pos);
			print(n, m + 1, pos + 1);
		}
		else if(n < N && m < M && dp[n + 1][m + 1] == -1) {
			out.printf("C%c%02d", s2[m], pos);
			print(n + 1, m + 1, pos + 1);
		}
		else {
			out.printf("D%c%02d", s1[n], pos);
			print(n + 1, m, pos);
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