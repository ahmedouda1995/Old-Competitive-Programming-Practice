package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_562_Dividingcoins_rev {

	static int val[];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int t = sc.nextInt(), n;
		
		while(t-- > 0) {
			n = sc.nextInt();
			int sum = 0;
			val = new int[n];
			
			for(int i = 0; i < n; ++i) { sum += (val[i] = sc.nextInt()); }
			
			int max = solve(sum / 2);
			out.println(sum - max - max);
		}
		
		out.flush();
		out.close();
	}
	
	private static int solve(int n) {
		if(n == 0) return 0;
		boolean dp[][] = new boolean[val.length][n + 1];
		int max = 0;
		
		for(int i = 0; i < val.length; ++i) dp[i][0] = true; 
		
		if(val[0] <= n) {
			max = val[0];
			dp[0][val[0]] = true;
		}
		
		for(int i = 1; i < val.length; ++i) {
			for(int j = 0; j <= n; ++j) {
				if(j >= val[i]) {
					dp[i][j] = dp[i - 1][j] | dp[i - 1][j - val[i]];
				}
				else
					dp[i][j] = dp[i - 1][j];
				if(i == val.length - 1 && dp[i][j]) max = Math.max(max, j);
			}
		}
		return max;
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