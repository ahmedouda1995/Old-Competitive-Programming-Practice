package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_11407_Squares {

	static int dp[] = new int[10000 + 1];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt();
		dp[0] = 0; dp[1] = 1;
		solve(10000);
		while(t-- > 0) {
			out.println(dp[sc.nextInt()]);
		}
		
		out.flush();
		out.close();
	}
	
	private static void solve(int n) {
		for(int i = 2; i <= n; ++i) {
			int min = 1 + dp[i - 1];
			for(int j = 2; j * j <= i; ++j) {
				min = Math.min(min, 1 + dp[i - j * j]);
			}
			dp[i] = min;
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