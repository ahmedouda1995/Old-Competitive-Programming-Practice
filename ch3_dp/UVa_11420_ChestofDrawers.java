package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_11420_ChestofDrawers {

	static int N, S;
	static long memo[][][] = new long[65][2][65 + 1]; // 0 - U , 1 - L
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		while((N = sc.nextInt()) >= 0 | (S = sc.nextInt()) >= 0) {
			for(int i = 0; i < N; ++i) {
				Arrays.fill(memo[i][0], -1);
				Arrays.fill(memo[i][1], -1);
			}
			out.println(solve(0, Lock.L, S));
		}
		
		out.flush();
		out.close();
	}
	
	private static long solve(int idx, Lock prev, int s) {
		if(idx == N) {
			if(s == 0) return 1;
			return 0;
		}
		if(s < 0) return 0;
		if(memo[idx][prev.ordinal()][s] != -1) return memo[idx][prev.ordinal()][s];
		
		long first, second;
		if(prev == Lock.L) {
			first = solve(idx + 1, Lock.L, s - 1);
			second = solve(idx + 1, Lock.U, s);
		}
		else {
			first = solve(idx + 1, Lock.L, s);
			second = solve(idx + 1, Lock.U, s);
		}
		return memo[idx][prev.ordinal()][s] = first + second;
	}

	static enum Lock { U, L }
	
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