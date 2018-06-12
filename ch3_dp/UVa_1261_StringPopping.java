package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// all solutions on the internet were recursive bruteforce

public class UVa_1261_StringPopping {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int t = sc.nextInt();
		
		while(t-- > 0) {
			out.println(solve(sc.nextLine().toCharArray()));
		}
		
		out.flush();
		out.close();
	}
	
	private static int solve(char[] arr) {
		int n = arr.length;
		boolean dp[][] = new boolean[n][n];
		
		for(int i = 0; i < n; ++i) dp[i][i] = false;
		if(n >= 2) {
			for(int i = 0; i < n - 1; ++i) {
				if(arr[i] == arr[i + 1]) dp[i][i + 1] = true;
			}
		}
		
		for(int l = 2; l <= n; ++l) {
			for(int i = 0; i < n - l + 1; ++i) {
				int j = i + l - 1;
				
				dp[i][j] |= (dp[i][j - 1] && arr[j - 1] == arr[j]);
				dp[i][j] |= (dp[i + 1][j] && arr[i] == arr[i + 1]);
				if(arr[i] == arr[j] && dp[i + 1][j - 1]) dp[i][j] = true;
				
				if(!dp[i][j]) {
					for(int k = i + 1; k < j - 1; ++k) {
						dp[i][j] |= (dp[i][k] & dp[k + 1][j]);
					}
				}
				if(!dp[i][j]) {
					for(int k = i + 1; k < j - 1; ++k) {
						dp[i][j] |= (dp[i][k] & dp[k][j]);
					}
				}
			}
		}
		
		return (dp[0][n - 1])?1:0;
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