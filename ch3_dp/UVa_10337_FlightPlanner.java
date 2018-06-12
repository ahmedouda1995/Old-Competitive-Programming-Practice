package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_10337_FlightPlanner {

	static int arr[] = {60, 30, 20};
	static final int INF = (int) 1e9;
	static int N = 10, M;
	static int grid[][] = new int[10][1000];
	static int memo[][] = new int[10][1000];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int t = sc.nextInt();
		
		while(t-- > 0) {
			M = sc.nextInt() / 100;
			
			for(int i = 0; i < N; ++i)
				for(int j = 0; j < M; ++j) {
					grid[i][j] = sc.nextInt();
					memo[i][j] = -1;
				}
			
			out.println(solve(N - 1, 0) + "\n");
		}
		
		out.flush();
		out.close();
	}
	
	private static int solve(int i, int j) {
		if(j == M) { if(i == N - 1) return 0; else return INF; }
		if(memo[i][j] != -1) return memo[i][j];
		
		int a, b;
		int min = INF;
		
		for(int k = -1; k <= 1; ++k) {
			a = i + k; b = j + 1;
			if(isSafe(a, b)) min = Math.min(min, arr[k + 1] - grid[i][j] + solve(a, b));
		}
		return memo[i][j] = min;
	}

	static boolean isSafe(int x, int y) {
		return x >= 0 && x < N;
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