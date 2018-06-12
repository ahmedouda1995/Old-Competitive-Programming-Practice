package ch4_dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_11110_Equidivisions {

	static int N;
	static int grid[][];
	static int dr[] = {1, -1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		while((N = sc.nextInt()) != 0) {
			grid = new int[N][N];
			StringTokenizer st; int a, b;
			
			for(int i = 1; i < N; ++i) {
				st = new StringTokenizer(sc.nextLine());
				for(int j = 0; j < N; ++j) {
					a = Integer.parseInt(st.nextToken()) - 1;
					b = Integer.parseInt(st.nextToken()) - 1;
					grid[a][b] = i;
				}
			}
			boolean ans = true;
			label : for(int i = 0; i < N; ++i) {
				for(int j = 0; j < N; ++j) {
					if(grid[i][j] != -1) {
						int tmp = dfs(i, j, grid[i][j]);
						if(tmp != N) { ans = false; break label; };
					}
				}
			}
			
			if(ans) out.println("good"); else out.println("wrong");
		}
		
		out.flush();
		out.close();
	}

	private static int dfs(int i, int j, int c) {
		if(i < 0 || j < 0 || i >= N || j >= N || grid[i][j] != c) return 0;
		
		int res = 1;
		grid[i][j] = -1;
		
		for(int k = 0; k < 4; ++k) res += dfs(i + dr[k], j + dc[k], c);
		
		return res;
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