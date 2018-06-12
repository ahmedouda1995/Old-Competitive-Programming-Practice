package ch6_5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_10066_TheTwinTowers {

	static int [] arr1 = new int[101];
	static int [] arr2 = new int[101];
	static int N1, N2;
	static int dp[][] = new int[101][101];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int cases = 1;
		
		while((N1 = sc.nextInt()) != 0 | (N2 = sc.nextInt()) != 0) {
			for(int i = 1; i <= N1; ++i) arr1[i] = sc.nextInt();
			for(int i = 1; i <= N2; ++i) arr2[i] = sc.nextInt();
			out.printf("Twin Towers #%d\n", cases++);
			out.printf("Number of Tiles : %d\n", solve());
			out.println();
		}
		
		out.flush();
		out.close();
	}
	
	private static int solve() {
		for(int i = 1; i <= N1; ++i) {
			for(int j = 1; j <= N2; ++j) {
				if(arr1[i] == arr2[j]) dp[i][j] = dp[i - 1][j - 1] + 1;
				else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
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