package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_11137_IngenuousCubrency {

	static int den[] = new int[21];
	static long dp[] = new long[10000];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		for(int i = 1; i <= 21; ++i) {
			den[i - 1] = i * i * i;
		}
		solve();
		while(sc.ready()) {
			out.println(dp[sc.nextInt()]);
		}
		
		out.flush();
		out.close();
	}
	
	private static void solve() {
		Arrays.fill(dp, 1);
		
		for(int i = 1; i < 21; ++i) {
			for(int j = den[i]; j < 10000; ++j) {
				dp[j] += dp[j - den[i]];
			}
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