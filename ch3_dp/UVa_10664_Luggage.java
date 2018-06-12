package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_10664_Luggage {

	static int a[];
	static int N;
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int t = sc.nextInt();
		StringTokenizer st;
		
		while(t-- > 0) {
			st = new StringTokenizer(sc.nextLine());
			N = st.countTokens();
			a = new int[N];
			int sum = 0;
			for(int i = 0; i < N; ++i) {
				int next = Integer.parseInt(st.nextToken());
				a[i] = next;
				sum += next;
			}
			
			if(sum % 2 == 1)
				out.println("NO");
			else
				out.println((solve(sum / 2))?"YES":"NO");
		}
		
		out.flush();
		out.close();
	}
	
	private static boolean solve(int n) {
		if(n == 0) return true;
		
		boolean dp[][] = new boolean[N][n + 1];
		
		if(a[0] <= n)
			dp[0][a[0]] = true;
		for(int i = 0; i < N; ++i) dp[i][0] = true;
		
		for(int i = 1; i < N; ++i) {
			for(int j = 0; j <= n; ++j) {
				dp[i][j] = dp[i - 1][j];
				if(a[i] <= j)
					dp[i][j] |= dp[i - 1][j - a[i]];
			}
		}
		
		return dp[N - 1][n];
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