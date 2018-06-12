package ch4_dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_785_GridColouring {

	static char[][] grid = new char[80][80];
	static boolean vis[][];
	static int len[] = new int[80];
	static int dr[] = {0, 0, 1, -1};
	static int dc[] = {1, -1, 0, 0};
	static int N, M;
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		while(sc.ready()) {
			String s;
			int i = 0; char contour = ' '; int max = -1;
			while(!(s = sc.nextLine()).contains("_")) {
				len[i] = s.length();
				
				for(int j = 0; j < s.length(); ++j) {
					grid[i][j] = s.charAt(j); max = Math.max(max, s.length());
					if(contour == ' ' && grid[i][j] != ' ') contour = grid[i][j];
				}
				i++;
			}
			N = i; M = max;
			
			vis = new boolean[N][M];
			
			for(i = 0; i < N; ++i) {
				for(int j = 0; j < len[i]; ++j) {
					if(grid[i][j] != ' ' && grid[i][j] != contour && !vis[i][j]) {
						dfs(i, j, contour, grid[i][j]);
					}
				}
			}
			
			for(i = 0; i < N; ++i) {
				for(int j = 0; j < len[i]; ++j) out.print(grid[i][j]);
				out.println();
			}
			
			out.println(s);
		}
		
		out.flush();
		out.close();
	}
	
	private static void dfs(int i, int j, char contour, char c) {
		if(i < 0 || j < 0 || i >= N || j >= M || grid[i][j] == contour || vis[i][j]) return;
		
		grid[i][j] = c;
		vis[i][j] = true;
		
		for(int k = 0; k < 4; ++k) dfs(i + dr[k], j + dc[k], contour, c);
	}

	static class Pair implements Comparable<Pair>{
		int x;
		int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Pair p) {
			if(Integer.compare(this.x, p.x) == 0)
				return Integer.compare(this.y, p.y);
			return Integer.compare(this.x, p.x);
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