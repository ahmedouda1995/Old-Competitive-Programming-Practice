package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_10003_CuttingSticks {

	static int L, INF = (int) 1e9;
	static int cuts[];
	static int memo[][];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int n;
		
		while((L = sc.nextInt()) != 0) {
			n = sc.nextInt();
			cuts = new int[n + 2]; memo = new int[n + 2][n + 2];
			cuts[0] = 0; cuts[n + 1] = L;
			for(int i = 1; i <= n; ++i) cuts[i] = sc.nextInt();
			for(int i = 0; i < n + 2; ++i) Arrays.fill(memo[i], -1);
			out.printf("The minimum cutting is %d.\n", solve(0, n + 1));
		}
		
		out.flush();
		out.close();
	}
	
	private static int solve(int l, int r) {
		if(memo[l][r] != -1) return memo[l][r];
		if(l + 1 == r) return memo[l][r] = 0;
		
		int min = INF;
		
		for(int i = l + 1; i < r; ++i) {
			min = Math.min(min, solve(l, i) + (cuts[r] - cuts[l]) + solve(i, r));
		}
		return memo[l][r] = min;
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