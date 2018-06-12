package ch4_dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_11470_SquareSums {

	static int grid[][] = new int[10][10];
	static boolean vis[][] = new boolean[10][10];
	static int dr[] = {0, 1, 0, -1}; // right - down - left - up
	static int dc[] = {1, 0, -1, 0};
	static int N;
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int cases = 1;
		
		while((N = sc.nextInt()) != 0) {
			for(int i = 0; i < N; ++i) {
				for(int j = 0; j < N; ++j) {
					grid[i][j] = sc.nextInt();
					vis[i][j] = false;
				}
			}
			
			out.printf("Case %d:", cases++);
			for(int i = 0; i < Math.ceil((N / 2.0)); ++i) {
				long res = 0;
				res += dfs(i, i, 0);
				out.print(" " + res);
			}
			out.println();
		}
		
		out.flush();
		out.close();
	}

	private static long dfs(int i, int j, int dir) {
		long res = 0;
		
		res += grid[i][j];
		vis[i][j] = true;
		int x = i + dr[dir], y = j + dc[dir];
		
		if(x < 0 || y < 0 || x >= N || y >= N || (vis[x][y] && dir != 3)) {
			dir = (dir + 1) % 4;
			x = i + dr[dir]; y = j + dc[dir];
		}
		if((!(x < 0 || y < 0 || x >= N || y >= N)) && !vis[x][y])
			res += dfs(x, y, dir);
		
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