package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_116_UnidirectionalTSP {

	static int N, M;
	static int grid[][] = new int[10][100];
	static int memo[][] = new int[10][100];
	static int sol[][] = new int[10][100];
	static PrintWriter out = new PrintWriter(System.out);
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));

		while(sc.ready()) {
			N = sc.nextInt(); M = sc.nextInt();
			
			for(int i = 0; i < N; ++i)
				for(int j = 0; j < M; ++j) {
					grid[i][j] = sc.nextInt();
					memo[i][j] = Integer.MIN_VALUE;
				}

			int min = Integer.MAX_VALUE, minStart = 0;
			
			for(int i = 0; i < N; ++i) {
				int tmp = solve(i, 0);
				if(tmp < min) {
					min = tmp; minStart = i;
				}
			}
			printPath(minStart, 0);
			out.println(min);
		}
		
		out.flush();
		out.close();
	}
	
	private static void printPath(int i, int j) {
		if(j == M - 1) { out.println(i + 1); return; }
		else {
			out.print((i + 1) + " ");
			printPath(sol[i][j], j + 1);
		}
	}

	private static int solve(int i, int j) {
		if(j == M - 1) return grid[i][j];
		if(memo[i][j] != Integer.MIN_VALUE) return memo[i][j];
		
		int a = (((i - 1) % N) + N) % N, b = i, c = (i + 1) % N;
		int min = Integer.MAX_VALUE, tmp, minPos = N;
		
		tmp = grid[i][j] + solve(a, j + 1);
		if(tmp <= min) {
			if(tmp == min && a < minPos) { minPos = a; }
			else if(tmp < min) { min = tmp; minPos = a; }
		}
		
		tmp = grid[i][j] + solve(b, j + 1);
		if(tmp <= min) {
			if(tmp == min && b < minPos) { minPos = b; }
			else if(tmp < min) { min = tmp; minPos = b; }
		}
		
		tmp = grid[i][j] + solve(c, j + 1);
		if(tmp <= min) {
			if(tmp == min && c < minPos) { minPos = c; }
			else if(tmp < min) { min = tmp; minPos = c; }
		}
		sol[i][j] = minPos;
		return memo[i][j] = min;
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