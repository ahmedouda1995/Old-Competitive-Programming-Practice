package ch4_dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_782_ContourPainting {

	// review it again
	
	static char grid[][] = new char[100][100];
	static boolean vis[][];
	static int N, M;
	static int dr[] = {0, 0, 1, -1};
	static int dc[] = {-1, 1, 0, 0};
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt(); String s;
		
		while(t-- > 0) {
			
			for(int i = 0; i < 100; ++i) {
				for(int j = 0; j < 100; ++j) grid[i][j] = ' ';
			}
			
			int i = 0, starX = -1, starY = -1;
			while(!(s = sc.nextLine()).contains("_")) {
				
				for(int j = 0; j < s.length(); ++j) {
					if(s.charAt(j) == '*') {
						starX = i; starY = j;
						grid[i][j] = ' ';
					}
					else
						grid[i][j] = s.charAt(j);
				}
				i++;
			}
			N = i;
			
			vis = new boolean[100][100];
			
			dfs(starX, starY);
			
			for(i = 0; i < N; ++i) {
				int end = 99;
				while(end >= 0 && grid[i][end] == ' ') end--;
				for(int j = 0; j <= end; ++j) out.print(grid[i][j]);
				out.println();
			}
			
			out.println(s);
		}
			
		out.flush();
		out.close();
	}

	private static void dfs(int i, int j) {
		vis[i][j] = true;
		int x, y;
		for(int k = 0; k < 4; ++k) {
			x = i + dr[k]; y = j + dc[k];
			
			if(valid(x, y)) {
				if(grid[x][y] != ' ') {
					if(grid[x][y] != '#') grid[i][j] = '#';
				}
				else
					dfs(x, y);
			}
		}
	}

	static boolean valid(int i, int j) {
		if(i < 0 || j < 0 || i >= N || j > 99 || vis[i][j]) return false;
		return true;
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