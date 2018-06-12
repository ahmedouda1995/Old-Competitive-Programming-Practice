package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_674CoinChange {

	static int coin_den[] = {1, 5, 10, 25, 50};
	static int dp[][] = new int[5][7490];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		String s;
		int n;
		
		solve(7489);
		//for(int i = 0; i < 5; ++i) System.out.println(Arrays.toString(dp[i]));
		while((s = sc.nextLine()) != null) {
			out.println(dp[4][Integer.parseInt(s)]);
		}
		
		out.flush();
		out.close();
	}
	
	private static void solve(int amount) {
		for(int i = 0; i < 5; ++i) {
			for(int j = 0; j <= amount; ++j) {
				if(i == 0 || j == 0)
					dp[i][j] = 1;
				else {
					if(j >= coin_den[i])
						dp[i][j] = dp[i - 1][j] + dp[i][j - coin_den[i]];
					else
						dp[i][j] = dp[i - 1][j];
				}
			}
		}
	}

	static class Pair {
		char c;
		int n;
		
		public Pair(char c, int n) {
			this.c = c;
			this.n = n;
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