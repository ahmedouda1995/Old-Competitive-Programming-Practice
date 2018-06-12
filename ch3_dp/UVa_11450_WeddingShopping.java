package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_11450_WeddingShopping {

	static int M, C, garments[][], memo[][] = new int[20][201];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt();
		
		while(t-- > 0) {
			M = sc.nextInt(); 
			C = sc.nextInt();
			garments = new int[C][];
			for(int i = 0; i < C; ++i) {
				int n = sc.nextInt();
				garments[i] = new int[n];
				for(int j = 0; j < n; ++j) garments[i][j] = sc.nextInt();
			}
			
			//for(int i = 0; i < C; ++i) Arrays.fill(memo[i], -1);
			
			//int res = solve(0, M);
			int res = solve();
			
			//for(int i = 0; i < C; ++i) System.out.println(Arrays.toString(memo[i]));
			
			if(res < 0)
				out.println("no solution");
			else
				out.println(res);
		}
		
		out.flush();
		out.close();
	}
	
	private static int solve() {
		for(int i = 0; i <= M; ++i) {
			int max = 0;
			for(int j = 0; j < garments[0].length; ++j) {
				if(garments[0][j] <= i)
					max = Math.max(max, garments[0][j]);
			}
			memo[0][i] = max;
		}
		
		if(memo[0][M] == 0)
			return -1;
		
		for(int i = 1; i < C; ++i) {
			for(int j = 0; j <= M; ++j) {
				int max = 0;
				for(int k = 0; k < garments[i].length; ++k) {
					if(garments[i][k] <= j && memo[i - 1][j - garments[i][k]] > 0)
						max = Math.max(max, garments[i][k] + memo[i - 1][j - garments[i][k]]);
				}
				memo[i][j] = max;
			}
			if(memo[i][M] == 0)
				return -1;
		}
		
		return memo[C - 1][M];
	}

//	private static int solve(int c, int m) {
//		if(m < 0)
//			return Integer.MIN_VALUE;
//		if(c == C)
//			return M - m;
//		if(memo[c][m] != -1)
//			return memo[c][m];
//		
//		int max = Integer.MIN_VALUE;
//		for(int i = 0; i < garments[c].length; ++i) {
//			if(garments[c][i] <= m) {
//				max = Math.max(max, (solve(c + 1, m - garments[c][i])));
//			}
//		}
//		return memo[c][m] = max;
//	}

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