package ch4_dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_784_MazeExploration {

	static char[][] grid = new char[30][80];
	static int len[] = new int[30];
	static int N, M;
	static int dr[] = {1, -1, 0, 0};
	static int dc[] = {0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt();
		
		while(t-- > 0) {
			String s;
			int i = 0, j = 0, a = 0, b = 0;
			while((s = sc.nextLine()).charAt(0) != '_') {
				for(j = 0; j < s.length(); ++j) {
					grid[i][j] = s.charAt(j);
					if(grid[i][j] == '*') { a = i; b = j; grid[i][j] = ' '; }
				}
				len[i] = j;
				M = Math.max(M, j);
				i++;
			}
			N = i;
			dfs(a, b);
			
			for(int k = 0; k < N; ++k) {
				for(int l = 0; l < len[k]; ++l) out.print(grid[k][l]);
				out.println();
			}
			out.println(s);
		}
		
		out.flush();
		out.close();
	}
	
	private static void dfs(int i, int j) {
		if(i < 0 || j < 0 || i >= N || j >= M || grid[i][j] != ' ') return;
		
		grid[i][j] = '#';
		
		for(int k = 0; k < 4; ++k) dfs(i + dr[k], j + dc[k]);
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