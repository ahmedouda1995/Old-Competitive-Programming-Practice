package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_10910_MarksDistribution {

	static int N, T, P;
	static int memo[][] = new int[70][71];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int t = sc.nextInt();
		
		while(t-- > 0) {
			N = sc.nextInt(); T = sc.nextInt(); P = sc.nextInt();
			for(int i = 0; i < N; ++i) Arrays.fill(memo[i], -1);
			out.println(solve(1, T));
		}
		
		out.flush();
		out.close();
	}
	
	private static int solve(int idx, int t) {
		if(idx == N) return 1;
		if(memo[idx][t] != -1) return memo[idx][t];
		
		int m = (t - (N - idx) * P);
		int ways = 0;
		for(int i = P; i <= m; ++i) {
			ways += solve(idx + 1, t - i);
		}
		return memo[idx][t] = ways;
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