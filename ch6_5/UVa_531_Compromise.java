package ch6_5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_531_Compromise {

	static int N, M;
	static String [] s1, s2;
	static int dp[][] = new int[100][100];
	static char sol[][] = new char[100][100];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		String s;
		StringBuilder sb;
		
		while(sc.ready()) {
			sb = new StringBuilder();
			while(!(s = sc.nextLine()).equals("#"))	sb.append(s).append(" ");
			s1 = sb.toString().split(" ");
			sb = new StringBuilder();
			while(!(s = sc.nextLine()).equals("#"))	sb.append(s).append(" ");
			s2 = sb.toString().split(" ");
			N = s1.length; M = s2.length;
			solve();
			sb = new StringBuilder();
			print(N, M, sb);
			out.println(sb.toString().trim());
		}
		
		out.flush();
		out.close();
	}
	
	private static void solve() {
		
		for(int i = 1; i <= N; ++i) {
			for(int j = 1; j <= M; ++j) {
				if(s1[i - 1].equals(s2[j - 1])) {
					dp[i][j] = 1 + dp[i - 1][j - 1];
					sol[i][j] = 'D';
				}
				else {
					if(dp[i - 1][j] > dp[i][j - 1]) {
						dp[i][j] = dp[i - 1][j];
						sol[i][j] = 'U';
					}
					else {
						dp[i][j] = dp[i][j - 1];
						sol[i][j] = 'L';
					}
				}
			}
		}
	}

	private static void print(int i, int j, StringBuilder sb) {
		if(i == 0 || j == 0) return;
		if(sol[i][j] == 'D') {
			print(i - 1, j - 1, sb);
			sb.append(s1[i - 1] + " ");
		}
		else if(sol[i][j] == 'U') print(i - 1, j, sb);
		else print(i, j - 1, sb);
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