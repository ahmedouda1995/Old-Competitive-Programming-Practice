package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_10313_PaythePrice {

	// accepted although problem misunderstanding
	
	//static long dp[][] = new long[301][301];
	static long memo[][][] = new long[301][301][301];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		StringTokenizer st;
		for(int i = 0; i <= 300; ++i)
			for(int j = 0; j <= 300; ++j)
				Arrays.fill(memo[i][j], -1);
		
		while(sc.ready()) {
			st = new StringTokenizer(sc.nextLine());
			int n, l, r;
			if(st.countTokens() == 1) {
				n = Integer.parseInt(st.nextToken());
				long res = 0;
				for(int i = 0; i <= 300; ++i) res += solve(n, 1, i);
				out.println(res);
			}
			else if(st.countTokens() == 2) {
				n = Integer.parseInt(st.nextToken());
				r = Integer.parseInt(st.nextToken());
				if(r > 300) r = 300;
				long res = 0;
				for(int i = 0; i <= r; ++i)
					res += solve(n, 1, i);
				out.println(res);
			}
			else {
				n = Integer.parseInt(st.nextToken());
				l = Integer.parseInt(st.nextToken());
				if(l > 300) l = 300;
				r = Integer.parseInt(st.nextToken());
				if(r > 300) r = 300;
				long res = 0;
				for(; l <= r; ++l) res += solve(n, 1, l);
				out.println(res);
			}
		}
		
		out.flush();
		out.close();
	}
	
	private static long solve(int money, int den, int coins) {
		if(money == 0 && coins == 0) return 1;
		if(money < 0 || coins == 0 || den > 300) return 0;
		if(memo[money][den][coins] != -1) return memo[money][den][coins];
		
		return memo[money][den][coins] =
				solve(money, den + 1, coins) + solve(money - den, den, coins - 1);
	}
	
//	private static void solve() {
//		Arrays.fill(dp[1], 1);
//		for(int i = 0; i <= 300; ++i) dp[i][0] = 1;
//		
//		for(int i = 2; i <= 300; ++i) {
//			for(int j = 1; j <= 300; ++j) {
//				if(j >= i)
//					dp[i][j] = dp[i - 1][j] + dp[i][j - i];
//				else
//					dp[i][j] = dp[i - 1][j];
//			}
//		}
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