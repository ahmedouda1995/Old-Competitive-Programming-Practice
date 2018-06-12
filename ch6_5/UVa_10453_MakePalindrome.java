package ch6_5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_10453_MakePalindrome {

	static int N;
	static char [] s;
	static int dp[][] = new int[1001][1001];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		String str;
		
		while(sc.ready()) {
			str = sc.nextLine();
			s = str.toCharArray();
			N = s.length;
			out.print(solve() + " ");
			StringBuilder sb = new StringBuilder(str);
			printSol(0, N - 1, sb);
			out.println(sb.toString());
		}
		
		out.flush();
		out.close();
	}
	
	private static void printSol(int i, int j, StringBuilder sb) {
		if(i >= j || dp[i][j] == 0) return;
		if(s[i] == s[j]) printSol(i + 1, j - 1, sb);
		else {
			if(i < N - 1 && dp[i + 1][j] == dp[i][j] - 1) {
				sb.insert(j + 1, s[i]);
				printSol(i + 1, j, sb);
			}
			else {
				printSol(i, j - 1, sb);
				sb.insert(i, s[j]);
			}
		}
	}

	private static int solve() {
		int j;
		for(int l = 2; l <= N; ++l) {
			for(int i = 0; i < N - l + 1; ++i) {
				j = i + l - 1;
				if(s[i] == s[j]) dp[i][j] = dp[i + 1][j - 1];
				else dp[i][j] = 1 + Math.min(dp[i + 1][j], dp[i][j - 1]);
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