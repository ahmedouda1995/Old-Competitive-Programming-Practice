package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_11790_MurciaSkyline {

	static int heights[] = new int[10000];
	static int widths[] = new int[10000];
	static int dp[] = new int[10000];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt(), cases = 1;
		
		while(t-- > 0) {
			int n = sc.nextInt();
			
			for(int i = 0; i < n; ++i) heights[i] = sc.nextInt();
			for(int i = 0; i < n; ++i) widths[i] = sc.nextInt();
			
			int a = lis(n), b = lds(n);
			
			if(a >= b)
				out.printf("Case %d. Increasing (%d). Decreasing (%d).\n", cases++, a, b);
			else
				out.printf("Case %d. Decreasing (%d). Increasing (%d).\n", cases++, b, a);
		}
		
		out.flush();
		out.close();
	}
	
	private static int lis(int n) {
		int max = widths[0];
		dp[0] = widths[0];
		for(int i = 1; i < n; ++i) {
			int maxSoFar = 0;
			for(int j = i - 1; j >= 0; --j) {
				if(heights[i] > heights[j]) {
					if(maxSoFar < dp[j]) maxSoFar = dp[j];
				}
			}
			dp[i] = maxSoFar + widths[i];
			max = Math.max(max, dp[i]);
		}
		return max;
	}

	private static int lds(int n) {
		int max = widths[0];
		dp[0] = widths[0];
		for(int i = 1; i < n; ++i) {
			int maxSoFar = 0;
			for(int j = i - 1; j >= 0; --j) {
				if(heights[i] < heights[j]) {
					if(maxSoFar < dp[j]) maxSoFar = dp[j];
				}
			}
			dp[i] = maxSoFar + widths[i];
			max = Math.max(max, dp[i]);
		}
		return max;
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