package ch6_5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_526_StringDistanceandTransformProcess {

	static int N1, N2;
	static char [] s1, s2;
	static int dp[][] = new int[81][81];
	static int counter;
	static PrintWriter out = new PrintWriter(System.out);
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));

		boolean first = true;
		
		while(sc.ready()) {
			if(first) first = false; else out.println();
			s1 = sc.nextLine().toCharArray();
			s2 = sc.nextLine().toCharArray();
			N1 = s1.length; N2 = s2.length;
			
			out.println(solve());
			preProcess(N1, N2);
			counter = 1;
			print(0, 0, 1);
		}
		
		out.flush();
		out.close();
	}
	
	private static void print(int i, int j, int pos) {
		if(i == N1 && j == N2) return;
		if(i < N1 && j < N2 && s1[i] == s2[j] && dp[i + 1][j + 1] == -1)
			print(i + 1, j + 1, pos + 1);
		else if(i < N1 && j < N2 && dp[i + 1][j + 1] == -1) {
			out.printf("%d Replace %d,%c\n", counter++, pos, s2[j]);
			print(i + 1, j + 1, pos + 1);
		}
		else if(i < N1 && dp[i + 1][j] == -1) {
			out.printf("%d Delete %d\n", counter++, pos);
			print(i + 1, j, pos);
		}
		else {
			out.printf("%d Insert %d,%c\n", counter++, pos, s2[j]);
			print(i, j + 1, pos + 1);
		}
	}

	private static void preProcess(int i, int j) {
		if(i == 0 && j == 0) return;
		
		if(i > 0 && j > 0 && s1[i - 1] == s2[j - 1]) preProcess(i - 1, j - 1);
		else if(i > 0 && dp[i][j] == dp[i - 1][j] + 1) preProcess(i - 1, j);
		else if(j > 0 && dp[i][j] == dp[i][j - 1] + 1) preProcess(i, j - 1);
		else preProcess(i - 1, j - 1);
		
		dp[i][j] = -1;
	}

	private static int solve() {
		for(int i = 0; i <= N2; ++i) dp[0][i] = i;
		for(int i = 1; i <= N1; ++i) dp[i][0] = i;
		
		for(int i = 1; i <= N1; ++i) {
			for(int j = 1; j <= N2; ++j) {
				if(s1[i - 1] == s2[j - 1]) dp[i][j] = dp[i - 1][j - 1];
				else dp[i][j] =
						1 + Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
			}
		}
		return dp[N1][N2];
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