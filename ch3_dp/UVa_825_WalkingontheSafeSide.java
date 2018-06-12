package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_825_WalkingontheSafeSide {

	static int N, M;
	static boolean blocked[][];
	static int memo[][];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int t = sc.nextInt();
		
		boolean first = true;
		
		while(t-- > 0) {
			if(first) first = false; else out.println();
			N = sc.nextInt(); M = sc.nextInt();
			
			blocked = new boolean[N][M];
			memo = new int[N][M];
			int r, c;
			StringTokenizer st;
			for(int i = 0; i < N; ++i) {
				Arrays.fill(memo[i], -1);
				st = new StringTokenizer(sc.nextLine());
				int m = st.countTokens() - 1;
				r = Integer.parseInt(st.nextToken()) - 1;
				while(m-- > 0) {
					c = Integer.parseInt(st.nextToken()) - 1;
					blocked[r][c] = true;
				}
			}
			
			out.println(solve(0, 0));
		}
		
		out.flush();
		out.close();
	}
	
	private static int solve(int i, int j) {
		if(i == N - 1 && j == M - 1) return 1;
		if(memo[i][j] != -1) return memo[i][j];
		
		int path1 = 0, path2 = 0;
		if(isSafe(i + 1, j)) { path1 = solve(i + 1, j); }
		if(isSafe(i, j + 1)) { path2 = solve(i, j + 1); }
		
		return path1 + path2;
	}

	static boolean isSafe(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < M && !blocked[x][y];
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