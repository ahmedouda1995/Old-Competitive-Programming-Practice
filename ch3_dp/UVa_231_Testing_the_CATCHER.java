package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_231_Testing_the_CATCHER {

	static int dp[] = new int[100000];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int n, tests = 1, a[] = new int[100000];
		boolean first = true;
		while((n = sc.nextInt()) != -1) {
			if(first) first = false; else out.println();
			int i = 1, tmp;
			a[0] = n;
			while((tmp = sc.nextInt()) != -1) a[i++] = tmp;
			
			n = i;
			
			out.printf("Test #%d:\n", tests++);
			out.printf("  maximum possible interceptions: %d\n", lis(a, n));
		}
		
		out.flush();
		out.close();
	}
	
	private static int lis(int[] a, int n) {
		int max = 1;
		dp[0] = 1;
		for(int i = 1; i < n; ++i) {
			int maxSoFar = 0;
			for(int j = i - 1; j >= 0; --j) {
				if(a[j] >= a[i])
					maxSoFar = Math.max(maxSoFar, dp[j]);
			}
			dp[i] = maxSoFar + 1;
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