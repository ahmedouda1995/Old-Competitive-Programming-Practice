package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_11517_ExactChange {

	static int N, INF = (int) 1e9;
	static int den[] = new int[100];
	static int dp[][];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int t = sc.nextInt(), total;
		
		while(t-- > 0) {
			total = sc.nextInt();
			N = sc.nextInt();
			for(int i = 0; i < N; ++i) den[i] = sc.nextInt();
			Arrays.sort(den, 0, N);
			int sum = 0;
			int i = 0;
			while(sum < total)
				sum += den[i++];
			
			if(total == 0)
				out.println("0 0");
			else {
				int index = solve(sum, total);
				out.println(index + " " + dp[N - 1][index]);
			}
		}
		
		out.flush();
		out.close();
	}
	
	private static int solve(int sum, int total) {
		dp = new int[N][sum + 1];
		
		Arrays.fill(dp[0], INF); dp[0][den[0]] = 1;
		
		for(int i = 0; i < N; ++i) dp[i][0] = 0;
		
		for(int i = 1; i < N; ++i) {
			for(int j = 1; j <= sum; ++j) {
				if(j >= den[i])
					dp[i][j] = Math.min(dp[i - 1][j], 1 + dp[i - 1][j - den[i]]);
				else
					dp[i][j] = dp[i - 1][j];
			}
		}
		
		for(int i = total; i <= sum; ++i) {
			if(dp[N - 1][i] < INF) return i;
		}
		return -1;
	}

//	private static int solve(int sum, int total, int n) {
//		dp = new int[sum + 1];
//		
//		Arrays.fill(dp, INF); dp[0] = 0;
//		
//		for(int i = 0; i < n; ++i) {
//			for(int j = den[i]; j <= sum; ++j) {
//				dp[j] = Math.min(dp[j], 1 + dp[j - den[i]]);
//			}
//		}
//		
//		for(int i = total; i <= sum; ++i) {
//			if(dp[i] < INF) return i;
//		}
//		return -1;
//	}
	
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