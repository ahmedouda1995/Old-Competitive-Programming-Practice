package ch4_dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_00352_TheSeasonalWar {

	static int N;
	static char grid[][] = new char[25][25];
	static int dr[] = {1, 1, 0, -1, -1, -1, 0, 1};
	static int dc[] = {0, 1, 1, 1, 0, -1, -1, -1};
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int n, cases = 1; String s;
		
		while((s = sc.nextLine()) != null) {
			n = Integer.parseInt(s);
			N = n;
			for(int i = 0; i < n; ++i) {
				s = sc.nextLine();
				for(int j = 0; j < n; ++j) grid[i][j] = s.charAt(j);
			}
			int res = 0;
			for(int i = 0; i < n; ++i) {
				for(int j = 0; j < n; ++j) {
					if(grid[i][j] == '1') {
						res++;
						dfs(i, j);
					}
				}
			}
			out.printf("Image number %d contains %d war eagles.\n", cases++, res);
		}
		
		out.flush();
		out.close();
	}
	
	private static void dfs(int x, int y) {
		if(x < 0 || x >= N || y < 0 || y >= N) return;
		if(grid[x][y] == '0') return;
		
		grid[x][y] = '0';
		
		for(int i = 0; i < 8; ++i) dfs(x + dr[i], y + dc[i]);
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