package ch6_5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Uva_1207_AGTC {

	static int N, M;
	static char [] s1, s2;
	static int dp[][];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		while(sc.ready()) {
			N = sc.nextInt(); s1 = sc.next().toCharArray();
			M = sc.nextInt(); s2 = sc.next().toCharArray();
			
			dp = new int[N + 1][M + 1];
			out.println(solve());
		}
		
		out.flush();
		out.close();
	}
	
	private static int solve() {
		for(int i = 0; i <= M; ++i) dp[0][i] = i;
		for(int i = 1; i <= N; ++i) dp[i][0] = i;
		
		for(int i = 1; i <= N; ++i) {
			for(int j = 1; j <= M; ++j) {
				if(s1[i - 1] == s2[j - 1]) dp[i][j] = dp[i - 1][j - 1];
				else dp[i][j] =
						1 + Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
			}
		}
		return dp[N][M];
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